package dados;

import enums.Prioridades;
import enums.Status;
import exceptions.*;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements IRepository<Task> {
    static List<Task> listasDeTask = new ArrayList<>();

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
    public static void adicionar(Task item) throws ElementoJaExisteException, ArgumentoInvalidoException {
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
        if (item == null) {
            throw new ArgumentoInvalidoException(null);
        }
        if (item.getNome() == null || item.getNome().trim().isEmpty()) {
            throw new ArgumentoInvalidoException(item);
        }
        if (item.getConteudo() == null || item.getConteudo().trim().isEmpty()) {
            throw new ArgumentoInvalidoException(item);
        }
        if (item.getStatus() == null) {
            throw new ArgumentoInvalidoException(item);
        }
        if (item.getPrioridades() == null) {
            throw new ArgumentoInvalidoException(item);
        }
        int index = listasDeTask.indexOf(item);
        if (index == -1) {
            throw new ElementoNaoEncontradoException(item);
        }
        Task taskAntiga = listasDeTask.get(index);
        taskAntiga.setNome(item.getNome());
        taskAntiga.setConteudo(item.getConteudo());
        taskAntiga.setStatus(item.getStatus());
        taskAntiga.setPrioridades(item.getPrioridades());
    }

    @Override
    public void remover(Task item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {
        if (item == null) {
            throw new ArgumentoInvalidoException(null);
        }
        int index = listasDeTask.indexOf(item);
        if (index == -1) {
            throw new ElementoNaoEncontradoException(item);
        }
        if (!listasDeTask.remove(item)) {
            throw new DeletarFalhouException(item);
        }
    }

    public List<Task> listarPorStatus(Status status) throws ElementoNaoEncontradoException {
        List<Task> tasksComStatus = new ArrayList<Task>();

        for(Task task : listasDeTask){
            if(task.getStatus() == status){
                tasksComStatus.add(task);
            }
        }

        if(tasksComStatus.isEmpty()){
            throw new ElementoNaoEncontradoException("Nenhuma tarefa encontrada com o status " + status);
        }

        return tasksComStatus;
    }

    public List<Task> listarPorPrioridade(Prioridades prioridade) throws ElementoNaoEncontradoException {
        List<Task> tasksComPrioridade = new ArrayList<Task>();

        for(Task task : listasDeTask){
            if(task.getPrioridades() == prioridade){
                tasksComPrioridade.add(task);
            }
        }

        if(tasksComPrioridade.isEmpty()){
            throw new ElementoNaoEncontradoException("Nenhuma tarefa encontrada com a prioridade " + prioridade);
        }

        return tasksComPrioridade;
    }

    public List<Task> listarPorCor(String cor) throws ElementoNaoEncontradoException {
        List<Task> tasksComCor = new ArrayList<Task>();

        for(Task task : listasDeTask){
            if(task.getCor().equals(cor)){
                tasksComCor.add(task);
            }
        }

        if(tasksComCor.isEmpty()){
            throw new ElementoNaoEncontradoException("Nenhuma tarefa encontrada com a cor " + cor);
        }

        return tasksComCor;
    }

    public List<Task> listarPorUsuario(String usuario) throws ElementoNaoEncontradoException {
        List<Task> tasksDoUsuario = new ArrayList<Task>();

        for(Task task : listasDeTask){
            if(task.getUsuario().equals(usuario)){
                tasksDoUsuario.add(task);
            }
        }

        if(tasksDoUsuario.isEmpty()){
            throw new ElementoNaoEncontradoException("Nenhuma tarefa encontrada para o usuário " + usuario);
        }

        return tasksDoUsuario;
    }


    public void gerarRelatorioPorMes(Month){ }

    public void  salvarTarefas(){ }

    public void carregarTarefas(){ }
}

