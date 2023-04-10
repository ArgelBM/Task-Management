package gui.controlers;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlerItem  {

    @FXML
    private CheckBox checkbox;

    @FXML
    private MaterialIconView favoritar;

    @FXML
    private Label nomeLabel;

    public void setNomeLabel(String nome) {
        nomeLabel.setText(nome);
    }
}
