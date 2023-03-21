package dados;

import exceptions.*;
import negocio.beans.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosRepository implements IRepository<Usuario> {
    List<Usuario> usuarios = new ArrayList<>();

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
    public void atualizar(Usuario item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Usuario item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }
}
