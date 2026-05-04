package entity;

public abstract class Persona {
    private static Long contadorId = 0L;
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected Integer dni;

    public Persona() {
    }

    public Persona( String nombre, String apellido, Integer dni) {
        contadorId++;
        this.id = contadorId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public Integer getDni() {return dni;}

    public void setDni(Integer dni) {this.dni = dni;}

    @Override
    public String toString() {
        return "\n === Informacion General ===" +
                "\n ID:" + id +
                "\n Nombre:'" + nombre +
                "\n Apellido: " + apellido +
                "\n DNI: " + dni;
    }
}
