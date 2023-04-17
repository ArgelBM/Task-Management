package gui.controlers;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import negocio.ControladorUsuarios;
import negocio.Fachada;
import negocio.beans.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerCadastro implements Initializable {
    @FXML
    private Label erro;

    @FXML
    private DatePicker dataDeNascimento;

    @FXML
    private TextField login;

    @FXML
    private ChoiceBox<String> email;

    private ObservableList<String> emailsMaisUsados = FXCollections.observableArrayList(
            "@gmail.com",
            "@hotmail.com",
            "@outlook.com"
    );

    @FXML
    private TextField senha;

    @FXML
    private TextField userName;

    @FXML
    private Label validadorData;

    @FXML
    private Label validadorLogin;

    @FXML
    private Label validadorNome;

    @FXML
    private Label validadorSenha;

    @FXML
    boolean verificarArgumentos(){
        boolean v = false;
        if (this.login.getText().length() <= 10 && !login.getText().isEmpty()){
            validadorLogin.setText("ok!");
        }
        else{
            validadorLogin.setText("X");
        }
        if (this.senha.getText().length() <= 20 && !senha.getText().isEmpty()){
            validadorSenha.setText("ok!");
        }
        else{
            validadorSenha.setText("X");
        }
        if (this.userName.getText().length() <= 20 && !userName.getText().isEmpty()){
            validadorNome.setText("ok!");
        }
        else{
            validadorNome.setText("X");
        }
        if(this.login.getText().length() <= 10 && this.senha.getText().length() <= 20 &&
                this.userName.getText().length() <= 20 ){
            v = true;
        }
        return v;
    }

    @FXML
    void cadastrar() {

        try {
            Fachada.getInstance().adicionar(new Usuario( userName.getText(), dataDeNascimento.getValue(),
                    123, login.getText() + email.getValue(), senha.getText()));
            System.out.println("conta criada");
            ControlerInicial.getInstance().carregarTelaLogin();
        }
        catch (NullPointerException a){
            erro.setText("Os campos estão vazios");
            System.out.println("erro");
            System.out.println(a);
        }
        catch (IllegalArgumentException b){
            erro.setText("Esse usuário já existe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void fecha() {
        Main.fecharTela();
    }

    @FXML
    void login(){
        try {
            BorderPane contentArea = ControlerInicial.getInstance().getContentArea();
            ControlerInicial.getInstance().carregarTelaLogin();
        }
        catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        email.setItems(emailsMaisUsados);
        senha.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                cadastrar();
            }
        });
    }

}
