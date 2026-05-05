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
        return leerOpcionMenu("Seleccione una opcion: ", 0, 6);
    }

    public DatoOdontologo pedirDatosOdontologo() {
        System.out.println("\n=== REGISTRO DE ODONTOLOGO ===");
        System.out.println("Tipo de especialidad:");
        System.out.println("1. Odontologia General");
        System.out.println("2. Ortodoncia");
        System.out.println("3. Endodoncia");

        int opcion = leerOpcionMenu("Seleccione una opcion: ", 1, 3);
        String nombre = leerString("Nombre: ");
        String apellido = leerString("Apellido: ");
        Integer dni = leerInteger("DNI: ");
        String matricula = leerString("Matricula: ");

        switch (opcion) {
            case 1:
                return new DatoOdontologoGeneral(null, nombre, apellido, dni, matricula);
            case 2:
                return new DatoOrtodoncista(null, nombre, apellido, dni, matricula);
            case 3:
                return new DatoEndodoncista(null, nombre, apellido, dni, matricula);
            default:
                return null;
        }
    }

    public DatoOdontologo pedirDatosOdontologoActualizado() {
        System.out.println("\n=== MODIFICACION DE ODONTOLOGO ===");

        Long id = leerLong("ID del odontologo: ");
        String nombre = leerString("Nuevo nombre: ");
        String apellido = leerString("Nuevo apellido: ");
        Integer dni = leerInteger("Nuevo DNI: ");
        String matricula = leerString("Nueva matricula: ");

        return new DatoOdontologoGeneral(id, nombre, apellido, dni, matricula);
    }

    public Long pedirIdOdontologo() {
        return leerLong("Ingrese el ID del odontologo: ");
    }

    public String pedirMatriculaOdontologo() {
        return leerString("Ingrese la matricula del odontologo: ");
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