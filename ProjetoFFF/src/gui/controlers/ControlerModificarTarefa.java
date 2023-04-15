package gui.controlers;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import negocio.ControladorTasks;
import negocio.beans.Task;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerModificarTarefa implements Initializable {

    @FXML
    private VBox tarefa;

    @FXML
    private AnchorPane teste;

    @FXML
    private ContextMenu cm;

    @FXML
    private Button data;

//    private static ControlerModificarTarefa instance;
//
//    public static ControlerModificarTarefa getInstance() {
//        if(instance == null){
//            instance = new ControlerModificarTarefa();
//        }
//        return instance;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        data.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                double posx = data.localToScreen(data.getBoundsInLocal()).getCenterX();
                double posy = data.localToScreen(data.getBoundsInLocal()).getMaxY();
                cm.show(data, posx, posy);
            }
        });
    }

    @FXML
    void fecha(){
        ControlerPrincipal.getInstance().fecharTela("RIGHT");
    }

    public void modificatarefa(Task task) {
        try {
            FXMLLoader tela = new FXMLLoader(getClass().getResource("/gui/telas/Item.fxml"));
            HBox item = tela.load();
            ControlerItem controlerItem = tela.getController();
            controlerItem.setTask(task);
            if("importante".equals(task.getClassificacao().getPrioridadeDaTask())){
                controlerItem.setStar("STAR");
            }
            else {
                controlerItem.setStar("STAR_BORDER");
            }
            teste.getChildren().add(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

