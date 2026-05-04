package view;

import controller.TurnoController;
import entity.EstadoTurno;
import entity.Turno;

import java.util.Scanner;

public class VistaTurno {
    private Scanner scanner;
    private TurnoController turnoController;

    public VistaTurno(Scanner scanner, TurnoController turnoController) {
        this.scanner = scanner;
        this.turnoController = turnoController;
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n===== MENU TURNOS =====");
            System.out.println("1. Registrar turno");
            System.out.println("2. Listar turnos");
            System.out.println("3. Buscar turno por ID");
            System.out.println("4. Cambiar estado");
            System.out.println("5. Calcular monto");
            System.out.println("6. Volver");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                registrarTurno();
            } else if (opcion == 2) {
                turnoController.listar();
            } else if (opcion == 3) {
                buscarTurno();
            } else if (opcion == 4) {
                cambiarEstado();
            } else if (opcion == 5) {
                calcularMonto();
            } else if (opcion == 6) {
                System.out.println("Volviendo al menu principal...");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    private void registrarTurno() {
        DatoTurno dato = new DatoTurno();

        System.out.println("\n--- Registrar Turno ---");

        System.out.print("Ingrese ID del paciente: ");
        dato.setIdPaciente(scanner.nextLong());
        scanner.nextLine();

        System.out.print("Ingrese ID del odontologo: ");
        dato.setIdOdontologo(scanner.nextLong());
        scanner.nextLine();

        System.out.print("Ingrese nombre de la secretaria: ");
        dato.setNombreSecretaria(scanner.nextLine());

        System.out.print("Ingrese DNI de la secretaria: ");
        dato.setDniSecretaria(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Ingrese anio del turno: ");
        dato.setAnio(scanner.nextInt());

        System.out.print("Ingrese mes del turno: ");
        dato.setMes(scanner.nextInt());

        System.out.print("Ingrese dia del turno: ");
        dato.setDia(scanner.nextInt());

        System.out.print("Ingrese hora del turno: ");
        dato.setHora(scanner.nextInt());

        System.out.print("Ingrese minuto del turno: ");
        dato.setMinuto(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Ingrese motivo de consulta: ");
        dato.setMotivo(scanner.nextLine());

        Turno turno = turnoController.registrar(dato);

        if (turno != null) {
            System.out.println("Turno registrado correctamente.");
            System.out.println(turno);
        } else {
            System.out.println("No se pudo registrar el turno.");
        }
    }

    private void buscarTurno() {
        System.out.print("Ingrese ID del turno: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Turno turno = turnoController.buscarPorId(id);

        if (turno != null) {
            System.out.println(turno);
        } else {
            System.out.println("No se encontro un turno con ese ID.");
        }
    }

    private void cambiarEstado() {
        System.out.print("Ingrese ID del turno: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Seleccione nuevo estado:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. CONFIRMADO");
        System.out.println("3. CANCELADO");
        System.out.println("4. COMPLETADO");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        EstadoTurno estado = null;

        if (opcion == 1) {
            estado = EstadoTurno.PENDIENTE;
        } else if (opcion == 2) {
            estado = EstadoTurno.CONFIRMADO;
        } else if (opcion == 3) {
            estado = EstadoTurno.CANCELADO;
        } else if (opcion == 4) {
            estado = EstadoTurno.COMPLETADO;
        }

        if (estado != null) {
            turnoController.cambiarEstado(id, estado);
            System.out.println("Estado actualizado.");
        } else {
            System.out.println("Estado invalido.");
        }
    }

    private void calcularMonto() {
        System.out.print("Ingrese ID del turno: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        double monto = turnoController.calcularMonto(id);

        System.out.println("Monto a pagar: $" + monto);
    }
}
