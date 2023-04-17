package gui.controlers;

import gui.Main;
import gui.ScreamControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import negocio.ControladorUsuarios;
import negocio.Fachada;

import java.io.IOException;
import java.util.Objects;


public class ControlerLogin{

    @FXML
    private Label testador;

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    void fazerLogin() {
        String usuario = login.getText();
        String key = senha.getText();
        try {
            Fachada.getInstance().fazerLogin(usuario, key);
            ScreamControl.getInstance().telaPrincipal();
        }
        catch (Exception a ){
            testador.setText("*usuario ou senha errados");
            System.out.println("erro ao fazer login");
            System.out.println(a);
        }
    }

    @FXML
    void criaNovaConta() {

        try {
            ControlerInicial.getInstance().carregarTelaCadastro();
        }
        catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    @FXML
    void fecha(){
            Main.fecharTela();
            if (ScreamControl.getInstance().getStage() != null) {
                ScreamControl.getInstance().fecharTela();
            }
    }

    @FXML
    void esqueceuSenha() throws IOException {
        ControlerInicial controlerInicial = ControlerInicial.getInstance();
        controlerInicial.carregarTela("/gui/telas/EsqueceuSenha.fxml", "RIGHT");
    }

    public void initialize() {
        senha.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fazerLogin();
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlerLogin that = (ControlerLogin) o;
        return Objects.equals(testador, that.testador) && Objects.equals(login, that.login) && Objects.equals(senha, that.senha);
    }

}
