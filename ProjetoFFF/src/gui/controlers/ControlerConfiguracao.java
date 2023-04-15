package gui.controlers;

import gui.ScreamControl;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControlerConfiguracao {


    @FXML
    void sair() throws IOException {
        ScreamControl.getInstance().telaLogin();

    }
}
