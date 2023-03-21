package negocio;

import dados.TaskRepository;
import enums.Prioridades;
import enums.Status;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Pomodoro;
import negocio.beans.Task;

import java.time.LocalDate;
import java.util.List;

public class ControladorTasks {
    private static ControladorTasks instance;

    public static ControladorTasks getInstance(){
        if(instance == null){
            instance = new ControladorTasks();
        }
        return instance;
    }

    public void adicionarTarefa(String nome, String conteudo, Status status, LocalDate datacriada, Prioridades prioridade, List<Pomodoro> pomodoros) throws ElementoJaExisteException, ArgumentoInvalidoException{
        Task task = new Task(nome, conteudo, status, datacriada, prioridade, pomodoros);
        TaskRepository.adicionar(task);
    }

}
