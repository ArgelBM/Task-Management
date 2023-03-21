package negocio.beans;

import java.time.LocalDateTime;

public class Pomodoro {
    private int pomodoro;
    private int ciclo;
    private LocalDateTime tempo;
    private Task tarefa;
    private boolean concluido;

    public Pomodoro(int pomodoro, int ciclo, LocalDateTime tempo, Task tarefa, boolean concluido) {
        this.pomodoro = pomodoro;
        this.ciclo = ciclo;
        this.tempo = tempo;
        this.tarefa = tarefa;
        this.concluido = concluido;
    }

    public int getPomodoro() {
        return pomodoro;
    }

    public void setPomodoro(int pomodoro) {
        this.pomodoro = pomodoro;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDateTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalDateTime tempo) {
        this.tempo = tempo;
    }

    public Task getTarefa() {
        return tarefa;
    }

    public void setTarefa(Task tarefa) {
        this.tarefa = tarefa;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}
