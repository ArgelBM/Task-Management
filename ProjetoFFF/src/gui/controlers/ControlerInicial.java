package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerInicial implements Initializable {

    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("telas/TelaLogin.fxml"));

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void TelaLogin(javafx.event.ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("telas/TelaLogin.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void TelaCadastro(javafx.event.ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("telas/TelaCadastro.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

}
