package dados;

import exceptions.*;
import negocio.beans.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriasRepository implements IRepository {

    ArrayList<Categoria> categorias = new ArrayList<>();


    //    listarTodos(): retorna uma lista com todos os itens cadastrados
    public List listarTodos() {

        return categorias;
    }

    //    listarPorId(int id): retorna o item com o id especificado.
    public Categoria listarPorId(int id)throws ElementoNaoEncontradoException, ArgumentoInvalidoException {

        return null;
    }
    //    listarPorNome(String nome): retorna uma lista com os itens que possuem o nome especificado.
    public Categoria listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {

        Categoria b = null;
        for( Categoria a: categorias) {
            if (a.getNome() == nome) {

                b = a;

            }
        }
        return b;
    }

    //    adicionar(T item): adiciona um novo item à lista.
    public void adicionar(Categoria a) throws ElementoJaExisteException, ArgumentoInvalidoException{
        categorias.add(a);
    }

    //    atualizar(T item): atualiza os dados de um item já existente na lista.
    public void atualizar(Categoria a) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{

    }

    //    remover(T item): remove um item da lista.
    public void remover(Categoria a) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException{

                categorias.remove(a);

    }
}
