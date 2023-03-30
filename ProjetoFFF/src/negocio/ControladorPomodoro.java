package negocio;

import dados.PomodoroRepository;
import negocio.beans.Pomodoro;
import negocio.beans.Task;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ControladorPomodoro {

    public PomodoroRepository repository;

    public ControladorPomodoro(){
        this.repository = new PomodoroRepository("repositoriopomodoro.dat");
    }

    private static ControladorPomodoro instance;

    public static ControladorPomodoro getInstance(){
        if(instance == null){
            instance = new ControladorPomodoro();
        }
        return instance;
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
        int milissegundos = 5*60000; //5 min
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
        int milissegundos = 15*60000; //15 min
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
