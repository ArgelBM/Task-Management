package negocio.beans;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro {
    private int pomodoro;
    private int ciclo;
    private LocalDateTime tempoInicial;


    public void adicionarPomodoro(){
        this.pomodoro++;
    }
    public int getPomodoro() {
        return pomodoro;
    }


    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }



}
