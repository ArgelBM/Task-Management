package dados;

import exceptions.*;
import negocio.beans.Task;
import negocio.beans.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuariosRepository implements IRepository<Usuario> {
    List<Usuario> usuarios = new ArrayList<>();

    public Usuario fazerLogin(String login, String senha) throws CredenciaisInvalidasException {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        throw new CredenciaisInvalidasException();
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    @Override
    public Usuario listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Object listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public void adicionar(Usuario usuario) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if (usuario == null) {
            throw new ArgumentoInvalidoException(null);
        }
        if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
            throw new ArgumentoInvalidoException(usuario);
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new ArgumentoInvalidoException(usuario);
        }
        if (usuarios.contains(usuario)) {
            throw new ElementoJaExisteException(usuario);
        }
        usuarios.add(usuario);
    }


    @Override
    public void atualizar(Usuario item) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        if (item == null) {
            throw new ArgumentoInvalidoException(null);
        }
        if (item.getNomeUsuario() == null || item.getNomeUsuario().trim().isEmpty()) {
            throw new ArgumentoInvalidoException(item);
        }
        if (item.getDataNascimento() == null) {
            throw new ArgumentoInvalidoException(item);
        }
        if (item.getLogin() == null || item.getLogin().trim().isEmpty()) {
            throw new ArgumentoInvalidoException(item);
        }
        if (item.getSenha() == null || item.getSenha().trim().isEmpty()) {
            throw new ArgumentoInvalidoException(item);
        }
        int index = usuarios.indexOf(item);
        if (index == -1) {
            throw new ElementoNaoEncontradoException(item);
        }
        Usuario usuarioAntigo = usuarios.get(index);
        usuarioAntigo.setNomeUsuario(item.getNomeUsuario());
        usuarioAntigo.setDataNascimento(item.getDataNascimento());
        usuarioAntigo.setLogin(item.getLogin());
        usuarioAntigo.setSenha(item.getSenha());
        usuarioAntigo.setTask(item.getTask());
    }

     @Override
     public void remover(Usuario item) throws ElementoNaoEncontradoException, DeletarUsuarioException, ArgumentoInvalidoException {
         if (item == null) {
             throw new ArgumentoInvalidoException(null);
         }
         int index = usuarios.indexOf(item);
         if (index == -1) {
             throw new ElementoNaoEncontradoException(item);
         }
         if (!usuarios.remove(item)) {
             throw new DeletarUsuarioException(item);
         }
     }
}
