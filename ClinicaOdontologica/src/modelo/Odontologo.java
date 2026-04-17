package modelo;

import java.util.ArrayList;
import java.util.List;

public class Odontologo {

    private static Long contadorId = 0L;

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    private List<Turno> historialOdontologo;

    public Odontologo(String nombre, String apellido, String matricula) {
        contadorId++;
        this.id = contadorId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.historialOdontologo = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Turno> getHistorialOdontologo() {
        return historialOdontologo;
    }

    public void setHistorialOdontologo(List<Turno> historialOdontologo) {
        this.historialOdontologo = historialOdontologo;
    }

    @Override
    public String toString() {
        return "\n === Informacion del Odontologo ===" +
                "\n ID: " + id +
                "\n Nombre:" + nombre + " " + apellido +
                "\n Matricula: " + matricula;
    }
}