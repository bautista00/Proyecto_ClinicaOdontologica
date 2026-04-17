


import modelo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

   public static void main(String[] args) {
        Domicilio domicilio = new Domicilio("Rivadavia", 1234, "Castelar", "Buenos Aires");

        Domicilio domicilio1 = new Domicilio("Julian Alvarez",2233,"Palermo","Ciudad de Buenos Aires");

        Paciente paciente = new Paciente(
                "Bautista",
                "Quesada",
                54369492,
                "bautistaquesada@gmail.com",
                domicilio,
                true);


        Paciente paciente1 = new Paciente(
                "Lucas",
                "Gonzalez",
                43564789,
                "lucasglz@gmail.com",
                domicilio1,
                false
        );

        Odontologo odontologo = new Odontologo(
                "Ana",
                "Gomez",
                "MAT-12345"
        );

        Odontologo odontologo1 = new Odontologo(
                "Elisabet",
                "Perez",
                "MAT-45567"
        );

        Secretaria secretaria = new Secretaria(
                "Lucia",
                "Fernandez",
                30111222
        );

        Secretaria secretaria1 = new Secretaria(
                "Juana",
                "Loza",
                45131252
        );
        Turno turno = secretaria.registrarTurno(
                paciente,
                odontologo,
                LocalDate.of(2026, 4, 20),
                LocalTime.of(10, 30)
        );

        Turno turno1 = secretaria1.registrarTurno(
                paciente1,
                odontologo1,
                LocalDate.of(2026, 3, 27),
                LocalTime.of(9,45)
        );

        turno1.setEstado(EstadoTurno.COMPLETADO);

        Turno turno2 = secretaria.registrarTurno(
                paciente1,
                odontologo1,
                LocalDate.of(2026,5,30),
                LocalTime.of(10,45)
        );

        Turno turno3 = secretaria.registrarTurno(
                paciente1,
                odontologo1,
                LocalDate.of(2026,6,2),
                LocalTime.of(15,30)
        );

        System.out.println("\n====== RECORDATORIOS =========");

        System.out.println(turno.generarMensajeRecordatorio());
        System.out.println(turno1.generarMensajeRecordatorio());
        System.out.println(turno2.generarMensajeRecordatorio());
        System.out.println(turno3.generarMensajeRecordatorio());

       System.out.println("\n====== TURNOS DEL PACIENTE BAUTISTA =========");
       for (int i = 0; i < paciente.getHistorialPaciente().size(); i++) {
           Turno t = paciente.getHistorialPaciente().get(i);
           System.out.println(t.generarMensajeRecordatorio());
       }

       System.out.println("\n====== TURNOS DEL PACIENTE LUCAS =========");
       for (int i = 0; i < paciente1.getHistorialPaciente().size(); i++) {
           Turno t = paciente1.getHistorialPaciente().get(i);
           System.out.println(t.generarMensajeRecordatorio());
       }

       System.out.println("\n====== TURNOS REGISTRADOS POR LUCIA =========");
       for (int i = 0; i < secretaria.getHistorialSecretaria().size(); i++) {
           Turno t = secretaria.getHistorialSecretaria().get(i);
           System.out.println(t.generarMensajeRecordatorio());
       }

       System.out.println("\n====== TURNOS REGISTRADOS POR JUANA =========");
       for (int i = 0; i < secretaria1.getHistorialSecretaria().size(); i++) {
           Turno t = secretaria1.getHistorialSecretaria().get(i);
           System.out.println(t.generarMensajeRecordatorio());
       }

       System.out.println("\n====== TURNOS DEL ODONTOLOGO ANA =========");
       for (int i = 0; i < odontologo.getHistorialOdontologo().size(); i++) {
           Turno t = odontologo.getHistorialOdontologo().get(i);
           System.out.println(t.generarMensajeRecordatorio());
       }

       System.out.println("\n====== TURNOS DEL ODONTOLOGO ELISABET =========");
       for (int i = 0; i < odontologo1.getHistorialOdontologo().size(); i++) {
           Turno t = odontologo1.getHistorialOdontologo().get(i);
           System.out.println(t.generarMensajeRecordatorio());
       }


    }
}