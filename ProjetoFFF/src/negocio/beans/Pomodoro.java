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


    public void getTempoPomodoro() {

        Timer cronometro = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                pomodoro++;

                System.out.println( "Descanso de 5 min");
                //executar ação aqui
            }
        };
        int milissegundos = 25*60000; //25 min
        cronometro.schedule(tarefa, milissegundos);

    }

    public void getTempoDescanso() {

        Timer cronometro = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {

                System.out.println( "estude 25 min");
                //executar ação aqui
            }
        };
        int milissegundos = 5*60000; //25 min
        cronometro.schedule(tarefa, milissegundos);

    }

    public void getTempoDescansoLongo() {
        Timer cronometro = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {

                ciclo++;
                System.out.println( "estude 25 min");
                //executar ação aqui
            }
        };
        int milissegundos = 15*60000; //25 min
        cronometro.schedule(tarefa, milissegundos);

    }

    public void iniciarPomodoro(){

        getTempoPomodoro();
        getTempoDescanso();
        getTempoPomodoro();
        getTempoDescanso();
        getTempoPomodoro();
        getTempoDescanso();
        getTempoDescansoLongo();
        System.out.println("fim!");

    }
}
