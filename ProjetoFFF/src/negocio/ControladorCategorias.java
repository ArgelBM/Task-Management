package negocio;

import dados.CategoriasRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Categoria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ControladorCategorias {
    private static ControladorCategorias instance;

    private CategoriasRepository repositorio;

    public ControladorCategorias(){
        this.repositorio = new CategoriasRepository("Controlador" + ".dat");
    }

    public static ControladorCategorias getInstance(){
        if(instance == null){
            instance = new ControladorCategorias();
        }
        return instance;
    }

    public static void adicionar(Categoria obj) throws ElementoJaExisteException, ArgumentoInvalidoException {

    }

    public static void remover(Categoria obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    public static List<Categoria> listarPorTodos() {
    return null;
    }

    public static Categoria listarPornome(String nome)  throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }
}
