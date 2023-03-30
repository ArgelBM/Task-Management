package dados;

import exceptions.*;
import negocio.beans.Categoria;

import java.util.*;

public class CategoriasRepository implements IRepository<Categoria> {

    List<Categoria> categorias;
    String fileName;

    public CategoriasRepository(String fileName){
        this.categorias = new ArrayList<>();
        this.fileName = fileName;

        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
        if (listaElementos != null && listaElementos instanceof List<?>){
            this.categorias = (List<Categoria>) listaElementos;
        }
    }


    @Override
    public List<Categoria> listarTodos() {
        return this.categorias;
    }

    @Override
    public Categoria listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Categoria listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        Categoria b = null;
        for( Categoria a: categorias) {
            if (a.getNome().equals(nome)) {
                b = a;
            }
        }
        return b;
    }

    @Override
    public void adicionar(Categoria item) throws ElementoJaExisteException, ArgumentoInvalidoException {
            try {
                categorias.add(item);
            } catch (InputMismatchException e) {
                throw new ArgumentoInvalidoException("Falha ao adicionar item: " + e.getMessage());
            } catch (NoSuchElementException e) {
                throw new ArgumentoInvalidoException("Elemento não encontrado: " + e.getMessage());
            }

        RepositorioFileUtil.salvarArquivo(categorias, this.fileName);
        }

    @Override
    public void atualizar(Categoria item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {
        int index = this.categorias.indexOf(item);
        if(index >= 0){
            categorias.set(index, item);
        }
        RepositorioFileUtil.salvarArquivo(categorias, this.fileName);
    }

    @Override
    public void remover(Categoria item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {
            try {
                categorias.remove(item);
            } catch (InputMismatchException e) {
                throw new DeletarFalhouException("Falha ao deletar item: " + e.getMessage());
            } catch (NoSuchElementException e) {
                throw new ElementoNaoEncontradoException("Elemento não encontrado: " + e.getMessage());
            }
        RepositorioFileUtil.salvarArquivo(categorias, this.fileName);
        }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriasRepository that)) return false;
        return Objects.equals(categorias, that.categorias);
    }

}
