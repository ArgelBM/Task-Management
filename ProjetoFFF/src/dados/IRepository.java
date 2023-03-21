package dados;
import exceptions.*;

import java.util.List;

public interface IRepository <T> {
//    listarTodos(): retorna uma lista com todos os itens cadastrados
    List<T> listarTodos();

//    listarPorId(int id): retorna o item com o id especificado.
    T listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException;

//    listarPorNome(String nome): retorna uma lista com os itens que possuem o nome especificado.
    Object listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException;

//    adicionar(T item): adiciona um novo item à lista.
    void adicionar(T item) throws ElementoJaExisteException, ArgumentoInvalidoException;

//    atualizar(T item): atualiza os dados de um item já existente na lista.
    void atualizar(T item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException;

//    remover(T item): remove um item da lista.
    void remover(T item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException;

}
