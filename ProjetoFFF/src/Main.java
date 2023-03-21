import gui.TextualUserInterface;
import negocio.ControladorPomodoro;
import negocio.ControladorTasks;
import negocio.ControladorUsuarios;

public class Main {
    public static void main(String[] args) {
        ControladorTasks controladorTasks = new ControladorTasks();
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        ControladorPomodoro controladorPomodoro = new ControladorPomodoro();
        TextualUserInterface tui = new TextualUserInterface(controladorTasks, controladorUsuarios, controladorPomodoro);
        tui.exibirMenuPrincipal();
    }
}
