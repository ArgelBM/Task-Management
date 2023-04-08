package gui.controlers;

import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import negocio.ControladorUsuarios;
import negocio.beans.Usuario;

public class ControlerCadastro {
    @FXML
    private Label erro;

    @FXML
    private DatePicker dataDeNascimento;

    @FXML
    private TextField login;

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
            ControladorUsuarios.getInstance().adicionar(new Usuario(null, userName.getText(), dataDeNascimento.getValue(), 123, login.getText(), senha.getText()));
            System.out.println("conta criada");
            Main.mudarTela("telaLogin");
        }
        catch (Exception a){
            erro.setText("*Erro ao criar conta");
            System.out.println("erro");
            System.out.println(a);
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

}
