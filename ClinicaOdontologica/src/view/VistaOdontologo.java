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
        DatoOdontologo dato = new DatoOdontologo();

        System.out.println("\n=== REGISTRO DE ODONTOLOGO ===");

        System.out.print("Tipo de especialidad (general / ortodoncia / endodoncia): ");
        dato.setTipoEspecialidad(scanner.nextLine());

        System.out.print("Nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("DNI: ");
        dato.setDni(Integer.parseInt(scanner.nextLine()));

        System.out.print("Matricula: ");
        dato.setMatricula(scanner.nextLine());

        return dato;
    }

    public DatoOdontologo pedirDatosOdontologoActualizado() {
        DatoOdontologo dato = new DatoOdontologo();

        System.out.println("\n=== MODIFICACION DE ODONTOLOGO ===");

        System.out.print("ID del odontologo: ");
        dato.setId(Long.parseLong(scanner.nextLine()));

        System.out.print("Nuevo nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Nuevo apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("Nuevo DNI: ");
        dato.setDni(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nueva matricula: ");
        dato.setMatricula(scanner.nextLine());

        return dato;
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