package negocio.beans;

import java.time.LocalDateTime;

public class Pomodoro {
    private int pomodoro;
    private int ciclo;
    private LocalDateTime tempo;
    private Task task;

    public Pomodoro(int pomodoro, int ciclo, LocalDateTime tempo, Task task) {
        this.pomodoro = pomodoro;
        this.ciclo = ciclo;
        this.tempo = tempo;
        this.task = task;
    }

    public void adicionarPomodoro(){
        this.pomodoro++;
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

    public Task getTask() {
        return task;
    }

    public void setTarefa(Task task) {
        this.task = task;
    }

    public static int getTempoPomodoro() {
        return 25;
    }

    public static int getTempoDescanso() {
        return 5;
    }

    public static int getTempoDescansoLongo() {
        return 25;
    }
}
