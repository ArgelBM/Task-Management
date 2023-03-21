package negocio;

import negocio.beans.Task;
import negocio.beans.Usuario;

import java.util.List;

public class ControladorUsuarios {
    private static ControladorUsuarios instance;

    public static ControladorUsuarios getInstance(){
        if(instance == null){
            instance = new ControladorUsuarios();
        }
        return instance;
    }

    public static void remover(Usuario obj) {
    }

    public static void adicionar(Usuario obj) {
    }

    public static List<Usuario> listarPorTask(Task nome) {
    }

    public static List<Usuario> listarPorTodos() {
    }

    public static List<Usuario> listarPorId(int id) {
    }

    public static List<Usuario> listarPornome(String nome) {
    }
}
