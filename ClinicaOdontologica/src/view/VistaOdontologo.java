package view;

import entity.Odontologo;

import java.util.List;
import java.util.Scanner;

public class VistaOdontologo {

    private Scanner scanner;

    public VistaOdontologo() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== MENU ODONTOLOGOS ===");
        System.out.println("1. Registrar odontologo");
        System.out.println("2. Buscar odontologo por ID");
        System.out.println("3. Buscar odontologo por matricula");
        System.out.println("4. Listar odontologos");
        System.out.println("5. Modificar odontologo");
        System.out.println("6. Eliminar odontologo");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opcion: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public DatoOdontologo pedirDatosOdontologo() {
        System.out.println("\n=== REGISTRO DE ODONTOLOGO ===");
        System.out.println("Tipo de especialidad:");
        System.out.println("1. Odontologia General");
        System.out.println("2. Ortodoncia");
        System.out.println("3. Endodoncia");
        System.out.print("Seleccione una opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("DNI: ");
        Integer dni = Integer.parseInt(scanner.nextLine());

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        switch (opcion) {
            case 1:
                return new DatoOdontologoGeneral(null, nombre, apellido, dni, matricula);
            case 2:
                return new DatoOrtodoncista(null, nombre, apellido, dni, matricula);
            case 3:
                return new DatoEndodoncista(null, nombre, apellido, dni, matricula);
            default:
                System.out.println("Opcion invalida.");
                return null;
        }
    }

    public DatoOdontologo pedirDatosOdontologoActualizado() {
        System.out.println("\n=== MODIFICACION DE ODONTOLOGO ===");

        System.out.print("ID del odontologo: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo DNI: ");
        Integer dni = Integer.parseInt(scanner.nextLine());

        System.out.print("Nueva matricula: ");
        String matricula = scanner.nextLine();

        System.out.println("Tipo de especialidad:");
        System.out.println("1. Odontologia General");
        System.out.println("2. Ortodoncia");
        System.out.println("3. Endodoncia");
        System.out.print("Seleccione una opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                return new DatoOdontologoGeneral(id, nombre, apellido, dni, matricula);
            case 2:
                return new DatoOrtodoncista(id, nombre, apellido, dni, matricula);
            case 3:
                return new DatoEndodoncista(id, nombre, apellido, dni, matricula);
            default:
                System.out.println("Opcion invalida.");
                return null;
        }
    }

    public Long pedirIdOdontologo() {
        System.out.print("Ingrese el ID del odontologo: ");
        return Long.parseLong(scanner.nextLine());
    }

    public String pedirMatriculaOdontologo() {
        System.out.print("Ingrese la matricula del odontologo: ");
        return scanner.nextLine();
    }

    public void mostrarOdontologo(Odontologo odontologo) {
        if (odontologo == null) {
            System.out.println("No se encontro el odontologo.");
            return;
        }

        System.out.println(odontologo);
    }

    public void mostrarOdontologos(List<Odontologo> odontologos) {
        if (odontologos == null || odontologos.isEmpty()) {
            System.out.println("No hay odontologos registrados.");
            return;
        }

        System.out.println("\n=== LISTADO DE ODONTOLOGOS ===");
        for (Odontologo odontologo : odontologos) {
            System.out.println(odontologo);
            System.out.println("-----------------------------------");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}