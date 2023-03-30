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

    public void adicionar(Categoria categoria) throws ElementoJaExisteException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        if(categoria == null){
            throw new ArgumentoInvalidoException("A categoria não pode ser nula");
        }
        Categoria categoriaExistente = listarPorNome(categoria.getNome());
        if(categoriaExistente != null){
            throw new ElementoJaExisteException("Já existe uma categoria com o mesmo nome");
        }
        this.repositorio.adicionar(categoria);
    }

    public void remover(Categoria categoria) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {
        if(categoria == null){
            throw new ArgumentoInvalidoException("A categoria não pode ser nula");
        }
        Categoria categoriaExistente = listarPorNome(categoria.getNome());
        if(categoriaExistente == null){
            throw new ElementoNaoEncontradoException("Não foi possível encontrar a categoria");
        }
        this.repositorio.remover(categoriaExistente);
    }

    public List<Categoria> listarTodos() {
        return this.repositorio.listarTodos();
    }

    public Categoria listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        if(nome == null || nome.trim().isEmpty()){
            throw new ArgumentoInvalidoException("O nome da categoria não pode ser vazio");
        }
        Categoria categoria = this.repositorio.listarPorNome(nome);
        if(categoria == null){
            throw new ElementoNaoEncontradoException("Não foi possível encontrar a categoria");
        }
        return categoria;
    }
}
