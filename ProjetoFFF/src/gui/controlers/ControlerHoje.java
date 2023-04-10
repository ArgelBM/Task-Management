package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerHoje implements Initializable {
    @FXML
    private VBox tarefas;

    @FXML
    private TextField novaTarefa;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void adicionarTarefa() {
        if (!novaTarefa.getText().isEmpty()) {
            try {
                Node item = FXMLLoader.load(getClass().getResource("/gui/telas/Item.fxml"));
                tarefas.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
            novaTarefa.clear();
        }
    }
}
