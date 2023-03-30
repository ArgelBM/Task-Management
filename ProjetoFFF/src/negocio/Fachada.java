package negocio;


import enums.Prioridades;
import enums.Status;
import exceptions.*;
import negocio.beans.Categoria;
import negocio.beans.Pomodoro;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.io.IOException;
import java.time.Month;
import java.util.List;

public class Fachada {
   /* private static Fachada instance;
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
    public List<Categoria> listarCategorias(){
        return ControladorCategorias.listarPorTodos();
    }
    public Categoria listarCategoriasPorNome(String nome) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        return ControladorCategorias.listarPornome(nome);
    }
    public void adicionarCategoria(Categoria obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorCategorias.adicionar(obj);
    }
    public void removerCategoria(Categoria obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorCategorias.remover(obj);
    }


    //Pomodoro
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

    //Task
    public List<Task> listarTask(){
        return ControladorTasks.listarTodos();
    }
    public void adicionarTask(Task obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorTasks.adicionar(obj);
    }
    public void atualizarTask(Task obj) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorTasks.atualizar(obj);
    }
    public void removerTask(Task obj) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorTasks.remover(obj);
    }
    public List<Task> listarTaskPorStatus(Status status) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        return ControladorTasks.listarPorStatus(status);
    }
    public List<Task> listarTaskPorPrioridade(Prioridades prioridade) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return ControladorTasks.listarPorPrioridade(prioridade);
    }
    public List<Task> listarTaskPorCor(String cor) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        return ControladorTasks.listarPorCor(cor);
    }
    public void gerarRelatorioPorMes(Month mes) throws ElementoNaoEncontradoException {
        ControladorTasks.relatorioPorMes(mes);
    }
    public void salvarTarefa(List<Task> tasks, String nomeArquivo) throws IOException {
        ControladorTasks.salvar(tasks, nomeArquivo);
    }
    public List<Task> carregarTask(String nomeArquivo) throws IOException {
        return ControladorTasks.carregar(nomeArquivo);
    }

    //Usuario
    public List<Usuario> listarUsuario(){
        return ControladorUsuarios.listarPorTodos();
    }
    public Usuario listarUsuarioPorId(int id) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        return ControladorUsuarios.listarPorId(id);
    }
    public void adicionarUsuario(Usuario obj) throws ElementoJaExisteException, ArgumentoInvalidoException{
        ControladorUsuarios.adicionar(obj);
    }
    public void removerUsuario(Usuario obj) throws ElementoNaoEncontradoException, ArgumentoInvalidoException{
        ControladorUsuarios.remover(obj);
    }
    public void atualizarUsuario(Usuario obj) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        ControladorUsuarios.atualizar(obj);
    }


    */
}
