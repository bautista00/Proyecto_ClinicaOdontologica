package view;

public class DatoOrtodoncista extends DatoOdontologo {

    public DatoOrtodoncista() {
    }

    public DatoOrtodoncista(Long id, String nombre, String apellido, Integer dni, String matricula) {
        super(id, nombre, apellido, dni, matricula);
    }
}