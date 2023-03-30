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

    private TaskRepository repositorio;
    private static ControladorTasks instance;

    public ControladorTasks(){
        this.repositorio = new TaskRepository("taskrepositorio.dat");
    }

    public static ControladorTasks getInstance(){
        if(instance == null){
            instance = new ControladorTasks();
        }
        return instance;
    }


    public List<Task> listarTodos() {
        return repositorio.listarTodos();
    }

    public void adicionar(Task task) throws ArgumentoInvalidoException, ElementoJaExisteException {
        repositorio.adicionar(task);
    }

    public  void atualizar(Task task) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        repositorio.atualizar(task);
    }

    public void remover(Task task) throws DeletarFalhouException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        repositorio.remover(task);
    }

    public List<Task> listarPorStatus(Status status) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return repositorio.listarPor(Filtro.STATUS, status);
    }

    public List<Task> listarPorPrioridade(Prioridades prioridades) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return repositorio.listarPor(Filtro.PRIORIDADE, prioridades);
    }

    public List<Task> listarPorCor(String cor) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return repositorio.listarPor(Filtro.COR, cor);
    }

    public void relatorioPorMes(Month mes) throws ElementoNaoEncontradoException {
        repositorio.gerarRelatorioPorMes(mes);
    }

}
