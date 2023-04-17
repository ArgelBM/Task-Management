package negocio;


import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.DeletarFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Pomodoro;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.util.List;
import java.util.function.Consumer;

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
    public void fazerLogin(String login, String senha, boolean marcada) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorUsuarios.fazerLogin(login, senha, marcada);
        System.out.println("fachada");
    }

    public List<Usuario> listarPorTodos() {
        return controladorUsuarios.listarPorTodos();
    }

    public Usuario listarPorId(int id) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        return controladorUsuarios.listarPorId(id);
    }

    public Usuario procuraPorLogin(String nome) throws ElementoNaoEncontradoException {
        return controladorUsuarios.procuraPorLogin(nome);
    }

    public void adicionar(Usuario usuario) throws ElementoJaExisteException, ArgumentoInvalidoException {
        controladorUsuarios.adicionar(usuario);
    }

    public void remover(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorUsuarios.remover(usuario);
    }

//    public void atualizar(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
//        controladorUsuarios.atualizar(usuario);
//    }

    public UsuariosRepository getRepositorioUsuarios() {
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

    // tarefas
    public void adicionar(Task task) throws ArgumentoInvalidoException, ElementoJaExisteException {
        controladorTasks.adicionar(task);
    }

    public void mudarNome(Task task, String nome) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorTasks.mudarNome(task, nome);
    }

    public void remover(Task task) throws DeletarFalhouException, ArgumentoInvalidoException, ElementoNaoEncontradoException {
        controladorTasks.remover(task);
    }
    public void addChangeListener(Consumer<List<Task>> listener){
        controladorTasks.addChangeListener(listener);
    }
    public void marcarComoPendente(Task task){
        controladorTasks.marcarComoPendente(task);
    }
    public void marcarComoImportante(Task task){
        controladorTasks.marcarComoImportante(task);
    }
    public void marcarComoConcluida(Task task){
        controladorTasks.marcarComoConcluida(task);
    }
    public void desmarcarComoConcluida(Task task){
        controladorTasks.desmarcarComoConcluida(task);}

    public Task procurarPorNome(String nome) throws ElementoNaoEncontradoException {
        return controladorTasks.procurarPorNome(nome);
    }

   // pomodoro
    public void desmarcarComoImportante(Task task) {
        controladorTasks.desmarcarComoImportante(task);
    }

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

    public void pare() {
        controladorPomodoro.pare();
    }

    public boolean getAtivo(){
        return controladorPomodoro.ativo;
    }

    public int getMinutos(){
      return controladorPomodoro.minutos;
    }

    public int getSegundos(){
        return controladorPomodoro.segundos;
    }
}