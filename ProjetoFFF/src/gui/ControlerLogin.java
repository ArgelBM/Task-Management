package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControlerLogin {

    @FXML
    private Label textoNaTela;

    @FXML
    void clicouNoBotao() {
        textoNaTela.setText("apareceu!");
        System.out.println("foi");
    }


}
