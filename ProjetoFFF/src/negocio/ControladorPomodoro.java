package negocio;

import negocio.beans.Pomodoro;
import negocio.beans.Task;

import java.util.List;

public class ControladorPomodoro {
    private static ControladorPomodoro instance;

    public static ControladorPomodoro getInstance(){
        if(instance == null){
            instance = new ControladorPomodoro();
        }
        return instance;
    }

    public static void adicionar(Pomodoro obj) {
    }

    public static void remover(Pomodoro obj) {
    }

    public static List<Pomodoro> listarPorTarefa(Task obj) {
    }

    public static Pomodoro iniciar() {
    }

    public static Pomodoro parar() {
    }

    public static Pomodoro alerta() {
    }
}
