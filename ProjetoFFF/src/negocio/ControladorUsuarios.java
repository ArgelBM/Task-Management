package negocio;

import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

public class ControladorUsuarios implements Serializable {


    private UsuariosRepository repositorio;
    private static ControladorUsuarios instance;

    public ControladorUsuarios(){
        this.repositorio = new UsuariosRepository("usuarios.dat");
    }

    public static ControladorUsuarios getInstance(){
        if(instance == null){
            instance = new ControladorUsuarios();
        }
        return instance;
    }

    public void fazerLogin(String login, String senha, boolean marcada) throws NullPointerException, IllegalArgumentException {
        if (login == null || senha == null) {
            throw new NullPointerException("Login ou senha inválidos");
        }
        ControladorTasks.getInstance().setUsuarioAtivo(repositorio.fazerLogin(login, senha, marcada));
    }

    public List<Usuario> listarPorTodos() {
        return repositorio.listarTodos();
    }

    public Usuario listarPorId(int id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return repositorio.listarPorId(id);
    }

    public void adicionar(Usuario usuario) throws NullPointerException, IllegalArgumentException  {
        repositorio.adicionar(usuario);
    }

    public void remover(Usuario usuario) throws NullPointerException, NoSuchElementException {
        if (usuario == null) {
            throw new  NullPointerException("Usuário inválido");
        }
        repositorio.remover(usuario);
    }

    public Usuario procuraPorLogin(String nome) throws NoSuchElementException{
        return repositorio.procurarPorLogin(nome);
    }

    public UsuariosRepository getRepositorio() {
        return repositorio;
    }

    public void salvarMudancas(){
        repositorio.salvar();
    }

}
