package gui.controlers;


import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoNaoEncontradoException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import negocio.ControladorTasks;
import negocio.beans.Task;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.event.FocusEvent.*;

public class ControlerModificarTarefa implements Initializable {

    @FXML
    private CheckBox checkbox;

    @FXML
    private TextField label;

    @FXML
    private MaterialIconView star;

    @FXML
    private ContextMenu cm;

    @FXML
    private Button data;

    private Task task;

    public void setTask(Task task){
        this.task = task;
        mudarNome(task.getNome());
        checarStar();
        checarCheckbox();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Se der enter atualiza a tarefa
        label.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                registrarNovoValor(label);
            }
        });

        data.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        data.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                double posx = data.localToScreen(data.getBoundsInLocal()).getCenterX();
                double posy = data.localToScreen(data.getBoundsInLocal()).getMaxY();
                cm.show(data, posx, posy);
            }
        });

        ControladorTasks.getInstance().addChangeListener(tasks -> {
            checarStar();
            checarCheckbox();
        });
    }

    @FXML
    void fecha(){
        ControlerPrincipal.getInstance().fecharTela("RIGHT");
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

    public void mudarNome(String nome) {
        label.setText(nome);
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

    private void checarStar(){
        if("importante".equals(task.getClassificacao().getPrioridadeDaTask())){
            setStar("STAR");
        }
        else {
            setStar("STAR_BORDER");
        }
    }

    private void checarCheckbox(){
        if("concluida".equals(task.getClassificacao().getStatusDaTask())){
            checkbox.setSelected(true);
        }
        else {
            checkbox.setSelected(false);
        }
    }

    private void registrarNovoValor(TextField textField) {
        String novoValor = textField.getText();
        task.setNome(novoValor);
        try {
            ControladorTasks.getInstance().mudarNome(task, novoValor);
        } catch (ArgumentoInvalidoException e) {
            System.out.println("Argumento invalido");
        } catch (ElementoNaoEncontradoException e) {
            System.out.println("task nao encontrada");
        }
        textField.setText(novoValor);
        label.getParent().requestFocus();
    }



}

