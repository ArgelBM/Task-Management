package negocio.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro implements Serializable {

    private Task task;
    private LocalDateTime tempoInicial;

    public Pomodoro(Task task){
        this.task = task;
    }

    public Pomodoro(){}






}
