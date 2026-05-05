package view;

import entity.EstadoTurno;
import entity.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

public class VistaTurno {

    private Scanner scanner;

    public VistaTurno() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== MENU TURNOS ===");
        System.out.println("1. Registrar turno");
        System.out.println("2. Buscar turno por ID");
        System.out.println("3. Listar todos los turnos");
        System.out.println("4. Listar turnos por paciente");
        System.out.println("5. Listar turnos por odontologo");
        System.out.println("6. Listar turnos por secretaria");
        System.out.println("7. Modificar turno");
        System.out.println("8. Cambiar estado de turno");
        System.out.println("9. Eliminar turno");
        System.out.println("10. Calcular monto de turno");
        System.out.println("0. Volver");
        return leerOpcionMenu("Seleccione una opcion: ", 0, 10);
    }

    public DatoTurno pedirDatosTurno() {
        DatoTurno dato = new DatoTurno();

        System.out.println("\n=== REGISTRO DE TURNO ===");
        dato.setDniPaciente(leerInteger("DNI del paciente: "));
        dato.setMatriculaOdontologo(leerString("Matricula del odontologo: "));
        dato.setDniSecretaria(leerInteger("DNI de la secretaria: "));
        dato.setFecha(leerFecha());
        dato.setHora(leerHora());
        dato.setMotivoConsulta(leerString("Motivo de consulta: "));

        return dato;
    }

    public DatoTurno pedirDatosTurnoActualizado() {
        DatoTurno dato = new DatoTurno();

        System.out.println("\n=== MODIFICACION DE TURNO ===");
        dato.setId(leerLong("ID del turno: "));
        dato.setMatriculaOdontologo(leerString("Nueva matricula del odontologo: "));
        dato.setDniSecretaria(leerInteger("Nuevo DNI de la secretaria: "));
        dato.setFecha(leerFecha());
        dato.setHora(leerHora());
        dato.setMotivoConsulta(leerString("Nuevo motivo de consulta: "));
        dato.setEstado(pedirEstadoTurno());

        return dato;
    }

    public Long pedirIdTurno() {
        return leerLong("Ingrese el ID del turno: ");
    }

    public Long pedirIdPaciente() {
        return leerLong("Ingrese el ID del paciente: ");
    }

    public Long pedirIdOdontologo() {
        return leerLong("Ingrese el ID del odontologo: ");
    }

    public Long pedirIdSecretaria() {
        return leerLong("Ingrese el ID de la secretaria: ");
    }

    public EstadoTurno pedirEstadoTurno() {
        System.out.println("\nEstados disponibles:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. CONFIRMADO");
        System.out.println("3. CANCELADO");
        System.out.println("4. COMPLETADO");

        int opcion = leerOpcionMenu("Seleccione un estado: ", 1, 4);

        switch (opcion) {
            case 1:
                return EstadoTurno.PENDIENTE;
            case 2:
                return EstadoTurno.CONFIRMADO;
            case 3:
                return EstadoTurno.CANCELADO;
            case 4:
                return EstadoTurno.COMPLETADO;
            default:
                return EstadoTurno.PENDIENTE;
        }
    }

    public void mostrarTurno(Turno turno) {
        if (turno == null) {
            System.out.println("No se encontro el turno.");
            return;
        }

        System.out.println(turno);
    }

    public void mostrarTurnos(List<Turno> turnos) {
        if (turnos == null || turnos.isEmpty()) {
            System.out.println("No hay turnos registrados.");
            return;
        }

        System.out.println("\n=== LISTADO DE TURNOS ===");
        for (Turno turno : turnos) {
            System.out.println(turno);
            System.out.println("-----------------------------------");
        }
    }

    public void mostrarMonto(Double monto) {
        if (monto == null) {
            System.out.println("No se pudo calcular el monto del turno.");
            return;
        }

        System.out.println("Monto a pagar: $" + monto);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private String leerString(String mensaje) {
        String valor;

        do {
            System.out.print(mensaje);
            valor = scanner.nextLine().trim();

            if (valor.isEmpty()) {
                System.out.println("Error: el valor no puede estar vacio.");
            }
        } while (valor.isEmpty());

        return valor;
    }

    private Integer leerInteger(String mensaje) {
        String valorTexto;

        do {
            System.out.print(mensaje);
            valorTexto = scanner.nextLine().trim();

            if (!esNumeroEnteroPositivo(valorTexto)) {
                System.out.println("Error: debe ingresar un numero entero positivo.");
            }
        } while (!esNumeroEnteroPositivo(valorTexto));

        return Integer.parseInt(valorTexto);
    }

    private Long leerLong(String mensaje) {
        String valorTexto;

        do {
            System.out.print(mensaje);
            valorTexto = scanner.nextLine().trim();

            if (!esNumeroEnteroPositivo(valorTexto)) {
                System.out.println("Error: debe ingresar un numero valido.");
            }
        } while (!esNumeroEnteroPositivo(valorTexto));

        return Long.parseLong(valorTexto);
    }

    private int leerOpcionMenu(String mensaje, int minimo, int maximo) {
        int opcion;

        do {
            opcion = leerInteger(mensaje);

            if (opcion < minimo || opcion > maximo) {
                System.out.println("Error: debe elegir una opcion entre " + minimo + " y " + maximo + ".");
            }
        } while (opcion < minimo || opcion > maximo);

        return opcion;
    }

    private LocalDate leerFecha() {
        int anio;
        int mes;
        int dia;
        int maxDia;

        do {
            anio = leerInteger("Anio: ");
            mes = leerInteger("Mes: ");

            if (mes < 1 || mes > 12) {
                System.out.println("Error: el mes debe estar entre 1 y 12.");
            }
        } while (mes < 1 || mes > 12);

        maxDia = YearMonth.of(anio, mes).lengthOfMonth();

        do {
            dia = leerInteger("Dia: ");

            if (dia < 1 || dia > maxDia) {
                System.out.println("Error: el dia debe estar entre 1 y " + maxDia + ".");
            }
        } while (dia < 1 || dia > maxDia);

        return LocalDate.of(anio, mes, dia);
    }

    private LocalTime leerHora() {
        int hora;
        int minuto;

        do {
            hora = leerInteger("Hora (0-23): ");

            if (hora < 0 || hora > 23) {
                System.out.println("Error: la hora debe estar entre 0 y 23.");
            }
        } while (hora < 0 || hora > 23);

        do {
            minuto = leerInteger("Minuto (0-59): ");

            if (minuto < 0 || minuto > 59) {
                System.out.println("Error: el minuto debe estar entre 0 y 59.");
            }
        } while (minuto < 0 || minuto > 59);

        return LocalTime.of(hora, minuto);
    }

    private boolean esNumeroEnteroPositivo(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }

        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}