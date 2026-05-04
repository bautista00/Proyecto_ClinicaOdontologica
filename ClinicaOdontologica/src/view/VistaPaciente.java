package view;

import entity.Paciente;

import java.util.List;
import java.util.Scanner;

public class VistaPaciente {

    private Scanner scanner;

    public VistaPaciente() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== MENU PACIENTES ===");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Buscar paciente por ID");
        System.out.println("3. Buscar paciente por DNI");
        System.out.println("4. Listar pacientes");
        System.out.println("5. Modificar paciente");
        System.out.println("6. Eliminar paciente");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opcion: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public DatoPaciente pedirDatosPaciente() {
        DatoPaciente dato = new DatoPaciente();

        System.out.println("\n=== REGISTRO DE PACIENTE ===");
        System.out.print("Nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("DNI: ");
        dato.setDni(Integer.parseInt(scanner.nextLine()));

        System.out.print("Email: ");
        dato.setEmail(scanner.nextLine());

        System.out.print("Calle: ");
        dato.setCalle(scanner.nextLine());

        System.out.print("Numero: ");
        dato.setNumero(Integer.parseInt(scanner.nextLine()));

        System.out.print("Localidad: ");
        dato.setLocalidad(scanner.nextLine());

        System.out.print("Provincia: ");
        dato.setProvincia(scanner.nextLine());

        System.out.print("Tiene obra social? (true/false): ");
        dato.setObraSocial(Boolean.parseBoolean(scanner.nextLine()));

        return dato;
    }

    public DatoPaciente pedirDatosPacienteActualizado() {
        DatoPaciente dato = new DatoPaciente();

        System.out.println("\n=== MODIFICACION DE PACIENTE ===");

        System.out.print("ID del paciente: ");
        dato.setId(Long.parseLong(scanner.nextLine()));

        System.out.print("Nuevo nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Nuevo apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("Nuevo DNI: ");
        dato.setDni(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nuevo email: ");
        dato.setEmail(scanner.nextLine());

        System.out.print("Nueva calle: ");
        dato.setCalle(scanner.nextLine());

        System.out.print("Nuevo numero: ");
        dato.setNumero(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nueva localidad: ");
        dato.setLocalidad(scanner.nextLine());

        System.out.print("Nueva provincia: ");
        dato.setProvincia(scanner.nextLine());

        System.out.print("Tiene obra social? (true/false): ");
        dato.setObraSocial(Boolean.parseBoolean(scanner.nextLine()));

        return dato;
    }

    public Long pedirIdPaciente() {
        System.out.print("Ingrese el ID del paciente: ");
        return Long.parseLong(scanner.nextLine());
    }

    public Integer pedirDniPaciente() {
        System.out.print("Ingrese el DNI del paciente: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("No se encontro el paciente.");
            return;
        }

        System.out.println(paciente);
    }

    public void mostrarPacientes(List<Paciente> pacientes) {
        if (pacientes == null || pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        System.out.println("\n=== LISTADO DE PACIENTES ===");
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
            System.out.println("-----------------------------------");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}