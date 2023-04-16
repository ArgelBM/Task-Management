package gui.controlers;

import gui.ScreamControl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import negocio.ControladorPomodoro;
import negocio.Fachada;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class ControlerPomodoro implements Initializable{

    public void initialize(URL url, ResourceBundle resourceBundle) {
    if(Fachada.getInstance().getAtivo()){
        iniciar();
    }
    }

    @FXML
    public Label contador;
    @FXML
    void iniciar() {
        if (!Fachada.getInstance().getAtivo()) {
            Fachada.getInstance().getTempoDescansoLongo();
        }
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        if (Fachada.getInstance().getAtivo()) {
                            contador.setText(String.format("%02d:%02d", Fachada.getInstance().getMinutos()
                                    ,Fachada.getInstance().getSegundos()));
                        }
                        if (!Fachada.getInstance().getAtivo()) {
                            timer.cancel();
                        }
                    });
                }
            }, 0, 200);
        }
    @FXML
    void parar() {
        ControladorPomodoro.getInstance().pare();
        contador.setText(String.format("%02d:%02d", ControladorPomodoro.minutos
                , Fachada.getInstance().getSegundos()));
    }

}
