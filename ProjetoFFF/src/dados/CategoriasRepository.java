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
        return null;
    }

    @Override
    public void adicionar(Categoria item) throws ElementoJaExisteException, ArgumentoInvalidoException {
        this.categorias.add(item);
        RepositorioFileUtil.salvarArquivo(categorias, this.fileName);
        }

    @Override
    public void atualizar(Categoria item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Categoria item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {
        this.categorias.remove(item);
        RepositorioFileUtil.salvarArquivo(categorias, this.fileName);
        }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriasRepository that)) return false;
        return Objects.equals(categorias, that.categorias);
    }

}
