package service;

import entity.Odontologo;
import entity.Paciente;

public class Facturador {

    private static final Double COPAGO_OBRA_SOCIAL = 10000.0;

    public Double calcularMonto(Paciente paciente, Odontologo odontologo) {
        if (paciente == null) {
            System.out.println("Error: el paciente no puede ser nulo.");
            return null;
        }

        if (odontologo == null) {
            System.out.println("Error: el odontologo no puede ser nulo.");
            return null;
        }

        if (Boolean.TRUE.equals(paciente.getObraSocial())) {
            return COPAGO_OBRA_SOCIAL;
        }

        return odontologo.getTarifaBase();
    }
}