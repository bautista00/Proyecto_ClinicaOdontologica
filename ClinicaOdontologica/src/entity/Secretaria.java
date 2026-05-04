package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Secretaria extends Persona{
    private List<Turno> historialSecretaria;

    public Secretaria() {
    }

    public Secretaria(String nombre, String apellido, Integer dni) {
        super(nombre, apellido, dni);
        this.historialSecretaria = new ArrayList<>();
    }

    public List<Turno> getHistorialSecretaria() {
        return historialSecretaria;
    }

    public void agregarTurno(Turno turno) {
        historialSecretaria.add(turno);
    }

    @Override
    public String toString() {
        return "\n=== Informacion de la Secretaria ===\n" +
                super.toString();
    }
}
