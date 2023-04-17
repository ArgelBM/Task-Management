package negocio;

import dados.TaskRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;
import java.util.function.Consumer;


public class ControladorTasks{
    private static ControladorTasks instance;
    private Usuario usuarioAtivo;

    public static ControladorTasks getInstance(){
        if(instance == null){
            instance = new ControladorTasks();
        }

        return instance;
    }

    public TaskRepository getRepositorio() {
        return usuarioAtivo.getTask();
    }

    public Usuario getUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(Usuario usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }


    public List<Task> listarTarefas() {
        return usuarioAtivo.getTask().listarTodos();
    }

    public void adicionar(Task task) throws ArgumentoInvalidoException, ElementoJaExisteException {
        usuarioAtivo.getTask().adicionar(task);
    }

    public  void mudarNome(Task task, String nome) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        usuarioAtivo.getTask().mudarNome(task, nome);
    }

    public void remover(Task task) throws DeletarFalhouException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        usuarioAtivo.getTask().remover(task);
    }

    public void marcarComoConcluida(Task task){
        usuarioAtivo.getTask().marcaComoConcluida(task);
    }

    public void marcarComoPendente(Task task){
        usuarioAtivo.getTask().marcaComoPendente(task);
    }

    public void desmarcarComoConcluida(Task task){
        usuarioAtivo.getTask().desmarcaComoConcluida(task);
    }

    public void addChangeListener(Consumer<List<Task>> listener){
        usuarioAtivo.getTask().addChangeListener(listener);
    }

    public void marcarComoImportante(Task task){
        usuarioAtivo.getTask().marcaComoImportante(task);
    }

    public void desmarcarComoImportante(Task task){
        usuarioAtivo.getTask().desmarcaComoImportante(task);
    }

    public Task procurarPorNome(String nome) throws ElementoNaoEncontradoException {
        return usuarioAtivo.getTask().listarPorNome(nome);
    }

    public int contarTarefasConcluidasNoMes(int mes) {
        return usuarioAtivo.getTask().contarTarefasConcluidasNoMes(mes);
    }

    public int contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek dia){
        return usuarioAtivo.getTask().contarTarefasConcluidasNaUltimaSemanaPorDia(dia);
    }

    public int contarTarefasConcluidasNoDiaPorMes(int dia, Month mes){
        return usuarioAtivo.getTask().contarTarefasConcluidasNoDiaPorMes(dia, mes);
    }
}
