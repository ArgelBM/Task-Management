package negocio;


import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;
import negocio.beans.Usuario;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void fazerLogin(String login, String senha, boolean marcada) throws NullPointerException, IllegalArgumentException {
        controladorUsuarios.fazerLogin(login, senha, marcada);
        System.out.println("fachada");
    }

    public List<Usuario> listarPorTodos() {
        return controladorUsuarios.listarPorTodos();
    }

    public Usuario listarPorId(int id) throws IllegalArgumentException {
        return controladorUsuarios.listarPorId(id);
    }

    public Usuario procuraPorLogin(String nome) throws NoSuchElementException {
        return controladorUsuarios.procuraPorLogin(nome);
    }

    public void adicionar(Usuario usuario) throws NullPointerException, IllegalArgumentException {
        controladorUsuarios.adicionar(usuario);
    }

    public void remover(Usuario usuario) {
        controladorUsuarios.remover(usuario);
    }

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

    public void remover(Task task) {
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

    public int contarTarefasConcluidasNoMes(int mes) {
        return controladorTasks.contarTarefasConcluidasNoMes(mes);
    }

    public int contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek dia) {
        return controladorTasks.contarTarefasConcluidasNaUltimaSemanaPorDia(dia);
    }

    public int contarTarefasConcluidasNoDiaPorMes(int dia, Month mes) {
        return controladorTasks.contarTarefasConcluidasNoDiaPorMes(dia, mes);
    }

    public void setDataPrevisao(Task task, LocalDate data) {
        controladorTasks.setDataPrevisao(task, data);
    }

    public void setFiltro(Task task, String nome) {
        controladorTasks.setFiltro(task, nome);
    }

    // pomodoro
    public void desmarcarComoImportante(Task task) {
        controladorTasks.desmarcarComoImportante(task);
    }

    public void iniciarPomodoro() {
        controladorPomodoro.iniciarPomodoro();
    }

    public void pare(int minutos) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        controladorPomodoro.pare(minutos);
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