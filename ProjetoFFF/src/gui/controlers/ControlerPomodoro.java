package gui.controlers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import negocio.ControladorPomodoro;
import negocio.ControladorTasks;
import negocio.Fachada;
import negocio.beans.Pomodoro;
import negocio.beans.Task;
import java.net.URL;
import java.util.*;


public class ControlerPomodoro implements Initializable{

    public void initialize(URL url, ResourceBundle resourceBundle) {
    if(Fachada.getInstance().getAtivo()){
        iniciar();
    }
        choiceBox.getItems().addAll(gerarLista());
    }

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    public Label contador;
    @FXML
    void iniciar() {
        if (!Fachada.getInstance().getAtivo()) {
            try {
                if(choiceBox != null){
                    ControladorPomodoro.getInstance().addPomodoro(new Pomodoro(ControladorTasks
                            .getInstance().procurarPorNome(choiceBox.getValue())));
                }
                else {
                    ControladorPomodoro.getInstance().addPomodoro(new Pomodoro());
                }
                ControladorPomodoro.getInstance().iniciarPomodoro();
            }
            catch (Exception e){
                e.printStackTrace();
            }
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

        public void pause(){

        }

    @FXML
    void parar(){
        ControladorPomodoro.getInstance().pare(25);
        contador.setText(String.format("%02d:%02d", ControladorPomodoro.minutos
                , Fachada.getInstance().getSegundos()));
    }

    private List<String> gerarLista(){
        List<String> listaStringDeTask = new ArrayList<>();
        listaStringDeTask.add("Iniciar sem tasks");
        for(Task task : Fachada.getInstance().getUsuarioAtivo().getTask().listarTodos()){
            listaStringDeTask.add(task.getNome());
            System.out.println(task.getNome());
        }
        return listaStringDeTask;
    }



}
