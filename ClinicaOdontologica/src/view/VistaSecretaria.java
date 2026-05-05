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
        return leerOpcionMenu("Seleccione una opcion: ", 0, 6);
    }

    public DatoSecretaria pedirDatosSecretaria() {
        DatoSecretaria dato = new DatoSecretaria();

        System.out.println("\n=== REGISTRO DE SECRETARIA ===");
        dato.setNombre(leerString("Nombre: "));
        dato.setApellido(leerString("Apellido: "));
        dato.setDni(leerInteger("DNI: "));

        return dato;
    }

    public DatoSecretaria pedirDatosSecretariaActualizada() {
        DatoSecretaria dato = new DatoSecretaria();

        System.out.println("\n=== MODIFICACION DE SECRETARIA ===");
        dato.setId(leerLong("ID de la secretaria: "));
        dato.setNombre(leerString("Nuevo nombre: "));
        dato.setApellido(leerString("Nuevo apellido: "));
        dato.setDni(leerInteger("Nuevo DNI: "));

        return dato;
    }

    public Long pedirIdSecretaria() {
        return leerLong("Ingrese el ID de la secretaria: ");
    }

    public Integer pedirDniSecretaria() {
        return leerInteger("Ingrese el DNI de la secretaria: ");
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