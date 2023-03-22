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
        System.out.println("Esse método será implementado com file posteriormente");
    }

    public static List<Task> listarTodos() {
    }

    public static List<Task> listarPornome(String nome) { //chamar a função do repositorio
    }

    public static void adicionar(Task task) throws ArgumentoInvalidoException, ElementoJaExisteException {
        TaskRepository repo = new TaskRepository();
        repo.adicionar(task);
    }

    public static void atualizar(Task obj, Categoria elemento) { //modificar a categoria da tarefa (cor e prioridade, isso ta em beans)
    }

    public static void remover(Task obj) { //depois de fazer em repositorio chamar aqui. tenho que ver qual é a categoria, ver exemplo em atualizar.
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
        System.out.println("Esse método será implementado com file posteriormente");
    }

}
