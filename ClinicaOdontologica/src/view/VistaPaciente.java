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
        return leerOpcionMenu("Seleccione una opcion: ", 0, 6);
    }

    public DatoPaciente pedirDatosPaciente() {
        DatoPaciente dato = new DatoPaciente();

        System.out.println("\n=== REGISTRO DE PACIENTE ===");
        dato.setNombre(leerString("Nombre: "));
        dato.setApellido(leerString("Apellido: "));
        dato.setDni(leerInteger("DNI: "));
        dato.setEmail(leerString("Email: "));
        dato.setCalle(leerString("Calle: "));
        dato.setNumero(leerInteger("Numero: "));
        dato.setLocalidad(leerString("Localidad: "));
        dato.setProvincia(leerString("Provincia: "));
        dato.setObraSocial(leerBoolean("Tiene obra social"));

        return dato;
    }

    public DatoPaciente pedirDatosPacienteActualizado() {
        DatoPaciente dato = new DatoPaciente();

        System.out.println("\n=== MODIFICACION DE PACIENTE ===");
        dato.setId(leerLong("ID del paciente: "));
        dato.setNombre(leerString("Nuevo nombre: "));
        dato.setApellido(leerString("Nuevo apellido: "));
        dato.setDni(leerInteger("Nuevo DNI: "));
        dato.setEmail(leerString("Nuevo email: "));
        dato.setCalle(leerString("Nueva calle: "));
        dato.setNumero(leerInteger("Nuevo numero: "));
        dato.setLocalidad(leerString("Nueva localidad: "));
        dato.setProvincia(leerString("Nueva provincia: "));
        dato.setObraSocial(leerBoolean("Tiene obra social"));

        return dato;
    }

    public Long pedirIdPaciente() {
        return leerLong("Ingrese el ID del paciente: ");
    }

    public Integer pedirDniPaciente() {
        return leerInteger("Ingrese el DNI del paciente: ");
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

    private Boolean leerBoolean(String mensaje) {
        String respuesta;

        do {
            System.out.print(mensaje + " (s/n): ");
            respuesta = scanner.nextLine().trim().toLowerCase();

            if (!respuesta.equals("s") && !respuesta.equals("n")) {
                System.out.println("Error: responda con 's' o 'n'.");
            }
        } while (!respuesta.equals("s") && !respuesta.equals("n"));

        return respuesta.equals("s");
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