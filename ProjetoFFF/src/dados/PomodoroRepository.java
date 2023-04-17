package dados;

import exceptions.ArgumentoInvalidoException;
import exceptions.AtualizacaoFalhouException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Pomodoro;
import negocio.beans.Task;

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
        return pomodoros;
    }

    @Override
    public Pomodoro listarPorId(int id) throws ElementoNaoEncontradoException, ArgumentoInvalidoException {
        return null;
    }

    @Override
    public Pomodoro listarPorNome(String nome) throws ElementoNaoEncontradoException {
        return null;
    }

    @Override
    public void adicionar(Pomodoro item) throws ElementoJaExisteException, ArgumentoInvalidoException {
        this.pomodoros.add(item);
        salvar();
    }

    @Override
    public void mudarNome(Pomodoro item, String nome) throws AtualizacaoFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }

    @Override
    public void remover(Pomodoro item) {

    }

    public Pomodoro pomodoroBuscarPorTask(Task task){
        for (Pomodoro pomodoro : pomodoros){
            if (pomodoro.getTask().equals(task)){
                return pomodoro;
            }
        }
        return null;
    }

    public void salvar(){
        RepositorioFileUtil.salvarArquivo(pomodoros, this.fileName);
    }
}
