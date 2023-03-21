package dados;

import exceptions.*;
import negocio.beans.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements IRepository<Task> {
    List<Task> listasDeTask = new ArrayList<>();

    @Override
    public List<Task> listarTodos() {
        return listasDeTask;
    }

    @Override
    public Task listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Object listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        if (nome == null || nome.isEmpty()) {
            throw new ArgumentoInvalidoException(nome);
        }

        for (Task task : listasDeTask) {
            if (task.getNome().equals(nome)) {
                return task;
            }
        }

        throw new ElementoNaoEncontradoException(nome);
    }


    @Override
    public void adicionar(Task item) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if (item == null){
            throw new ArgumentoInvalidoException(item);
        }
        if(item.getNome() == null || item.getNome().trim().isEmpty()){
            throw new ArgumentoInvalidoException(item);
        }
        if(item.getConteudo() == null || item.getConteudo().trim().isEmpty()){
            throw new ArgumentoInvalidoException(item);
        }
        if(item.getStatus() == null){
            throw new ArgumentoInvalidoException(item);
        }
        if(item.getPrioridades() == null){
            throw new ArgumentoInvalidoException(item);
        }
        if (listasDeTask.contains(item)) {
            throw new ElementoJaExisteException(item);
        }
        listasDeTask.add(item);
    }

    @Override
    public void atualizar(Task item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Task item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }
}

