package dados;

import exceptions.*;

import java.util.List;

public class TaskRepository implements IRepository {
    @Override
    public List listarTodos() {
        return null;
    }

    @Override
    public Object listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Object listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public void adicionar(Object a) throws ElementoJaExisteException, ArgumentoInvalidoException {

    }

    @Override
    public void atualizar(Object item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Object a) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }
}

