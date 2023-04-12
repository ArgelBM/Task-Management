package negocio;

import dados.IRepository;
import dados.PomodoroRepository;
import javafx.application.Platform;
import negocio.beans.Pomodoro;

import java.util.Timer;
import java.util.TimerTask;

public class ControladorPomodoro {

    public IRepository<Pomodoro> repositorio;

//    public ControladorPomodoro(){
//        this.repositorio = new PomodoroRepository("repositoriopomodoro.dat");
//    }

    private static ControladorPomodoro instance;

    public static boolean ativo = false;



    //teste


    public static ControladorPomodoro getInstance(){
        if(instance == null){
            instance = new ControladorPomodoro();
        }
        return instance;
    }

    public void getTempoPomodoro( ) {

        Timer cronometro = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                ativo = false;
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
                ativo = false;
                System.out.println( "passou 5 min");
                //executar ação aqui
            }
        };
        int milissegundos = 5*60000; //5 min
        cronometro.schedule(tarefa, milissegundos);

    }

    public void getTempoDescansoLongo() {
        ativo = true;

        Timer cronometro = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    ativo = false;
                System.out.println( "passou 5 min");
                });
            }
        };
        int milissegundos = 15*60000; //25 min
        cronometro.schedule(tarefa, milissegundos);
    }


    public void iniciarPomodoro(Pomodoro pomodoro){

    }

    public void pare() {
        this.ativo = false;
    }
}
