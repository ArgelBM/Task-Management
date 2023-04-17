package negocio;

import alerta.AlertaSonoro;
import dados.IRepository;
import dados.PomodoroRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import javafx.application.Platform;
import negocio.beans.Pomodoro;
import java.util.Timer;
import java.util.TimerTask;

public class ControladorPomodoro {

    private PomodoroRepository repositorio;

   public ControladorPomodoro(){
    this.repositorio = new PomodoroRepository("repositoriopomodoro.dat");
}

    public static int segundos = 25;
    public static int minutos = 0;
    private static ControladorPomodoro instance;
    public static boolean ativo = false;

    private Pomodoro ultimoDosPomodoros;

    public void addPomodoro(Pomodoro pomodoro) throws ArgumentoInvalidoException, ElementoJaExisteException {
        repositorio.adicionar(pomodoro);
        ultimoDosPomodoros = repositorio.listarTodos().get(repositorio.listarTodos().size() -1);
    }


    public static ControladorPomodoro getInstance(){
        if(instance == null){
            instance = new ControladorPomodoro();
        }
        return instance;
    }

    public PomodoroRepository getRepositorio() {
        return repositorio;
    }

    public void getTempoPomodoro(Pomodoro pomodoro) {
        ativo = true;
        pause(25);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    if (!ControladorPomodoro.ativo) {
                        timer.cancel();
                    }
//                    if (segundos == 0) {
//                        minutos--;
//                        segundos += 60;
//                    }
                    segundos--;
                    System.out.println("Tempo decorrido: " + segundos + " segundos " + minutos + " minutos ");

                    if (minutos == 0 && segundos == 0){
                        timer.cancel();
                        if(pomodoro.getVinteCincoMin() - pomodoro.getTrintaMin()*4 <= 4 ){
                            pomodoro.addVinteCincoMin();
                            System.out.println("fez 25 min : " + pomodoro.getVinteCincoMin());
                            System.out.println("fez uma pausa de 5 e mudou para descansocurto");
                            getTempoDescanso(pomodoro);
                            System.out.println("iniciou pomodoro descanso");
                        }
                        else{
                            System.out.println("fez uma pausa de 5 e mudou para descansoLONGO");
                            pomodoro.addTrintaMin();
                            System.out.println("fez 30 min : " + pomodoro.getVinteCincoMin());
                            getTempoDescansoLongo(pomodoro);
                            System.out.println("iniciou pomodoro descansoLONGO");
                        }
                        try {
                            AlertaSonoro.getInstance().iniciarAlerta();
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                });
            }
        },0 , 1000);
    }

    public void getTempoDescanso(Pomodoro pomodoro) {
        ativo = true;
        pause(5);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
//                    if (segundos == 0) {
//                        minutos--;
//                        segundos += 60;
//                    }
                    segundos--;
                    System.out.println("Tempo decorrido: " + segundos + " segundos " + minutos + " minutos ");
                    if (!ControladorPomodoro.ativo) {
                        timer.cancel();
                    }
                    if (minutos == 0 && segundos == 0){
                        timer.cancel();
                        try {
                            AlertaSonoro.getInstance().iniciarAlerta();
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                        getTempoPomodoro(pomodoro);
                    }
                });
            }
        },0 , 1000);
    }

    public void getTempoDescansoLongo(Pomodoro pomodoro) {
        ativo = true;
        pause(30);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    if (!ControladorPomodoro.ativo) {
                        timer.cancel();
                    }
                    if (minutos == 0 && segundos == 0){
                        pause(25);
                        try {
                            AlertaSonoro.getInstance().iniciarAlerta();
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                        getTempoPomodoro(pomodoro);
                        timer.cancel();
                    }
//                    if (segundos == 0) {
//                        minutos--;
//                        segundos += 60;
//                    }
                    segundos--;
                    System.out.println("Tempo decorrido: " + segundos + " segundos " + minutos + " minutos ");
                });
            }
        },0 , 1000);
    }


    public void iniciarPomodoro(){
        getTempoPomodoro(this.ultimoDosPomodoros);
    }

    public void pare(int minutos){
        //Minutos no local errado para testes!!!
        ativo = false;
        //segundos = 0;
        //minutos = minutos;
        segundos = minutos;
        minutos = 0;
    }

    public void pause(int minutos){
        //segundos = 0;
        //minutos = minutos;

        segundos = minutos;
    }
    public void resume(int minutos){
        //segundos = 0;
        //minutos = minutos;

        segundos = minutos;
    }

}
