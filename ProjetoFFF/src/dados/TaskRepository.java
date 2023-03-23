package dados;

import enums.Filtro;
import enums.Prioridades;
import enums.Status;
import exceptions.*;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class TaskRepository implements IRepository<Task> {

    private static final String FIELD_SEPARATOR = ";";
    private static final String FILE_EXTENSION = ".txt";
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


    public void salvarTarefas(List<Task> tasks, String nomeArquivo) throws IOException {
        String nomeCompletoArquivo = nomeArquivo.endsWith(FILE_EXTENSION) ? nomeArquivo : nomeArquivo + FILE_EXTENSION;
        String caminho = Paths.get(System.getProperty("user.home"), "Desktop", nomeCompletoArquivo).toString();
        FileWriter fileWriter = new FileWriter(caminho);
        for (Task task : tasks) {
            fileWriter.write(task.getNome() + FIELD_SEPARATOR
                    + task.getConteudo() + FIELD_SEPARATOR
                    + task.getStatus() + FIELD_SEPARATOR
                    + task.getPrioridades() + FIELD_SEPARATOR
                    + task.getCor() + "\n");
        }
        fileWriter.close();
    }
    public List<Task> carregarTarefas(String nomeArquivo) throws IOException {
        String nomeCompletoArquivo = nomeArquivo.endsWith(FILE_EXTENSION) ? nomeArquivo : nomeArquivo + FILE_EXTENSION;
        String caminho = Paths.get(System.getProperty("user.home"), "Desktop", nomeCompletoArquivo).toString();
        List<Task> tasks = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] campos = linha.split(FIELD_SEPARATOR);
            Status status = Status.valueOf(campos[2]);
            Prioridades prioridades = Prioridades.valueOf(campos[5]);

            Task task = new Task(campos[0], campos[1], status, LocalDate.parse(campos[3]),
                    LocalDate.parse(campos[4]), prioridades, new ArrayList<>(), campos[7]);
            tasks.add(task);
        }
        bufferedReader.close();
        return tasks;
    }

    public void marcaComoConcluida (Task task){
        for(Task a : listasDeTask){
            if(task == a){
                a.setDataConclusao(LocalDate.now());
                a.setStatus(Status.CONCLUIDA);
            }
        }
    }
}


