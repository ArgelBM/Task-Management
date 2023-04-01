package negocio;


import enums.Filtro;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Categoria;
import negocio.beans.Pomodoro;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.time.Month;
import java.util.List;

public class Fachada {
   private static Fachada instance;
    private ControladorCategorias controladorCategorias;
    private ControladorPomodoro controladorPomodoro;
    private ControladorTasks controladorTasks;
    private ControladorUsuarios controladorUsuarios;

    public Fachada(){
        this.controladorCategorias = ControladorCategorias.getInstance();
        this.controladorPomodoro = ControladorPomodoro.getInstance();
        this.controladorTasks = ControladorTasks.getInstance();
        this.controladorUsuarios = ControladorUsuarios.getInstance();
    }
    public static Fachada getInstance(){
        if(instance == null){
            instance = new Fachada();
        }
        return instance;
    }

    //Categorias
    public void adicionar(Categoria categoria) throws ElementoJaExisteException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorCategorias.adicionar(categoria);
    }

    public void remover(Categoria categoria) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {
        controladorCategorias.remover(categoria);
    }

    public List<Categoria> listarTodos() {
        return controladorCategorias.listarTodos();
    }

    public Categoria listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return controladorCategorias.listarPorNome(nome);
    }


    //Pomodoro
    public void getTempoPomodoro() {
        controladorPomodoro.getTempoPomodoro();
    }

    public void getTempoDescanso() {
        controladorPomodoro.getTempoDescanso();
    }

    public void getTempoDescansoLongo() {
        controladorPomodoro.getTempoDescansoLongo();
    }

    public void iniciarPomodoro(Pomodoro pomodoro) {
        controladorPomodoro.iniciarPomodoro(pomodoro);
    }

    //Task
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

    public List<Task> listarPor(Filtro filtro, Object valor) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return controladorTasks.listarPor(filtro, valor);
    }

    public void relatorioPorMes(Month mes) throws ElementoNaoEncontradoException {
        controladorTasks.relatorioPorMes(mes);
    }


    //Usuarios
    public Usuario fazerLogin(String login, String senha) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        return controladorUsuarios.fazerLogin(login, senha);
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

    public void atualizar(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorUsuarios.atualizar(usuario);
    }

    public void remover(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorUsuarios.remover(usuario);
    }
}
