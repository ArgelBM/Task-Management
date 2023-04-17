package gui.controlers;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import exceptions.ElementoNaoEncontradoException;
import gui.ScreamControl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import negocio.ControladorTasks;
import negocio.Fachada;
import negocio.beans.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControlerItem  {

    private static ControlerItem instance;

    public static ControlerItem getInstance(){
        if(instance == null){
            instance = new ControlerItem();
        }
        return instance;
    }

    @FXML
    private CheckBox checkbox;

    @FXML
    private Button favoritar;

    @FXML
    private Label nomeLabel;

    @FXML
    private Label data;

    @FXML
    private Label lista;

    @FXML
    private MaterialIconView star;

    @FXML
    private HBox item;

    @FXML
    private Circle bola;

    private Task task;

    public void setTask(Task task){
        this.task = task;
        setNomeLabel(task.getNome());
        setData(dataDaTask());
    }

    @FXML
    public void setConcluida(){
        if (checkbox.isSelected()) {
            Fachada.getInstance().marcarComoConcluida(task);
            System.out.println("marcou como concluida");
        } else {
            Fachada.getInstance().desmarcarComoConcluida(task);
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
        String nomeTarefa = nomeLabel.getText();
        System.out.println("Nome da label: " + nomeTarefa);

        ControlerPrincipal controlerPrincipal = ControlerPrincipal.getInstance();
        System.out.println("Pegou a instancia de principal");
        controlerPrincipal.carregarTela("/gui/telas/ModificarTarefa.fxml", "RIGHT");
        System.out.println("carregou a tela a direita");


       ControlerModificarTarefa controlerModificarTarefa = (ControlerModificarTarefa) controlerPrincipal.getUltimoControlador();
        System.out.println("Pegou a instancia de modifica");

        try{
            Task tarefaEspecifica = Fachada.getInstance().procurarPorNome(nomeTarefa);
            System.out.println("Nome da tarefa: " + tarefaEspecifica.getNome());
            controlerModificarTarefa.setTask(tarefaEspecifica);
            System.out.println("chamou modifica");
        }catch (ElementoNaoEncontradoException e){
            e.printStackTrace();
        }

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
            Fachada.getInstance().marcarComoImportante(task);
            System.out.println("marcou como importante");
        }
        else {
            Fachada.getInstance().desmarcarComoImportante(task);
            System.out.println("desmarcou como importante");
        }
    }

    public void setStar(String glyphName) {
        star.setGlyphName(glyphName);
    }

    public void setData(String nome) {
        if(" ".equals(nome)){
            data.setVisible(false);
            bola.setVisible(false);
        }
        data.setText(nome);
    }

    public String dataDaTask(){
        if(task.getDataPrevisao() == null){
            return " ";
        }
        LocalDate data = task.getDataPrevisao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        String dataFormat = data.format(formatter);
        if (data.isEqual(LocalDate.now())){
            return "Hoje";
        } else if (data.isEqual(LocalDate.now().plusDays(1))) {
            return "Amanhã";
        } else{
            return dataFormat;
        }
    }

    public void setLista(Label lista) {
        this.lista = lista;
    }
}
