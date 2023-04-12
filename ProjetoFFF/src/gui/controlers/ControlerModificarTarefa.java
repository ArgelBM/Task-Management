package gui.controlers;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class ControlerModificarTarefa implements Initializable {

    @FXML
    private ContextMenu cm;

    @FXML
    private Button data;

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
}
