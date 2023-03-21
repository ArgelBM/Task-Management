package dados;

import exceptions.*;
import negocio.beans.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosRepository implements IRepository<UsuariosRepository> {
    List<Usuario> usuarios = new ArrayList<>();

    @Override
    public List<UsuariosRepository> listarTodos() {
        return null;
    }

    @Override
    public UsuariosRepository listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Object listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public void adicionar(UsuariosRepository item) throws ElementoJaExisteException, ArgumentoInvalidoException {

    }

    @Override
    public void atualizar(UsuariosRepository item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(UsuariosRepository item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }
}
