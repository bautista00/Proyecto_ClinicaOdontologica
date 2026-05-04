package view;

import entity.EstadoTurno;
import entity.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
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
        System.out.print("Seleccione una opcion: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public DatoTurno pedirDatosTurno() {
        DatoTurno dato = new DatoTurno();

        System.out.println("\n=== REGISTRO DE TURNO ===");

        System.out.print("ID del paciente: ");
        dato.setIdPaciente(Long.parseLong(scanner.nextLine()));

        System.out.print("ID del odontologo: ");
        dato.setIdOdontologo(Long.parseLong(scanner.nextLine()));

        System.out.print("ID de la secretaria: ");
        dato.setIdSecretaria(Long.parseLong(scanner.nextLine()));

        System.out.print("Fecha (yyyy-mm-dd): ");
        dato.setFecha(LocalDate.parse(scanner.nextLine()));

        System.out.print("Hora (HH:mm): ");
        dato.setHora(LocalTime.parse(scanner.nextLine()));

        System.out.print("Motivo de consulta: ");
        dato.setMotivoConsulta(scanner.nextLine());

        return dato;
    }

    public DatoTurno pedirDatosTurnoActualizado() {
        DatoTurno dato = new DatoTurno();

        System.out.println("\n=== MODIFICACION DE TURNO ===");

        System.out.print("ID del turno: ");
        dato.setId(Long.parseLong(scanner.nextLine()));

        System.out.print("Nuevo ID del odontologo: ");
        dato.setIdOdontologo(Long.parseLong(scanner.nextLine()));

        System.out.print("Nuevo ID de la secretaria: ");
        dato.setIdSecretaria(Long.parseLong(scanner.nextLine()));

        System.out.print("Nueva fecha (yyyy-mm-dd): ");
        dato.setFecha(LocalDate.parse(scanner.nextLine()));

        System.out.print("Nueva hora (HH:mm): ");
        dato.setHora(LocalTime.parse(scanner.nextLine()));

        System.out.print("Nuevo motivo de consulta: ");
        dato.setMotivoConsulta(scanner.nextLine());

        dato.setEstado(pedirEstadoTurno());

        return dato;
    }

    public Long pedirIdTurno() {
        System.out.print("Ingrese el ID del turno: ");
        return Long.parseLong(scanner.nextLine());
    }

    public Long pedirIdPaciente() {
        System.out.print("Ingrese el ID del paciente: ");
        return Long.parseLong(scanner.nextLine());
    }

    public Long pedirIdOdontologo() {
        System.out.print("Ingrese el ID del odontologo: ");
        return Long.parseLong(scanner.nextLine());
    }

    public Long pedirIdSecretaria() {
        System.out.print("Ingrese el ID de la secretaria: ");
        return Long.parseLong(scanner.nextLine());
    }

    public EstadoTurno pedirEstadoTurno() {
        System.out.println("\nEstados disponibles:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. CONFIRMADO");
        System.out.println("3. CANCELADO");
        System.out.println("4. COMPLETADO");
        System.out.print("Seleccione un estado: ");

        int opcion = Integer.parseInt(scanner.nextLine());

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
                System.out.println("Opcion invalida. Se asignara PENDIENTE por defecto.");
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
}