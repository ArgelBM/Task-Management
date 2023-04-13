package dados;

import exceptions.*;
import negocio.ControladorUsuarios;
import negocio.beans.Task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TaskRepository implements IRepository<Task>, Serializable {

    private List<Task> listasDeTask;
    private transient List<Consumer<List<Task>>> changeListeners;

    public TaskRepository(){
        this.listasDeTask = new ArrayList<>();
        this.changeListeners = new ArrayList<>();
    }

    public void addChangeListener(Consumer<List<Task>> listener) {
        if (changeListeners == null) {
            changeListeners = new ArrayList<>();
        }
        changeListeners.add(listener);
    }

    private void notifyChangeListeners() {
        for (Consumer<List<Task>> listener : changeListeners) {
            listener.accept(listasDeTask);
        }
    }

    @Override
    public void adicionar(Task item) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if ((item == null)){
            throw new ArgumentoInvalidoException(null);
        }
        if(item.getNome() == null || item.getNome().trim().isEmpty()){
            throw new ArgumentoInvalidoException(item);
        }
        for(Task a: listasDeTask){
            if(a.getNome().equals(item.getNome())){
                throw new ArgumentoInvalidoException(item);
            }
        }
        listasDeTask.add(item);
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
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
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
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
    public void atualizar(Task item) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
    }


    public List<Task> listarPorFiltro(String cor, String prioridade, String status) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        List<Task> tasksFiltradas = new ArrayList<>();
        for(Task n: listasDeTask){
            if(n.getClassificacao().getCorDaTask().equals(cor) && n.getClassificacao().getPrioridadeDaTask().equals(prioridade)
            && n.getClassificacao().getStatusDaTask().equals(status)){
                tasksFiltradas.add(n);
            }
        }
        return tasksFiltradas;

    }

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
                //salva a dataconclusao na dataconclusaoanterior
                a.setDataConclusaoAnterior(a.getDataConclusao());
                //atualiza a dataconclusao pra agora
                a.setDataConclusao(LocalDate.now());
                a.getClassificacao().setStatusDaTask("concluida");
            }
        }
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
    }

    public void marcaComoPendente (Task task){
        for(Task a : listasDeTask){
            if(task == a){
                //salva a dataconclusao na dataconclusaoanterior
                a.setDataConclusaoAnterior(a.getDataConclusao());
                //atualiza a dataconclusao pra agora
                a.setDataConclusao(LocalDate.now());
                a.getClassificacao().setStatusDaTask("pendente");
            }
        }
        notifyChangeListeners();
        ControladorUsuarios.getInstance().salvarMudancas();
    }

    public void desmarcaComoConcluida (Task task){
        for(Task a : listasDeTask){
            if(task == a){
                //recupera a data de conclusao antes de marcar como concluido
                a.setDataConclusao(a.getDataConclusaoAnterior());
                a.getClassificacao().setStatusDaTask("pendente");
            }
        }
        notifyChangeListeners();
        ControladorUsuarios.getInstance().salvarMudancas();
    }

}


