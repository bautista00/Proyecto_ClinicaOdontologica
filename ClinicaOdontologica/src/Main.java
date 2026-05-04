import controller.SistemaController;
import view.VistaPrincipal;

public class Main {
    public static void main(String[] args) {

        VistaPrincipal vista = new VistaPrincipal();
        SistemaController controller = new SistemaController(vista);
        controller.run();
    }
}
