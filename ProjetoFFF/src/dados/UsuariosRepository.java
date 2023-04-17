package dados;

import exceptions.*;
import negocio.beans.Usuario;

import java.util.*;

public class UsuariosRepository implements IRepository<Usuario> {

    List<Usuario> usuarios;

    String fileName;

    private Usuario ultimoUsuario;

    public boolean lembreDeMim = false;

    public Usuario getUltimoUsuario() {
        return ultimoUsuario;
    }

    public boolean isLembreDeMim() {
        return lembreDeMim;
    }

    public UsuariosRepository(String fileName){
        this.usuarios = new ArrayList<>();
        this.fileName = fileName;

        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
        if (listaElementos != null && listaElementos instanceof List<?>){
            this.usuarios = (List<Usuario>) listaElementos;
        }
    }

    public Usuario fazerLogin(String login, String senha, boolean marcado) throws ArgumentoInvalidoException {
        if (login == null || senha == null) {
            throw new ArgumentoInvalidoException("Credenciais inválidas");
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                if(marcado){
                    ultimoUsuario = usuario;
                    lembreDeMim = true;
                }
                else{
                    ultimoUsuario = null;
                    lembreDeMim = false;
                }
                salvar();
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
    public Usuario listarPorNome(String nome) throws ElementoNaoEncontradoException {
        Optional<Usuario> usuariosEncontrados = usuarios.stream()
                .filter(u -> nome.equalsIgnoreCase(u.getNomeUsuario()))
                .findAny();
        if (usuariosEncontrados.isPresent()) {
            return usuariosEncontrados.get();
        } else {
            throw new ElementoNaoEncontradoException(usuariosEncontrados);
        }
    }

    @Override
    public void adicionar(Usuario usuario) throws ElementoJaExisteException, ArgumentoInvalidoException {
        if (usuario == null || usuario.getSenha().equals("") || usuario.getLogin().equals("") || usuario.getNomeUsuario().equals("") ||
        usuario.getDataNascimento() == null ) {
            throw new ArgumentoInvalidoException("Usuário não pode ser nulo");
        }
        for(Usuario a : usuarios){
            if (a.getLogin().equals(usuario.getLogin()) && a.getSenha().equals(usuario.getSenha())){
                throw new ElementoJaExisteException("Usuário já existe");
            }
        }
        usuarios.add(usuario);
        salvar();
    }


    @Override
    public void mudarNome(Usuario usuario, String nome) throws ElementoNaoEncontradoException {
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

         salvar();
     }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public String getFileName() {
        return fileName;
    }

    public void salvar(){
        RepositorioFileUtil.salvarArquivo(usuarios, this.fileName);
    }

}
