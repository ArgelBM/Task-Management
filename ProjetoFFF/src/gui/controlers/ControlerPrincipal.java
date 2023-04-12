package gui.controlers;

import gui.Main;
import gui.ScreamControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import negocio.ControladorTasks;
import negocio.ControladorUsuarios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControlerPrincipal implements Initializable {

    public ControlerPomodoro controlerTelaPomodoro;

    @FXML
    private BorderPane contentArea;

    @FXML
    private Label login;

    @FXML
    private Label nomeDeUsuario;

    private static ControlerPrincipal instance;

    public static ControlerPrincipal getInstance(){
        if(instance == null){
            instance = new ControlerPrincipal();
        }
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carregarTelaHoje();
            nomeDeUsuario.setText(ControladorTasks.getInstance().getUsuarioAtivo().getNomeUsuario());
            login.setText(ControladorTasks.getInstance().getUsuarioAtivo().getLogin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void sair() throws IOException {
        ScreamControl.getInstance().telaLogin();

    }

    public BorderPane getContentArea() {
        return contentArea;
    }

    public void carregarTela(String caminhoFXML, String posicao) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
        Parent root = loader.load();

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

    public void carregarTelaFiltro() throws IOException {
        carregarTela("/gui/telas/Filtro.fxml", "CENTER");
    }

    @FXML
    void telaFiltro(){
        try {
            carregarTelaFiltro();
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaHoje() throws IOException {
        carregarTela("/gui/telas/Hoje.fxml", "CENTER");

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

    @FXML
    public void carregarTelaPomodoro(String a) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/Pomodoro.fxml"));
        Parent root = loader.load();

        ControlerPomodoro b = loader.getController();
        b.contador.setText(a);
        contentArea.setCenter(root);


    }

    @FXML
    void telaPomodoro(){
        try {
            carregarTela("/gui/telas/Pomodoro.fxml", "CENTER");
        }catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    public void carregarTelaConfiguracao() throws IOException {
        carregarTela("/gui/telas/Configuracao.fxml", "CENTER");
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

    public void setContentArea(BorderPane contentArea) {
        this.contentArea = contentArea;
    }

}
