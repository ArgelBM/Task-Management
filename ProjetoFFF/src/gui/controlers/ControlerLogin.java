package gui.controlers;

import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import negocio.ControladorUsuarios;
import negocio.beans.Usuario;

import java.util.Objects;


public class ControlerLogin{


    @FXML
    private Label testador;

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    void fecharTela(){
        Main.fecharTela();
    }

    @FXML
    void fazerLogin() {


        String usuario = login.getText();
        String key = senha.getText();

        try {
            ControladorUsuarios.getInstance().fazerLogin(usuario, key);
            testador.setText("Bem vindo "+ usuario);
            Main.mudarTela("telaPrincipal");

        }
        catch (Exception a ){
            testador.setText("usurio ou senha errados");
            System.out.println("erro ao fazer login");
            System.out.println(a);
        }
        }

    @FXML
    void criaNovaConta() {

        String usuario = login.getText();
        String key = senha.getText();

        try {
            ControladorUsuarios.getInstance().adicionar(new Usuario(null, "teste", null, 123, usuario, key));
            System.out.println("conta criada");
        }
        catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlerLogin that = (ControlerLogin) o;
        return Objects.equals(testador, that.testador) && Objects.equals(login, that.login) && Objects.equals(senha, that.senha);
    }

}
