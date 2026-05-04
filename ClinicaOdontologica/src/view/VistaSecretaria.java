package view;

import entity.Secretaria;

import java.util.List;
import java.util.Scanner;

public class VistaSecretaria {

    private Scanner scanner;

    public VistaSecretaria() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== MENU SECRETARIAS ===");
        System.out.println("1. Registrar secretaria");
        System.out.println("2. Buscar secretaria por ID");
        System.out.println("3. Buscar secretaria por DNI");
        System.out.println("4. Listar secretarias");
        System.out.println("5. Modificar secretaria");
        System.out.println("6. Eliminar secretaria");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opcion: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public DatoSecretaria pedirDatosSecretaria() {
        DatoSecretaria dato = new DatoSecretaria();

        System.out.println("\n=== REGISTRO DE SECRETARIA ===");

        System.out.print("Nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("DNI: ");
        dato.setDni(Integer.parseInt(scanner.nextLine()));

        return dato;
    }

    public DatoSecretaria pedirDatosSecretariaActualizada() {
        DatoSecretaria dato = new DatoSecretaria();

        System.out.println("\n=== MODIFICACION DE SECRETARIA ===");

        System.out.print("ID de la secretaria: ");
        dato.setId(Long.parseLong(scanner.nextLine()));

        System.out.print("Nuevo nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Nuevo apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("Nuevo DNI: ");
        dato.setDni(Integer.parseInt(scanner.nextLine()));

        return dato;
    }

    public Long pedirIdSecretaria() {
        System.out.print("Ingrese el ID de la secretaria: ");
        return Long.parseLong(scanner.nextLine());
    }

    public Integer pedirDniSecretaria() {
        System.out.print("Ingrese el DNI de la secretaria: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarSecretaria(Secretaria secretaria) {
        if (secretaria == null) {
            System.out.println("No se encontro la secretaria.");
            return;
        }

        System.out.println(secretaria);
    }

    public void mostrarSecretarias(List<Secretaria> secretarias) {
        if (secretarias == null || secretarias.isEmpty()) {
            System.out.println("No hay secretarias registradas.");
            return;
        }

        System.out.println("\n=== LISTADO DE SECRETARIAS ===");
        for (Secretaria secretaria : secretarias) {
            System.out.println(secretaria);
            System.out.println("-----------------------------------");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}