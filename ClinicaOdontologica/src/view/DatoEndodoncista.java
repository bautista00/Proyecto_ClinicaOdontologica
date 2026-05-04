package view;

public class DatoEndodoncista extends DatoOdontologo {

    public DatoEndodoncista() {
    }

    public DatoEndodoncista(Long id, String nombre, String apellido, Integer dni, String matricula) {
        super(id, nombre, apellido, dni, matricula);
    }
}