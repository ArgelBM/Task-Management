package dados;

import enums.Filtro;
import enums.Prioridade;
import enums.Status;
import exceptions.*;
import negocio.beans.Task;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements IRepository<Task> {

    private List<Task> listasDeTask;

    private String fileName;

    public TaskRepository(String fileName){
        this.listasDeTask = new ArrayList<>();
        this.fileName = fileName;

        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
        if (listaElementos != null && listaElementos instanceof List<?>){
            this.listasDeTask = (List<Task>) listaElementos;
        }

    }

    @Override
    public List<Task> listarTodos() {
        return listasDeTask;
    }

    @Override
    public Task listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public List<Task> listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public void adicionar(Task item) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if ((item == null)){
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

        RepositorioFileUtil.salvarArquivo(listasDeTask, this.fileName);
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

        RepositorioFileUtil.salvarArquivo(listasDeTask, this.fileName);
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
        RepositorioFileUtil.salvarArquivo(listasDeTask, this.fileName);

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
                    if(task.getPrioridades() == (Prioridade) valor) {
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


    public void marcaComoConcluida (Task task){
        for(Task a : listasDeTask){
            if(task == a){
                a.setDataConclusao(LocalDate.now());
                a.setStatus(Status.CONCLUIDA);
            }
        }
    }
}


