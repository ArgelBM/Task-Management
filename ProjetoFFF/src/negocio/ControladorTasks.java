package negocio;

import dados.TaskRepository;
import enums.Prioridades;
import enums.Status;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;

import java.time.LocalDate;

public class ControladorTasks {
    public static void adicionarTarefa(String nome, String conteudo, Status status, LocalDate datacriada, Prioridades prioridade, Lis) throws ElementoJaExisteException, ArgumentoInvalidoException{
        Task task = new Task(nome, conteudo, status, datacriada, prioridade);
        if (TaskRepository.listarPorNome(task.getNome())) {
            throw new ElementoJaExisteException(task);
        } else {
            try {
                TaskRepository.adicionar(task);
            } catch (IllegalArgumentException e) {
                throw new ArgumentoInvalidoException(task);
            }
        }
    }

}
