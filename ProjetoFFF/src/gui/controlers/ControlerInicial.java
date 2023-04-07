package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerInicial implements Initializable {

    @FXML
    private BorderPane contentArea;

    private static ControlerInicial instance;

    public static ControlerInicial getInstance(){
        if(instance == null){
            instance = new ControlerInicial();
        }
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carregarTelaCadastro();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BorderPane getContentArea() {
        return contentArea;
    }

    public void carregarTela(String caminhoFXML, String posicao) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
        Parent root = loader.load();
        switch (posicao) {
            case "TOP" -> contentArea.setTop(root);
            case "LEFT" -> contentArea.setLeft(root);
            case "BOTTOM" -> contentArea.setBottom(root);
            case "RIGHT" -> contentArea.setRight(root);
            default -> throw new IllegalArgumentException("Posição inválida");
        }
    }

    public void carregarTelaLogin() throws IOException {
        carregarTela("/gui/telas/TelaLogin.fxml", "RIGHT");
    }

    public void carregarTelaCadastro() throws IOException {
        carregarTela("/gui/telas/TelaCadastro.fxml", "RIGHT");
    }

}
