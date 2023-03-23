package negocio;

import dados.CategoriasRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
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

    public static void adicionar(Categoria obj) throws ElementoJaExisteException, ArgumentoInvalidoException {
        CategoriasRepository repo = new CategoriasRepository();
        repo.adicionar(obj);
    }

    public static void remover(Categoria obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {
        CategoriasRepository repo = new CategoriasRepository();
        repo.remover(obj);
    }

    public static List<Categoria> listarPorTodos() {
        CategoriasRepository repo = new CategoriasRepository();
        return repo.listarTodos();
    }

    public static Categoria listarPornome(String nome)  throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        CategoriasRepository repo = new CategoriasRepository();
        return repo.listarPorNome(nome);
    }
}
