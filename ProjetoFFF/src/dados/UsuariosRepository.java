package dados;

import negocio.beans.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Usuario fazerLogin(String login, String senha, boolean marcado) throws NullPointerException, IllegalArgumentException {
        if (login == null || senha == null) {
            throw new NullPointerException("Credenciais inválidas");
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
        throw new IllegalArgumentException("Usuario ou senha incorretos");
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    @Override
    public Usuario listarPorId(int id) throws IllegalArgumentException {
            for (Usuario usuario : usuarios) {
                if (usuario.getId() == id) {
                    return usuario;
                }
            }
            throw new IllegalArgumentException("Credenciais inválidas");
        }

    @Override
    public Usuario listarPorNome(String nome) throws NoSuchElementException {
        Optional<Usuario> usuariosEncontrados = usuarios.stream()
                .filter(u -> nome.equalsIgnoreCase(u.getNomeUsuario()))
                .findAny();
        if (usuariosEncontrados.isPresent()) {
            return usuariosEncontrados.get();
        } else {
            throw new NoSuchElementException("Não existem usuários");
        }
    }

    @Override
    public void adicionar(Usuario usuario) throws NullPointerException, IllegalArgumentException {
        if (usuario == null || usuario.getSenha().equals("") || usuario.getLogin().equals("") || usuario.getNomeUsuario().equals("") ||
        usuario.getDataNascimento() == null ) {
            throw new NullPointerException();
        }
        for(Usuario a : usuarios){
            if (a.getLogin().equals(usuario.getLogin()) && a.getSenha().equals(usuario.getSenha())){
                throw new IllegalArgumentException();
            }
        }
        usuarios.add(usuario);
        salvar();
    }


    @Override
    public void mudarNome(Usuario usuario, String nome) throws IllegalArgumentException {
        if (usuario == null || nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Argumentos inválidos");
        }
        usuario.setNomeUsuario(nome);
        salvar();
    }

     @Override
     public void remover(Usuario usuario) {
         usuarios.remove(usuario);
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

    public Usuario procurarPorLogin(String nome) throws NoSuchElementException {
        Optional<Usuario> usuariosEncontrados = usuarios.stream()
                .filter(u -> nome.equalsIgnoreCase(u.getLogin()))
                .findAny();
        if (usuariosEncontrados.isPresent()) {
            return usuariosEncontrados.get();
        } else {
            throw new NoSuchElementException("Usuário não encontrado.");
        }
    }

}
