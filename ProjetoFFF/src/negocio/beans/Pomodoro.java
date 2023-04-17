package negocio.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro implements Serializable {

    private Task task;

    private int vinteCincoMin;

    private int cincoMin;

    private int trintaMin;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getVinteCincoMin() {
        return vinteCincoMin;
    }

    public void addVinteCincoMin() {
        this.vinteCincoMin++;
    }

    public int getCincoMin() {
        return cincoMin;
    }

    public void setCincoMin() {
        this.cincoMin++;
    }

    public int getTrintaMin() {
        return trintaMin;
    }

    public void addTrintaMin() {
        this.trintaMin++;
    }

    private LocalDateTime tempoInicial;

    public Pomodoro(Task task){
        this.task = task;
        vinteCincoMin = 0;
        cincoMin = 0;
        trintaMin = 0;
        tempoInicial = LocalDateTime.now();
    }

    public Pomodoro(){
        task = null;
        vinteCincoMin = 0;
        cincoMin = 0;
        trintaMin = 0;
        tempoInicial = LocalDateTime.now();
    }




}
