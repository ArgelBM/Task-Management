package dados;

import exceptions.*;
import negocio.beans.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements IRepository {

    private List<Task> ListasDeTask = new ArrayList<>();
    @Override
    public List listarTodos() {
        return ListasDeTask;
    }

    @Override
    public Object listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Task listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        if (nome == null || nome.isEmpty()) {
            throw new ArgumentoInvalidoException(nome);
        }

        for (Task task : ListasDeTask) {
            if (task.getNome().equals(nome)) {
                return task;
            }
        }

        throw new ElementoNaoEncontradoException(nome);
    }
}

    @Override
    public void adicionar(Object a) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if (a instanceof Task) {
            Task task = (Task) a;

            if (ListasDeTask.stream().anyMatch(t -> t.getNome().equals(task.getNome()))) {
                throw new ElementoJaExisteException(task);
            }

            ListasDeTask.add(task);
        } else {
            throw new ArgumentoInvalidoException(a);
        }
    }

    @Override
    public void atualizar(Object item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Object a) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }
}

