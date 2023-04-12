package gui.controlers;

import gui.Main;
import gui.ScreamControl;
import javafx.fxml.FXML;

public class ControlerModificarTarefa {

    @FXML
    void fecha(){
        ControlerPrincipal.getInstance().fecharTela("RIGHT");
    }
}
