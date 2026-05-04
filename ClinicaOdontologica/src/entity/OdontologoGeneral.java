package entity;

public class OdontologoGeneral extends Odontologo{

    public OdontologoGeneral(String nombre, String apellido, Integer dni, String matricula) {
        super(nombre, apellido, dni, matricula);
    }

    @Override
    public double getTarifaBase() {
        return 50000.0;
    }

    @Override
    public String getEspecialidad() {
        return "Odontologo General";
    }

    @Override
    public boolean puedeAtender(String motivoConsulta) {
        return true;
    }
}
