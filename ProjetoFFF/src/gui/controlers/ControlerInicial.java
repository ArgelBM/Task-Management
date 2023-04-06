package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerInicial implements Initializable {

    @FXML
    private BorderPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            trocarConteudoDireita("/gui/telas/TelaLogin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trocarConteudoDireita(String caminhoFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
        Parent root = loader.load();
        contentArea.setRight(root);
    }

    @FXML
    private void exibirTelaLogin(ActionEvent event) throws IOException {
        trocarConteudoDireita("/gui/telas/TelaLogin.fxml");
    }

    @FXML
    private void exibirTelaCadastro(ActionEvent event) throws IOException {
        trocarConteudoDireita("/gui/telas/TelaCadastro.fxml");
    }

}
