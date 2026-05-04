package service;

import entity.Turno;

public class Facturador {
    private static final double COPAGO_OBRA_SOCIAL = 10000.0;
    private static final double RECARGO_SIN_OS = 1.0;

    public double calcularMonto(Turno turno) {

        if (turno.getPaciente().getObraSocial()) {
            return COPAGO_OBRA_SOCIAL;
        } else {
            return turno.getOdontologo().getTarifaBase() * RECARGO_SIN_OS;
        }
    }
}
