package dados;

import exceptions.*;
import negocio.beans.Categoria;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

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

    }

    @Override
    public void atualizar(Object item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

        try{
            categorias.add((Categoria) item);

        }
        catch (InputMismatchException a){

            System.out.println("ERROR : " +  a.getMessage());

        }
    }

    @Override
    public void remover(Object item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

        if(item == null){
            System.out.println("item vazio");

        }
        else {
            try {
                categorias.remove((Categoria) item);

            } catch (InputMismatchException a) {

                System.out.println("ERROR : " + a.getMessage());

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
