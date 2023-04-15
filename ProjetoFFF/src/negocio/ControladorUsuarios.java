package negocio;

import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Usuario;

import java.io.Serializable;
import java.util.List;

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

    public void fazerLogin(String login, String senha) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        if (login == null || senha == null) {
            throw new ArgumentoInvalidoException("Login ou senha inválidos");
        }
        ControladorTasks.getInstance().setUsuarioAtivo(repositorio.fazerLogin(login, senha));
    }

    public List<Usuario> listarPorTodos() {
        return repositorio.listarTodos();
    }

    public Usuario listarPorId(int id) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        if (id < 0) {
            throw new ArgumentoInvalidoException("ID inválido");
        }
        return repositorio.listarPorId(id);
    }

    public void adicionar(Usuario usuario) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if (usuario == null) {
            throw new ArgumentoInvalidoException("Usuário inválido");
        }
        else if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty() || usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new ArgumentoInvalidoException("Login inválido ou senha invalidos");
        }
        else {
            repositorio.adicionar(usuario);
        }
    }

    public void remover(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        if (usuario == null) {
            throw new ArgumentoInvalidoException("Usuário inválido");
        }
        repositorio.remover(usuario);
    }

//    Tem que ajeitar o mudarNome de UsuariosRepository, ele ta pegando o de TaskRepo, porque o
//    de UsuariosRepository esta vazio.

//    public void atualizar(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
//        if (usuario == null) {
//            throw new ArgumentoInvalidoException("Usuário inválido");
//        }
//        if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().trim().isEmpty()) {
//            throw new ArgumentoInvalidoException("Nome de usuário inválido");
//        }
//        if (usuario.getDataNascimento() == null) {
//            throw new ArgumentoInvalidoException("Data de nascimento inválida");
//        }
//        if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
//            throw new ArgumentoInvalidoException("Login inválido");
//        }
//        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
//            throw new ArgumentoInvalidoException("Senha inválida");
//        }
//        repositorio.mudarNome(usuario);
//    }


    public UsuariosRepository getRepositorio() {
        return repositorio;
    }

    public void salvarMudancas(){
        repositorio.salvar();
    }

}
