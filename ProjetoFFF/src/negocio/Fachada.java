package negocio;


import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Pomodoro;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.time.Month;
import java.util.List;

public class Fachada {
    private static Fachada instance;
    private ControladorPomodoro controladorPomodoro;
    private ControladorTasks controladorTasks;
    private ControladorUsuarios controladorUsuarios;

    public Fachada() {
        this.controladorPomodoro = ControladorPomodoro.getInstance();
        this.controladorTasks = ControladorTasks.getInstance();
        this.controladorUsuarios = ControladorUsuarios.getInstance();
    }

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }


    //Usuario
    public void fazerLogin(String login, String senha) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorUsuarios.fazerLogin(login, senha);
    }

    public List<Usuario> listarPorTodos() {
        return controladorUsuarios.listarPorTodos();
    }

    public Usuario listarPorId(int id) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        return controladorUsuarios.listarPorId(id);
    }

    public void adicionar(Usuario usuario) throws ElementoJaExisteException, ArgumentoInvalidoException {
        controladorUsuarios.adicionar(usuario);
    }

    public void remover(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorUsuarios.remover(usuario);
    }

    public void atualizar(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorUsuarios.atualizar(usuario);
    }

    public UsuariosRepository getRepositorio() {
        return controladorUsuarios.getRepositorio();
    }

    public void salvarMudancas() {
        controladorUsuarios.salvarMudancas();
    }

    //Listas


    public Usuario getUsuarioAtivo() {
        return controladorTasks.getUsuarioAtivo();
    }

    public void setUsuarioAtivo(Usuario usuarioAtivo) {
        controladorTasks.setUsuarioAtivo(usuarioAtivo);
    }

    public List<Task> listarTarefas() {
        return controladorTasks.listarTarefas();
    }

    public void adicionar(Task task) throws ArgumentoInvalidoException, ElementoJaExisteException {
        controladorTasks.adicionar(task);
    }

    public void atualizar(Task task) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorTasks.atualizar(task);
    }

    public void remover(Task task) throws DeletarFalhouException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorTasks.remover(task);
    }


}