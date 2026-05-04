package service;

import entity.Odontologo;
import repository.OdontologoRepository;

import java.util.List;

public class OdontologoServiceImpl implements IService<Odontologo> {

    private OdontologoRepository odontologoRepository;

    public OdontologoServiceImpl(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        if (!validarOdontologo(odontologo)) {
            return null;
        }

        if (odontologoRepository.existeMatricula(odontologo.getMatricula())) {
            System.out.println("Error: ya existe un odontologo con esa matricula.");
            return null;
        }

        odontologoRepository.guardar(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: el ID del odontologo es invalido.");
            return null;
        }

        Odontologo odontologo = odontologoRepository.buscarPorId(id);
        if (odontologo == null) {
            System.out.println("Error: no existe un odontologo con ese ID.");
            return null;
        }

        return odontologo;
    }

    public Odontologo buscarPorMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            System.out.println("Error: la matricula es invalida.");
            return null;
        }

        Odontologo odontologo = odontologoRepository.buscarPorMatricula(matricula);
        if (odontologo == null) {
            System.out.println("Error: no existe un odontologo con esa matricula.");
            return null;
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.listarTodos();
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        if (!validarOdontologo(odontologo)) {
            return null;
        }

        Odontologo odontologoExistente = odontologoRepository.buscarPorId(odontologo.getId());
        if (odontologoExistente == null) {
            System.out.println("Error: no existe un odontologo con ese ID.");
            return null;
        }

        Odontologo odontologoConMismaMatricula = odontologoRepository.buscarPorMatricula(odontologo.getMatricula());
        if (odontologoConMismaMatricula != null && !odontologoConMismaMatricula.getId().equals(odontologo.getId())) {
            System.out.println("Error: ya existe otro odontologo con esa matricula.");
            return null;
        }

        odontologoRepository.actualizar(odontologo);
        return odontologo;
    }

    @Override
    public boolean eliminar(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: el ID del odontologo es invalido.");
            return false;
        }

        Odontologo odontologoExistente = odontologoRepository.buscarPorId(id);
        if (odontologoExistente == null) {
            System.out.println("Error: no existe un odontologo con ese ID.");
            return false;
        }

        odontologoRepository.eliminar(id);
        return true;
    }

    private boolean validarOdontologo(Odontologo odontologo) {
        if (odontologo == null) {
            System.out.println("Error: el odontologo no puede ser nulo.");
            return false;
        }

        if (odontologo.getNombre() == null || odontologo.getNombre().isBlank()) {
            System.out.println("Error: el nombre del odontologo no puede estar vacio.");
            return false;
        }

        if (odontologo.getApellido() == null || odontologo.getApellido().isBlank()) {
            System.out.println("Error: el apellido del odontologo no puede estar vacio.");
            return false;
        }

        if (odontologo.getDni() == null || odontologo.getDni() <= 0) {
            System.out.println("Error: el DNI del odontologo es invalido.");
            return false;
        }

        if (odontologo.getMatricula() == null || odontologo.getMatricula().isBlank()) {
            System.out.println("Error: la matricula no puede estar vacia.");
            return false;
        }

        return true;
    }
}