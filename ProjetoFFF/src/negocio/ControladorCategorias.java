package negocio;

import negocio.beans.Categoria;

import java.util.List;

public class ControladorCategorias {
    private static ControladorCategorias instance;

    public static ControladorCategorias getInstance(){
        if(instance == null){
            instance = new ControladorCategorias();
        }
        return instance;
    }

    public static void adicionar(Categoria obj) {
    }

    public static void remover(Categoria obj) {
    }

    public static List<Categoria> listarPorTodos() {
    }

    public static List<Categoria> listarPornome(String nome) {
    }
}
