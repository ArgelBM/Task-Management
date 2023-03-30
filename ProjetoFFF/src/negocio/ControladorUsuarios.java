package negocio;

import dados.CategoriasRepository;
import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.util.List;

public class ControladorUsuarios {

    private UsuariosRepository repositorio;
    private static ControladorUsuarios instance;

    public ControladorUsuarios(){
        repositorio = new UsuariosRepository("usuarios.dat");
    }

    public static ControladorUsuarios getInstance(){
        if(instance == null){
            instance = new ControladorUsuarios();
        }
        return instance;
    }

    public void remover(Usuario obj) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        repositorio.remover(obj);

    }

    public void adicionar(Usuario obj) throws ElementoJaExisteException, ArgumentoInvalidoException {
        repositorio.adicionar(obj);
    }


    public List<Usuario> listarPorTodos() {
        return repositorio.listarTodos();
    }

    public Usuario listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException{
        return repositorio.listarPorId(id);
    }

    public void atualizar(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        repositorio.atualizar(usuario);
    }

}
