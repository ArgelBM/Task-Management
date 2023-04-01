package dados;

import exceptions.*;
import negocio.beans.Usuario;

import java.util.*;

public class UsuariosRepository implements IRepository<Usuario> {
    List<Usuario> usuarios;
    String fileName;

    public UsuariosRepository(String fileName){
        this.usuarios = new ArrayList<>();
        this.fileName = fileName;

        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
        if (listaElementos != null && listaElementos instanceof List<?>){
            this.usuarios = (List<Usuario>) listaElementos;
        }
    }

    public Usuario fazerLogin(String login, String senha) throws ArgumentoInvalidoException {
        if (login == null || senha == null) {
            throw new ArgumentoInvalidoException("Credenciais inválidas");
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        throw new ArgumentoInvalidoException("Usuario ou senha incorretos");
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    @Override
    public Usuario listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
            for (Usuario usuario : usuarios) {
                if (usuario.getId() == id) {
                    return usuario;
                }
            }
            throw new ArgumentoInvalidoException("Credenciais inválidas");
        }

    @Override
    public List<Usuario> listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        List<Usuario> usuariosEncontrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getNomeUsuario().equalsIgnoreCase(nome)) {
                usuariosEncontrados.add(usuario);
            }
        }

        return usuariosEncontrados;
    }

    @Override
    public void adicionar(Usuario usuario) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if (usuario == null) {
            throw new ArgumentoInvalidoException("Usuário não pode ser nulo");
        }
        if (usuarios.contains(usuario)) {
            throw new ElementoJaExisteException("Usuário já existe");
        }
        usuarios.add(usuario);
        RepositorioFileUtil.salvarArquivo(usuarios, this.fileName);

    }


    @Override
    public void atualizar(Usuario usuario) throws ElementoNaoEncontradoException {
        int index = usuarios.indexOf(usuario);

        if (index == -1) {
            throw new ElementoNaoEncontradoException("Usuário não encontrado");
        }

        Usuario usuarioAntigo = usuarios.get(index);

        usuarioAntigo.setNomeUsuario(usuario.getNomeUsuario());
        usuarioAntigo.setDataNascimento(usuario.getDataNascimento());
        usuarioAntigo.setLogin(usuario.getLogin());
        usuarioAntigo.setSenha(usuario.getSenha());
        usuarioAntigo.setTask(usuario.getTask());

        RepositorioFileUtil.salvarArquivo(usuarios, this.fileName);
    }

     @Override
     public void remover(Usuario usuario) throws ElementoNaoEncontradoException {
         if (usuario == null) {
             throw new IllegalArgumentException("Usuário não pode ser nulo.");
         }

         boolean removido = usuarios.remove(usuario);
         if (!removido) {
             throw new ElementoNaoEncontradoException("Usuário não encontrado.");
         }

         RepositorioFileUtil.salvarArquivo(usuarios, this.fileName);
     }
}
