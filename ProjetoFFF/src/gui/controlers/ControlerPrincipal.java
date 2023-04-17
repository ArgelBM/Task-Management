package gui.controlers;

import gui.Main;
import gui.ScreamControl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import negocio.ControladorTasks;
import negocio.ControladorUsuarios;
import negocio.Fachada;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControlerPrincipal implements Initializable {

    @FXML
    private BorderPane contentArea;

    @FXML
    private Label login;

    @FXML
    private Label nomeDeUsuario;

    public static Object ultimoControlador;

    private static ControlerPrincipal instance;

    public static ControlerPrincipal getInstance(){
        if(instance == null){
            instance = new ControlerPrincipal();
        }
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //requisita foco no clique pra o textfield de modificaTarefa funcionar
        contentArea.setOnMouseClicked(event->{
            contentArea.requestFocus();
        });

        try {
            carregarTelaHoje();
            nomeDeUsuario.setText(Fachada.getInstance().getUsuarioAtivo().getNomeUsuario());
            login.setText(Fachada.getInstance().getUsuarioAtivo().getLogin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BorderPane getContentArea() {
        return contentArea;
    }

    public void carregarTela(String caminhoFXML, String posicao) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
        Parent root = loader.load();
        ultimoControlador = loader.getController();

        switch (posicao) {
            case "TOP" -> contentArea.setTop(root);
            case "LEFT" -> contentArea.setLeft(root);
            case "BOTTOM" -> contentArea.setBottom(root);
            case "RIGHT" -> contentArea.setRight(root);
            case "CENTER" -> contentArea.setCenter(root);
            default -> throw new IllegalArgumentException("Posição inválida");
        }
    }

    public void carregarTelaEmbreve() throws IOException {
        carregarTela("/gui/telas/Embreve.fxml", "CENTER");
        fecharTela("RIGHT");
    }

    @FXML
    void telaEmbreve(){
        try {
            carregarTelaEmbreve();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaHoje() throws IOException {
        carregarTela("/gui/telas/Hoje.fxml", "CENTER");
        fecharTela("RIGHT");

    }

    @FXML
    void telaHoje(){
        try {
            carregarTelaHoje();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaImportante() throws IOException {
        carregarTela("/gui/telas/Importante.fxml", "CENTER");
        fecharTela("RIGHT");
    }

    @FXML
    void telaImportante(){
        try {
            carregarTelaImportante();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaPomodoro() throws IOException {
        carregarTela("/gui/telas/Pomodoro.fxml", "CENTER");
        fecharTela("RIGHT");

    }

    @FXML
    void telaPomodoro(){
        try {
            carregarTelaPomodoro();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaConfiguracao() throws IOException {
        carregarTela("/gui/telas/Configuracao.fxml", "CENTER");
        fecharTela("RIGHT");
    }

    @FXML
    void telaConfiguracao(){
        try {
            carregarTelaConfiguracao();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaRelatorios() throws IOException {
        carregarTela("/gui/telas/Relatorios.fxml", "CENTER");
        fecharTela("RIGHT");
    }

    @FXML
    void telaRelatorios(){
        try {
            carregarTelaRelatorios();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaTarefas() throws IOException {
        carregarTela("/gui/telas/Tarefas.fxml", "CENTER");
        fecharTela("RIGHT");
    }

    @FXML
    void telaTarefas(){
        try {
            carregarTelaTarefas();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void setContentArea(BorderPane contentArea) {
        this.contentArea = contentArea;
    }

    public void fecharTela(String posicao){
        switch (posicao) {
            case "TOP" -> contentArea.setTop(null);
            case "LEFT" -> contentArea.setLeft(null);
            case "BOTTOM" -> contentArea.setBottom(null);
            case "RIGHT" -> contentArea.setRight(null);
            case "CENTER" -> contentArea.setCenter(null);
            default -> throw new IllegalArgumentException("Posição inválida");
        }
    }

    public Object getUltimoControlador() {
        return ultimoControlador;
    }

    public void setUltimoControlador(Object ultimoControlador) {
        ControlerPrincipal.ultimoControlador = ultimoControlador;
    }
}
