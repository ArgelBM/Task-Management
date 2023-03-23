package negocio;

import dados.TaskRepository;
import enums.Filtro;
import enums.Prioridades;
import enums.Status;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.io.IOException;
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


    public static List<Task> listarTodos() {
        TaskRepository repo = new TaskRepository();
        return repo.listarTodos();
    }

    public static void adicionar(Task task) throws ArgumentoInvalidoException, ElementoJaExisteException {
        TaskRepository repo = new TaskRepository();
        repo.adicionar(task);
    }

    public static void atualizar(Task task) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        TaskRepository repo = new TaskRepository();
        repo.atualizar(task);
    }

    public static void remover(Task task) throws DeletarFalhouException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        TaskRepository repo = new TaskRepository();
        repo.remover(task);
    }

    public static List<Task> listarPorStatus(Status status) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        TaskRepository repo = new TaskRepository();
        return repo.listarPor(Filtro.STATUS, status);
    }

    public static List<Task> listarPorPrioridade(Prioridades prioridades) throws ElementoNaoEncontradoException {
        TaskRepository repo = new TaskRepository();
        return repo.listarPor(Filtro.PRIORIDADE, prioridades);
    }

    public static List<Task> listarPorCor(String cor) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        TaskRepository repo = new TaskRepository();
        return repo.listarPor(Filtro.COR, cor);
    }

    public static List<Task> listarPorUsuario(Usuario usuario) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        TaskRepository repo = new TaskRepository();
        return repo.listarPor(Filtro.USUARIO,usuario);
    }

    public static void relatorioPorMes(Month mes) throws ElementoNaoEncontradoException {
        TaskRepository repo = new TaskRepository();
        repo.gerarRelatorioPorMes(mes);
    }

    public static void salvar(List<Task> tasks) throws IOException {
        TaskRepository repo = new TaskRepository();
        repo.salvarTarefas(tasks);
    }

    public static void carregar() throws IOException {
        TaskRepository repo = new TaskRepository();
        repo.carregarTarefas();
    }
}
