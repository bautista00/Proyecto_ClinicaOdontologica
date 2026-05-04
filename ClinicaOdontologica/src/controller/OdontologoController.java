package controller;

import entity.Endodoncista;
import entity.Odontologo;
import entity.OdontologoGeneral;
import entity.Ortodoncista;
import service.OdontologoServiceImpl;
import view.DatoOdontologo;

import java.util.List;

public class OdontologoController {
    private OdontologoServiceImpl odontologoService;

    public OdontologoController(OdontologoServiceImpl odontologoService) {
        this.odontologoService = odontologoService;
    }

    public Odontologo registrar(DatoOdontologo dato) {
        Odontologo odontologo = null;

        if (dato.getTipo() == 1) {
            odontologo = new OdontologoGeneral(
                    dato.getNombre(),
                    dato.getApellido(),
                    dato.getDni(),
                    dato.getMatricula()
            );
        } else if (dato.getTipo() == 2) {
            odontologo = new Ortodoncista(
                    dato.getNombre(),
                    dato.getApellido(),
                    dato.getDni(),
                    dato.getMatricula()
            );
        } else if (dato.getTipo() == 3) {
            odontologo = new Endodoncista(
                    dato.getNombre(),
                    dato.getApellido(),
                    dato.getDni(),
                    dato.getMatricula()
            );
        }

        if (odontologo == null) {
            System.out.println("Tipo de odontologo invalido.");
            return null;
        }

        return odontologoService.registrar(odontologo);
    }

    public Odontologo buscarPorId(Long id) {
        return odontologoService.buscarPorId(id);
    }

    public List<Odontologo> listar() {
        return odontologoService.listar();
    }

    public void eliminar(Long id) {
        odontologoService.eliminar(id);
    }
}
