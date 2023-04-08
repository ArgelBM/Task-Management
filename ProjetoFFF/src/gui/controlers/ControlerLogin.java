package gui.controlers;

import gui.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import negocio.ControladorUsuarios;

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

            ControladorUsuarios.getInstance().fazerLogin(usuario, key);
            testador.setText("*Bem vindo "+ usuario);
//            Main.mudarTela("telaPrincipal");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../telas/telaPrincipal.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Main.fecharTela();

        }
        catch (Exception a ){
            testador.setText("*usurio ou senha errados");
            System.out.println("erro ao fazer login");
            System.out.println(a);
        }
        }

    @FXML
    void criaNovaConta() {

        try {
            BorderPane contentArea = ControlerInicial.getInstance().getContentArea();
            ControlerInicial.getInstance().carregarTelaCadastro();
        }
        catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    @FXML
    void fecha() {
        Main.fecharTela();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlerLogin that = (ControlerLogin) o;
        return Objects.equals(testador, that.testador) && Objects.equals(login, that.login) && Objects.equals(senha, that.senha);
    }

}
