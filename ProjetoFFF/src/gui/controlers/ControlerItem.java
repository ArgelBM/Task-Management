package gui.controlers;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlerItem  {

    @FXML
    private CheckBox checkbox;

    @FXML
    private Button favoritar;

    @FXML
    private Label nomeLabel;

    @FXML
    private MaterialIconView star;

    @FXML
    public void setFavoritar() {
        if (star.getGlyphName().equals("STAR_BORDER")) {
            star.setGlyphName("STAR");
        } else {
            star.setGlyphName("STAR_BORDER");
        }
    }

    public void setNomeLabel(String nome) {
        nomeLabel.setText(nome);
    }
}
