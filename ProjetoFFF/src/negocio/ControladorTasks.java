package negocio;

import dados.IRepository;
import dados.TaskRepository;
import enums.Filtro;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;

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


    public List<Task> listarTarefas() {
        return this.repositorio.listarTodos();
    }

    public void adicionar(Task task) throws ArgumentoInvalidoException, ElementoJaExisteException {
        validarTask(task);
        repositorio.adicionar(task);
    }

    public  void atualizar(Task task) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        validarTask(task);
        repositorio.atualizar(task);
    }

    public void remover(Task task) throws DeletarFalhouException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        validarTask(task);
        repositorio.remover(task);
    }

    public List<Task> listarPor(Filtro filtro, Object valor) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return repositorio.listarPor(filtro, valor);
    }

    public void relatorioPorMes(Month mes) throws ElementoNaoEncontradoException {
        repositorio.gerarRelatorioPorMes(mes);
    }

    private void validarTask(Task task) throws ArgumentoInvalidoException {
        if (task == null || task.getNome() == null || task.getNome().trim().isEmpty()
                || task.getConteudo() == null || task.getConteudo().trim().isEmpty() || task.getStatus() == null
                || task.getPrioridades() == null) {
            throw new ArgumentoInvalidoException(task);
        }
    }
}
