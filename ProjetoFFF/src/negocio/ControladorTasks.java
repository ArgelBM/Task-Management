package negocio;

import dados.TaskRepository;
import enums.Prioridades;
import enums.Status;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Categoria;
import negocio.beans.Pomodoro;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ControladorTasks {
    private static ControladorTasks instance;

    public static ControladorTasks getInstance(){
        if(instance == null){
            instance = new ControladorTasks();
        }
        return instance;
    }

    public static void carregar(Task obj) {
    }

    public static List<Task> listarPorTodos() {
    }

    public static List<Task> listarPornome(String nome) {
    }

    public static void adicionar(Task obj) {
    }

    public static void atualizar(Task obj, Categoria elemento) {
    }

    public static void remover(Task obj) {
    }

    public static List<Task> listarPorStatus(Status status) {
    }

    public static List<Task> listarPorPrioridade(Prioridades prioridade) {
    }

    public static List<Task> listarPorCor(Categoria cor) {
    }

    public static List<Task> listarConcluidas() {
    }

    public static List<Task> listarPorUsuario(Usuario nome) {
    }

    public static void relatorioPorMes(Month mes) {
    }

    public static void salvar(Task obj) {
    }

    public void adicionarTarefa(String nome, String conteudo, Status status, LocalDate datacriada, Prioridades prioridade) throws ElementoJaExisteException, ArgumentoInvalidoException{
        Task task = new Task(nome, conteudo, status, datacriada, prioridade);
        TaskRepository.adicionar(task);
    }

}
