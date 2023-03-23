package dados;

import enums.Filtro;
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

public class TaskRepository implements IRepository<Task> {

    private static final String FILE_NAME = "tasks.txt";
    private static final String FIELD_SEPARATOR = ";";
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
        return null;
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

    public List<Task> listarPor(Filtro filtro, Object valor) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        List<Task> tasksFiltradas = new ArrayList<>();

        for(Task task : listasDeTask) {
            switch(filtro) {
                case STATUS:
                    if(task.getStatus() == (Status) valor) {
                        tasksFiltradas.add(task);
                    }
                    break;
                case PRIORIDADE:
                    if(task.getPrioridades() == (Prioridades) valor) {
                        tasksFiltradas.add(task);
                    }
                    break;
                case COR:
                    if(task.getCor().equals((String) valor)) {
                        tasksFiltradas.add(task);
                    }
                    break;
                case USUARIO:
                    if(task.getUsuario().equals((String) valor)) {
                        tasksFiltradas.add(task);
                    }
                    break;
                default:
                    throw new ArgumentoInvalidoException(valor);
            }
        }

        if(tasksFiltradas.isEmpty()){
            throw new ElementoNaoEncontradoException(valor);
        }

        return tasksFiltradas;
    }


    public List<Task> gerarRelatorioPorMes(Month mes) throws ElementoNaoEncontradoException {
        List<Task> tarefasConcluidasNoMes = new ArrayList<>();

        for (Task task : listasDeTask) {
            if (task.getStatus() == Status.CONCLUIDA && task.getDataConclusao().getMonth() == mes) {
                tarefasConcluidasNoMes.add(task);
            }
        }

        if (tarefasConcluidasNoMes.isEmpty()) {
            throw new ElementoNaoEncontradoException(mes);
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


