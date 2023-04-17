package dados;

import exceptions.*;
import negocio.ControladorUsuarios;
import negocio.beans.Task;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void adicionar(Task item) throws ElementoJaExisteException,IllegalArgumentException {
        if ((item == null)){
            throw new IllegalArgumentException("tarefa não pode ser nula!");
        }
        if(item.getNome() == null || item.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("O nome da tarefa é invalido!");
        }
        for(Task a: listasDeTask){
            if(a.getNome().equals(item.getNome())){
                throw new IllegalArgumentException("Essa tarefa já existe na lista!");
            }
        }
        listasDeTask.add(item);
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
    }
    @Override
    public void remover(Task item) throws ElementoNaoEncontradoException, NullPointerException {
        if (item == null) {
            throw new NullPointerException("Tarefa não pode ser nula!");
        }
        int index = listasDeTask.indexOf(item);
        if (index == -1) {
            throw new ElementoNaoEncontradoException(item);
        }
        if (!listasDeTask.remove(item)) {
            throw new NullPointerException ("Deletar falhou!");
        }
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
    }
    @Override
    public List<Task> listarTodos() {
        return listasDeTask;
    }

    @Override
    public Task listarPorId(int id) {
        return null;
    }

    @Override
    public Task listarPorNome(String nome) throws ElementoNaoEncontradoException {
        Optional<Task> tarefa = listasDeTask.stream()
                .filter(u -> nome.equalsIgnoreCase(u.getNome()))
                .findAny();
        if (tarefa.isPresent()) {
            return tarefa.get();
        } else {
            throw new ElementoNaoEncontradoException(tarefa);
        }
    }

    @Override
    public void mudarNome(Task task, String novoNome) throws ElementoNaoEncontradoException, NullPointerException {
        if(task == null){
            throw new NullPointerException("Tarefa não pod ser nula!");
        }
        String nome = task.getNome();
        Optional<Task> tarefa = listasDeTask.stream()
                .filter(u -> nome.equalsIgnoreCase(u.getNome()))
                .findAny();
        if (tarefa.isPresent()) {
            task.setNome(novoNome);
        } else {
            throw new ElementoNaoEncontradoException(tarefa);
        }
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
    }


    public List<Task> listarPorFiltro(String cor, String prioridade, String status) throws ElementoNaoEncontradoException,IllegalArgumentException {
        List<Task> tasksFiltradas = new ArrayList<>();
        for(Task n: listasDeTask){
            if(n.getClassificacao().getCorDaTask().equals(cor) && n.getClassificacao().getPrioridadeDaTask().equals(prioridade)
            && n.getClassificacao().getStatusDaTask().equals(status)){
                tasksFiltradas.add(n);
            }
        }
        return tasksFiltradas;

    }

    public int contarTarefasConcluidasNoMes(int mes) {
        return (int) listasDeTask.stream()
                .filter(t -> mes == t.getDataConclusao().getMonthValue())
                .count();
    }

    public int contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek diaDaSemana) {
        LocalDate hoje = LocalDate.now();
        LocalDate umaSemanaAtras = hoje.minusDays(7);
        return (int) listasDeTask.stream()
                .filter(t -> "concluida".equals(t.getClassificacao().getStatusDaTask()))
                .filter(t -> t.getDataConclusao().isAfter(umaSemanaAtras))
                .filter(t -> t.getDataConclusao().getDayOfWeek() == diaDaSemana)
                .count();
    }

    public int contarTarefasConcluidasNoDiaPorMes(int dia, Month mes) {
        LocalDate dataEspecifica = LocalDate.now().withMonth(mes.getValue()).withDayOfMonth(dia);
        return (int) listasDeTask.stream()
                .filter(t -> "concluida".equals(t.getClassificacao().getStatusDaTask()))
                .filter(t -> t.getDataConclusao().getMonth() == mes)
                .filter(t -> t.getDataConclusao().isEqual(dataEspecifica))
                .count();
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

    public void marcaComoImportante (Task task){
        for(Task a : listasDeTask){
            if(task == a){
                a.getClassificacao().setPrioridadeDaTask("importante");
            }
        }
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
    }

    public void desmarcaComoImportante (Task task){
        for(Task a : listasDeTask){
            if(task == a){
                a.getClassificacao().setPrioridadeDaTask(" ");
            }
        }
        ControladorUsuarios.getInstance().salvarMudancas();
        notifyChangeListeners();
    }



}


