package view;

public class DatoPaciente extends DatoPersona {

    private String email;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
    private Boolean obraSocial;

    public DatoPaciente() {
    }

    public DatoPaciente(Long id, String nombre, String apellido, Integer dni,
                        String email, String calle, Integer numero,
                        String localidad, String provincia, Boolean obraSocial) {
        super(id, nombre, apellido, dni);
        this.email = email;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.obraSocial = obraSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Boolean getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(Boolean obraSocial) {
        this.obraSocial = obraSocial;
    }
}