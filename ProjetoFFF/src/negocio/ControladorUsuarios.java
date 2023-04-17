package negocio;

import dados.SaveUsersLogin;
import dados.UsuariosRepository;
import negocio.beans.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

public class ControladorUsuarios implements Serializable {

    private SaveUsersLogin usersLogin;
    private UsuariosRepository repositorio;
    private static ControladorUsuarios instance;

    public ControladorUsuarios(){
        this.repositorio = new UsuariosRepository("usuarios.dat");
        this.usersLogin = new SaveUsersLogin("Save.dat");
    }

    public static ControladorUsuarios getInstance(){
        if(instance == null){
            instance = new ControladorUsuarios();
        }
        return instance;
    }

    public SaveUsersLogin getUsersLogin() {
        return usersLogin;
    }

    public void fazerLogin(String login, String senha, boolean marcada) throws NullPointerException, IllegalArgumentException {
        if (login == null || senha == null) {
            throw new NullPointerException("Login ou senha inválidos");
        }
        if(!marcada){
            usersLogin.setUltimoUsuario(new Usuario("",null, 0, "",""));
        }
        if (marcada){
            usersLogin.setUltimoUsuario(repositorio.fazerLogin(login,senha,marcada));
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

    public void remover(Usuario usuario) {
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
