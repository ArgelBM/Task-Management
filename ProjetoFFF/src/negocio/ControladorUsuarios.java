package negocio;

import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.util.List;

public class ControladorUsuarios {
    private static ControladorUsuarios instance;

    public static ControladorUsuarios getInstance(){
        if(instance == null){
            instance = new ControladorUsuarios();
        }
        return instance;
    }

    public static void remover(Usuario obj) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        UsuariosRepository repo = new UsuariosRepository();
        repo.remover(obj);


    }

    public static void adicionar(Usuario obj) throws ElementoJaExisteException, ArgumentoInvalidoException {
        UsuariosRepository repo = new UsuariosRepository();
        repo.adicionar(obj);

    }


    public static List<Usuario> listarPorTodos() {
        UsuariosRepository repo = new UsuariosRepository();
        return repo.listarTodos();
    }

    public static Usuario listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException{
        UsuariosRepository repo = new UsuariosRepository();
        return repo.listarPorId(id);
    }

    public static void atualizar(Usuario usuario) throws ArgumentoInvalidoException, ElementoNaoEncontradoException {
        UsuariosRepository repo = new UsuariosRepository();
        repo.atualizar(usuario);
    }

}
