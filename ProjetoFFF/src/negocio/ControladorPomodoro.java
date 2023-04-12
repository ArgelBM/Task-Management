package negocio;

import dados.IRepository;
import dados.PomodoroRepository;
import gui.controlers.ControlerPrincipal;
import javafx.application.Platform;
import negocio.beans.Pomodoro;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ControladorPomodoro {

    public IRepository<Pomodoro> repositorio;

//    public ControladorPomodoro(){
//        this.repositorio = new PomodoroRepository("repositoriopomodoro.dat");
//    }

    public static int segundos = 0;
    public static int minutos = 15;

    private static ControladorPomodoro instance;

    public static boolean ativo = false;


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

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    if (!ControladorPomodoro.ativo) {
                        timer.cancel();
                    }
                    if (minutos == 0 && segundos == 0){
                        System.out.println("teste");
                        pare();
                        timer.cancel();
                    }
                    if (segundos == 0) {
                        minutos--;
                        segundos += 60;
                    }
                    segundos--;
                    System.out.println("Tempo decorrido: " + segundos + " segundos " + minutos + " minutos ");
                });
            }
        },0 , 1000);
    }


    public void iniciarPomodoro(Pomodoro pomodoro){

    }

    public void pare() {
        ativo = false;
        segundos = 0;
        minutos = 15;
    }
}
