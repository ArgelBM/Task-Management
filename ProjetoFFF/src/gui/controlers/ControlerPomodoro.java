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


public class ControlerPomodoro implements Initializable{

    public void initialize(URL url, ResourceBundle resourceBundle) {
    if(ControladorPomodoro.ativo){
        iniciar();
    }
    }

    @FXML
    public Label contador;
    @FXML
    void iniciar() {
        if (!ControladorPomodoro.ativo) {
            ControladorPomodoro.getInstance().getTempoDescansoLongo();
        }
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        if (ControladorPomodoro.ativo) {
                            contador.setText(String.format("%02d:%02d", ControladorPomodoro.minutos
                                    , ControladorPomodoro.segundos));
                        }
                        if (!ControladorPomodoro.ativo) {
                            timer.cancel();
                        }
                    });
                }
            }, 0, 1000);
        }
    @FXML
    void parar() {
        ControladorPomodoro.getInstance().pare();
        contador.setText(String.format("%02d:%02d", ControladorPomodoro.minutos
                , ControladorPomodoro.segundos));
    }

}
