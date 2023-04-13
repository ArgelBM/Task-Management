package gui.controlers;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import negocio.ControladorTasks;
import negocio.beans.Task;

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

    private Task task;

    public void setTask(Task task){
        this.task = task;
        setNomeLabel(task.getNome());
    }

    @FXML
    public void setConcluida(){
        if (checkbox.isSelected()) {
            ControladorTasks.getInstance().marcarComoConcluida(task);
            System.out.println("marcou como concluida");
        } else {
            ControladorTasks.getInstance().desmarcarComoConcluida(task);
            System.out.println("desmarcou como concluida");
        }
    }

    @FXML
    public void setFavoritar() {
        if (star.getGlyphName().equals("STAR_BORDER")) {
            gravaStar("STAR");
        } else {
            gravaStar("STAR_BORDER");
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

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void gravaStar(String glyphName) {
        setStar(glyphName);
        if ("STAR".equals(glyphName)) {
            ControladorTasks.getInstance().marcarComoImportante(task);
            System.out.println("marcou como importante");
        }
        else {
            ControladorTasks.getInstance().desmarcarComoImportante(task);
            System.out.println("desmarcou como importante");
        }
    }

    public void setStar(String glyphName) {
        star.setGlyphName(glyphName);
    }
}
