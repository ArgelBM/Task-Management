package dados;

import exceptions.*;
import negocio.beans.Categoria;

import java.util.*;

public class CategoriasRepository implements IRepository {

    List<Categoria> categorias = new ArrayList<>();

    @Override
    public List listarTodos() {
        return categorias;
    }

    @Override
    public Object listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Object listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        Categoria b = null;
        for( Categoria a: categorias) {
            if (a.getNome().equals(nome)) {

                b = a;

            }
        }
        return b;
    }

    @Override
    public void adicionar(Object item) throws ElementoJaExisteException, ArgumentoInvalidoException {

        if(item == null){
            throw new ArgumentoInvalidoException("Item vazio");
        }
        else {
            try {
                categorias.add((Categoria) item);
            } catch (InputMismatchException e) {
                throw new ArgumentoInvalidoException("Falha ao deletar item: " + e.getMessage());
            } catch (NoSuchElementException e) {
                throw new ArgumentoInvalidoException("Elemento não encontrado: " + e.getMessage());
            }
        }

    }

    @Override
    public void atualizar(Object item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Object item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

        if(item == null){
            throw new ArgumentoInvalidoException("Item vazio");
        }
        else {
            try {
                categorias.remove((Categoria) item);
            } catch (InputMismatchException e) {
                throw new DeletarFalhouException("Falha ao deletar item: " + e.getMessage());
            } catch (NoSuchElementException e) {
                throw new ElementoNaoEncontradoException("Elemento não encontrado: " + e.getMessage());
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriasRepository that)) return false;
        return Objects.equals(categorias, that.categorias);
    }


}
