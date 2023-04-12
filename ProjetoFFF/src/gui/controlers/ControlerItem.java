package gui.controlers;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

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
    private HBox item;

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

    @FXML
    void modifica() throws IOException {
        ControlerPrincipal controlerPrincipal = ControlerPrincipal.getInstance();
        controlerPrincipal.carregarTela("/gui/telas/ModificarTarefa.fxml", "RIGHT");
    }

    @FXML
    void mudaCor() {
        item.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    void mudaCorDeVolta() {
        item.setStyle("-fx-background-color: #F2EEF2;");
    }
}
