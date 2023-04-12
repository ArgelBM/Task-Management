package gui.controlers;

import gui.ScreamControl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import negocio.ControladorPomodoro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class ControlerPomodoro{


    public int segundos = 0;
    public int minutos = 15;

    @FXML
    public Label contador;
    @FXML
    void iniciar() {
        ControladorPomodoro.getInstance().getTempoDescansoLongo();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {

                    if (segundos == 0) {
                        minutos--;
                        segundos = 60;
                    }
                    segundos--;
                    System.out.println("teste no timmer");
                    try {
                        ControlerPrincipal.getInstance().carregarTelaPomodoro(String.format("%02d:%02d", minutos, segundos));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Tempo decorrido: " + segundos + " segundos " + minutos + " minutos ");
                    if (!ControladorPomodoro.ativo) {
                        timer.cancel();
                        System.out.println("parou");
                    }
                });
            }
        },0 , 1000);
    }

    @FXML
    void parar() {
        ControladorPomodoro.getInstance().pare();

    }

}
