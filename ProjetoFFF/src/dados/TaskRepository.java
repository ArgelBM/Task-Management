package dados;

import enums.Prioridades;
import enums.Status;
import exceptions.*;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements IRepository<Task> {

    private static final String FILE_NAME = "tasks.txt";
    private static final String FIELD_SEPARATOR = ";";
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
    public void adicionar(Task item) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if (item == null){
            throw new ArgumentoInvalidoException(null);
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
    public void atualizar(Task item) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
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
        List<Task> tasksComStatus = new ArrayList<>();

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
        List<Task> tasksComPrioridade = new ArrayList<>();

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
        List<Task> tasksComCor = new ArrayList<>();

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
        List<Task> tasksDoUsuario = new ArrayList<>();

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


    public List<Task> gerarRelatorioPorMes(Month mes) throws ElementoNaoEncontradoException {
        List<Task> tarefasConcluidasNoMes = new ArrayList<>();

        for (Task task : listasDeTask) {
            if (task.getStatus() == Status.CONCLUIDA && task.getDataConclusao().getMonth() == mes) {
                tarefasConcluidasNoMes.add(task);
            }
        }

        if (tarefasConcluidasNoMes.isEmpty()) {
            throw new ElementoNaoEncontradoException("Nenhuma tarefa concluída encontrada no mês " + mes);
        }

        return tarefasConcluidasNoMes;
    }


    public void salvarTarefas(List<Task> tasks) throws IOException {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            for (Task task : tasks) {
                fileWriter.write(task.getNome() + FIELD_SEPARATOR
                        + task.getConteudo() + FIELD_SEPARATOR
                        + task.getStatus() + FIELD_SEPARATOR
                        + task.getPrioridades() + FIELD_SEPARATOR
                        + task.getCor() + FIELD_SEPARATOR
                        + task.getUsuario() + "\n");
            }
            fileWriter.close();
        }
    public List<Task> carregarTarefas() throws IOException {
        List<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line = reader.readLine();
        while (line != null) {
            String[] fields = line.split(FIELD_SEPARATOR);
            String nome = fields[0];
            String conteudo = fields[1];
            Status status = Status.valueOf(fields[2]);
            Prioridades prioridade = Prioridades.valueOf(fields[3]);
            String cor = fields[4];
            Usuario usuario = Usuario.valueOf(fields[5]);
            Task task = new Task(nome, conteudo, status, null,null,prioridade,null, cor,usuario);
            tasks.add(task);
            line = reader.readLine();
        }
        reader.close();
        return tasks;
    }

}


