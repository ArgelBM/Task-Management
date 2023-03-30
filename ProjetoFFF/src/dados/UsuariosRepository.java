package dados;

import exceptions.*;
import negocio.beans.Categoria;
import negocio.beans.Usuario;

import java.util.*;

public class UsuariosRepository implements IRepository<Usuario> {
    private List<Usuario> usuarios;

    private String fileName;

    public UsuariosRepository(String fileName){
        this.usuarios = new ArrayList<>();
        this.fileName = fileName;

        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
        if (listaElementos != null && listaElementos instanceof List<?>){
            this.usuarios = (List<Usuario>) listaElementos;
        }
    }

    public Usuario fazerLogin(String login, String senha) throws ArgumentoInvalidoException {
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
        RepositorioFileUtil.salvarArquivo(usuarios, this.fileName);

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

        RepositorioFileUtil.salvarArquivo(usuarios, this.fileName);

    }

     @Override
     public void remover(Usuario item) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {

         try {
             usuarios.remove(item);

         } catch (InputMismatchException e) {
             throw new ArgumentoInvalidoException("Falha ao deletar item: " + e.getMessage());
         } catch (NoSuchElementException e) {
             throw new ElementoNaoEncontradoException("Elemento não encontrado: " + e.getMessage());
         }
         RepositorioFileUtil.salvarArquivo(usuarios, this.fileName);
     }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuariosRepository that)) return false;
        return Objects.equals(usuarios, that.usuarios);
    }


}
