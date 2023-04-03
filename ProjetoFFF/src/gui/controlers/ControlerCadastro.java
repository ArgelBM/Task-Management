package gui.controlers;

import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import negocio.ControladorUsuarios;
import negocio.beans.Usuario;

import java.time.LocalDate;

public class ControlerCadastro {
    @FXML
    private Label erro;

    @FXML
    private TextField dataDeNascimento;

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
                this.userName.getText().length() <= 20 && verificarData() != null){
            v = true;
        }
        return v;
    }

    @FXML
    LocalDate verificarData(){
        LocalDate output = null;
        try {
            String x = dataDeNascimento.getText();
            String[] data = x.split("/");
            output = LocalDate.parse(String.format("%s-%s-%s",data[2],data[1],data[0]));
            validadorData.setText("ok!");
        }
        catch (Exception a){
            validadorData.setText("X");
        }
        return output;
    }

    @FXML
    void cadastrar() {

        try {
            ControladorUsuarios.getInstance().adicionar(new Usuario(null, userName.getText(), verificarData(), 123, login.getText(), senha.getText()));
            System.out.println("conta criada");
            Main.mudarTela("telaLogin");
        }
        catch (Exception a){
            erro.setText("Erro ao criar conta");
            System.out.println("erro");
            System.out.println(a);
        }
    }

    @FXML
    void fecha() {
        Main.fecharTela();
    }


    @FXML
    void volta() {
        Main.mudarTela("telaLogin");

    }

}
