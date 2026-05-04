package controller;

import entity.Endodoncista;
import entity.Odontologo;
import entity.OdontologoGeneral;
import entity.Ortodoncista;
import service.OdontologoServiceImpl;

import java.util.List;

public class OdontologoController {

    private OdontologoServiceImpl odontologoService;

    public OdontologoController(OdontologoServiceImpl odontologoService) {
        this.odontologoService = odontologoService;
    }

    public Odontologo registrarOdontologo(String tipoEspecialidad,
                                          String nombre,
                                          String apellido,
                                          Integer dni,
                                          String matricula) {

        Odontologo odontologo = crearOdontologoSegunEspecialidad(tipoEspecialidad, nombre, apellido, dni, matricula);
        if (odontologo == null) {
            System.out.println("Error: tipo de especialidad invalido.");
            return null;
        }

        return odontologoService.registrar(odontologo);
    }

    public Odontologo buscarOdontologoPorId(Long id) {
        return odontologoService.buscarPorId(id);
    }

    public Odontologo buscarOdontologoPorMatricula(String matricula) {
        return odontologoService.buscarPorMatricula(matricula);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoService.listarTodos();
    }

    public Odontologo actualizarOdontologo(Long id,
                                           String nombre,
                                           String apellido,
                                           Integer dni,
                                           String matricula) {

        Odontologo odontologoExistente = odontologoService.buscarPorId(id);
        if (odontologoExistente == null) {
            return null;
        }

        odontologoExistente.setNombre(nombre);
        odontologoExistente.setApellido(apellido);
        odontologoExistente.setDni(dni);
        odontologoExistente.setMatricula(matricula);

        return odontologoService.actualizar(odontologoExistente);
    }

    public boolean eliminarOdontologo(Long id) {
        return odontologoService.eliminar(id);
    }

    private Odontologo crearOdontologoSegunEspecialidad(String tipoEspecialidad,
                                                        String nombre,
                                                        String apellido,
                                                        Integer dni,
                                                        String matricula) {

        if (tipoEspecialidad == null || tipoEspecialidad.isBlank()) {
            return null;
        }

        String tipoNormalizado = tipoEspecialidad.trim().toLowerCase();

        switch (tipoNormalizado) {
            case "general":
            case "odontologia general":
                return new OdontologoGeneral(nombre, apellido, dni, matricula);

            case "ortodoncista":
            case "ortodoncia":
                return new Ortodoncista(nombre, apellido, dni, matricula);

            case "endodoncista":
            case "endodoncia":
                return new Endodoncista(nombre, apellido, dni, matricula);

            default:
                return null;
        }
    }
}