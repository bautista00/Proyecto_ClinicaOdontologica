package entity;

public class Endodoncista extends Odontologo{

    public Endodoncista( String nombre, String apellido, Integer dni, String matricula) {
        super(nombre, apellido, dni, matricula);
    }

    @Override
    public double getTarifaBase() {
        return 80000.0;
    }

    @Override
    public String getEspecialidad() {
        return "Endodoncista";
    }

    @Override
    public boolean puedeAtender(String motivoConsulta) {
        return motivoConsulta != null &&
                motivoConsulta.toLowerCase().contains("endodoncia");
    }
}
