package dados;

import exceptions.*;
import negocio.beans.Pomodoro;
import negocio.beans.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PomodoroRepository implements IRepository<Pomodoro>{

    public String fileName;
    List<Pomodoro> pomodoros;

    public PomodoroRepository(String fileName){
        this.fileName = fileName;
        this.pomodoros = new ArrayList<>();

        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
        if (listaElementos != null && listaElementos instanceof List<?>){
            this.pomodoros = (List<Pomodoro>) listaElementos;
        }
    }
    @Override
    public List<Pomodoro> listarTodos() {
        return null;
    }

    @Override
    public Pomodoro listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Object listarPorNome(String nome) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public void adicionar(Pomodoro item) throws ElementoJaExisteException, ArgumentoInvalidoException {
        this.pomodoros.add(item);
    }

    @Override
    public void atualizar(Pomodoro item) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Pomodoro item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }
}
