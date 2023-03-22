package dados;

import exceptions.*;
import negocio.beans.Pomodoro;

import java.util.ArrayList;
import java.util.List;

public class PomodoroRepository implements IRepository<Pomodoro>{
    List<Pomodoro> pomodoros = new ArrayList<>();

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
        if (item == null) {
            throw new ArgumentoInvalidoException(item);
        }

        boolean atualizado = false;

        for (Pomodoro p : this.pomodoros) {
            if (p.getTempo().equals(item.getTempo())) {
                this.pomodoros.set(this.pomodoros.indexOf(p), item);
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
            throw new ElementoNaoEncontradoException(item);
        }
    }

    @Override
    public void remover(Pomodoro item) throws DeletarFalhouException, ElementoNaoEncontradoException, ArgumentoInvalidoException {

    }
}
