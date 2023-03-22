package negocio;


import enums.Prioridades;
import enums.Status;
import exceptions.*;
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
    public List<Categoria> listarCategorias(){
        return ControladorCategorias.listarPorTodos();
    }
    public List<Categoria> listarCategoriasPorNome(String nome){
        return ControladorCategorias.listarPornome(nome);
    }
    public void adicionarCategoria(Categoria obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorCategorias.adicionar(obj);
    }
    public void removerCategoria(Categoria obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorCategorias.remover(obj);
    }



    public void adicionarPomodoro(Pomodoro obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorPomodoro.adicionar(obj);
    }
    public void removerPomodoro(Pomodoro obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorPomodoro.remover(obj);
    }
    public List<Pomodoro> pomodoroPorTarefa(Task obj){
        return ControladorPomodoro.listarPorTarefa(obj);
    }
    public List<Pomodoro> pomodoroConcluidosPorTarefa(Task obj){
        return ControladorPomodoro.listarPorTarefa(obj);
    }
    public Pomodoro iniciarPomodoro(){
        return ControladorPomodoro.iniciar();
    }
    public Pomodoro pararPomodoro(){
        return ControladorPomodoro.parar();
    }
    public Pomodoro alertaPomodoro(){
        return ControladorPomodoro.alerta();
    }



    public List<Task> listarTask(){
        return ControladorTasks.listarTodos();
    }
    public List<Task> listarTaskPorNome(String nome){
        return ControladorTasks.listarPornome(nome);
    }
    public void adicionarTask(Task obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorTasks.adicionar(obj);
    }
    public void atualizarTask(Task obj, Categoria elemento) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorTasks.atualizar(obj, elemento);
    }
    public void removerTask(Task obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorTasks.remover(obj);
    }
    public List<Task> listarTaskPorStatus(Status status){
        return ControladorTasks.listarPorStatus(status);
    }
    public List<Task> listarTaskPorPrioridade(Prioridades prioridade){
        return ControladorTasks.listarPorPrioridade(prioridade);
    }
    public List<Task> listarTaskPorCor(Categoria cor){
        return ControladorTasks.listarPorCor(cor);
    }
    public List<Task> listarTaskConcluidas(){
        return ControladorTasks.listarConcluidas();
    }
    public List<Task> listarTaskPorUsuario(Usuario nome){
        return ControladorTasks.listarPorUsuario(nome);
    }
    public void gerarRelatorioPorMes(Month mes) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorTasks.relatorioPorMes(mes);
    }
    public void salvarTarefa(Task obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorTasks.salvar(obj);
    }
    public void carregarTask(Task obj) throws ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorTasks.carregar(obj);
    }



    public List<Usuario> listarUsuario(){
        return ControladorUsuarios.listarPorTodos();
    }
    public List<Usuario> listarUsuarioPorId(int id){
        return ControladorUsuarios.listarPorId(id);
    }
    public List<Usuario> listarUsuarioPorNome(String nome){
        return ControladorUsuarios.listarPornome(nome);
    }
    public List<Usuario> listarUsuarioPorTask(Task nome){
        return ControladorUsuarios.listarPorTask(nome);
    }
    public void adicionarUsuario(Usuario obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorUsuarios.adicionar(obj);
    }
    public void removerUsuario(Usuario obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorUsuarios.remover(obj);
    }

}
