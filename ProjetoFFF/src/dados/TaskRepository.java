package dados;

import enums.Filtro;
import enums.Prioridade;
import enums.Status;
import exceptions.*;
import negocio.ControladorUsuarios;
import negocio.beans.Task;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements IRepository<Task>, Serializable {

    private List<Task> listasDeTask;

//    private String fileName;

    //public TaskRepository(String fileName){
    public TaskRepository(){
        this.listasDeTask = new ArrayList<>();
////        this.fileName = fileName;
////
////        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
////        if (listaElementos != null && listaElementos instanceof List<?>){
////            this.listasDeTask = (List<Task>) listaElementos;
//        }

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
//        if(item.getStatus() == null){
//            throw new ArgumentoInvalidoException(item);
//        }
//        if(item.getPrioridades() == null){
//            throw new ArgumentoInvalidoException(item);
//        }
        for(Task a: listasDeTask){
            if(a.getNome().equals(item.getNome())){
                throw new ArgumentoInvalidoException(item);
            }
        }
        listasDeTask.add(item);


        //ISSO PODE DAR RUIM !!!
        RepositorioFileUtil.salvarArquivo(ControladorUsuarios.getInstance().getRepositorio().getUsuarios(), ControladorUsuarios.getInstance().getRepositorio().getFileName());

//        RepositorioFileUtil.salvarArquivo(listasDeTask, this.fileName);
    }

    @Override
    public void atualizar(Task item) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
//        if (item == null) {
//            throw new ArgumentoInvalidoException(null);
//        }
//        if (item.getNome() == null || item.getNome().trim().isEmpty()) {
//            throw new ArgumentoInvalidoException(item);
//        }
//        if (item.getConteudo() == null || item.getConteudo().trim().isEmpty()) {
//            throw new ArgumentoInvalidoException(item);
//        }
//        if (item.getStatus() == null) {
//            throw new ArgumentoInvalidoException(item);
//        }
//        if (item.getPrioridades() == null) {
//            throw new ArgumentoInvalidoException(item);
//        }
//        int index = listasDeTask.indexOf(item);
//        if (index == -1) {
//            throw new ElementoNaoEncontradoException(item);
//        }
//        Task taskAntiga = listasDeTask.get(index);
//        taskAntiga.setNome(item.getNome());
//        taskAntiga.setConteudo(item.getConteudo());
//        taskAntiga.setStatus(item.getStatus());
//        taskAntiga.setPrioridades(item.getPrioridades());
//
//        RepositorioFileUtil.salvarArquivo(listasDeTask, this.fileName);
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
//        RepositorioFileUtil.salvarArquivo(listasDeTask, this.fileName);

    }
    public List<Task> listarPor(String cor, String prioridade, String status) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        List<Task> tasksFiltradas = new ArrayList<>();
        for(Task n: listasDeTask){
            if(n.getClassificacao().getCorDaTask().equals(cor) && n.getClassificacao().getPrioridadeDaTask().equals(prioridade)
            && n.getClassificacao().getStatusDaTask().equals(status)){
                tasksFiltradas.add(n);
            }
        }
        return tasksFiltradas;

    }

//    public List<Task> listarPorl(Filtro filtro, Object valor) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
//        List<Task> tasksFiltradas = new ArrayList<>();
//
//        for(Task task : listasDeTask) {
//            switch(filtro) {
//                case STATUS:
//                    if(task.getStatus() == (Status) valor) {
//                        tasksFiltradas.add(task);
//                    }
//                    break;
//                case PRIORIDADE:
//                    if(task.getPrioridades() == (Prioridade) valor) {
//                        tasksFiltradas.add(task);
//                    }
//                    break;
//                case COR:
//                    if(task.getCor().equals((String) valor)) {
//                        tasksFiltradas.add(task);
//                    }
//                    break;
//                default:
//                    throw new ArgumentoInvalidoException(valor);
//            }
//        }
//
//        if(tasksFiltradas.isEmpty()){
//            throw new ElementoNaoEncontradoException(valor);
//        }
//
//        return tasksFiltradas;
//    }


    public List<Task> gerarRelatorioPorMes(int mes) throws ElementoNaoEncontradoException {
        List<Task> tarefasConcluidasNoMes = new ArrayList<>();

        for (Task task : listasDeTask) {
            if (task.getClassificacao().getStatusDaTask().equals("concluida") && task.getDataConclusao().getMonthValue() == mes) {
                tarefasConcluidasNoMes.add(task);
            }
        }
        return tarefasConcluidasNoMes;
    }


    public void marcaComoConcluida (Task task){
        for(Task a : listasDeTask){
            if(task == a){
                a.setDataConclusao(LocalDate.now());
                a.getClassificacao().setStatusDaTask("concluida");            }
        }
    }

//    private void validarTask(Task task) throws ArgumentoInvalidoException {
//        if (task == null || task.getNome() == null || task.getNome().trim().isEmpty() || task.getClassificacao().getStatusDaTask() == null) {
//            throw new ArgumentoInvalidoException(task);
//        }
//    }
}


