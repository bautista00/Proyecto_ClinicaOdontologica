package service;

import entity.Odontologo;
import repository.OdontologoRepository;

import java.util.List;

public class OdontologoServiceImpl implements IService<Odontologo> {
    private OdontologoRepository odontologoRepository;

    public OdontologoServiceImpl(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public Odontologo registrar(Odontologo odontologo) {

        if (odontologo.getNombre() == null || odontologo.getNombre().isEmpty()) {
            System.out.println("Error: El nombre es obligatorio");
            return null;
        }

        if (odontologo.getApellido() == null || odontologo.getApellido().isEmpty()) {
            System.out.println("Error: El apellido es obligatorio");
            return null;
        }

        if (odontologo.getDni() == null || odontologo.getDni() <= 0) {
            System.out.println("Error: DNI invalido");
            return null;
        }

        if (odontologo.getMatricula() == null || odontologo.getMatricula().isEmpty()) {
            System.out.println("Error: La matricula es obligatoria");
            return null;
        }

        Odontologo existente = odontologoRepository.buscarPorId(odontologo.getId());

        if (existente != null) {
            System.out.println("Error: Ya existe un odontologo con ese ID");
            return null;
        }

        odontologoRepository.guardar(odontologo);
        return odontologo;
    }

    @Override
    public void guardar(Odontologo o) {
        odontologoRepository.guardar(o);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return odontologoRepository.buscarPorId(id);
    }

    @Override
    public List<Odontologo> listar() {
        return odontologoRepository.listar();
    }

    @Override
    public void actualizar(Odontologo o) {
        odontologoRepository.actualizar(o);
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.eliminar(id);
    }
}
