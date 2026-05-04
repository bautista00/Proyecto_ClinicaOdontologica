package entity;

public class Ortodoncista extends Odontologo{

    public Ortodoncista( String nombre, String apellido, Integer dni, String matricula) {
        super(nombre, apellido, dni, matricula);
    }

    @Override
    public double getTarifaBase() {
        return 70000.0;
    }

    @Override
    public String getEspecialidad() {
        return "Ortodoncista";
    }

    @Override
    public boolean puedeAtender(String motivoConsulta) {
        return motivoConsulta != null &&
                motivoConsulta.toLowerCase().contains("ortodoncia");
    }
}
