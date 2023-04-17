package gui.controlers;

import gui.Main;
import gui.ScreamControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import negocio.ControladorUsuarios;
import negocio.Fachada;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class ControlerLogin implements Initializable {

    @FXML
    private Label testador;

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    private CheckBox checkBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        senha.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fazerLogin();
            }
        });

        login.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fazerLogin();
            }
        });


        if  (Fachada.getInstance().getRepositorioUsuarios().lembreDeMim){
            checkBox.setSelected(true);
            login.setText(Fachada.getInstance().getRepositorioUsuarios().getUltimoUsuario().getLogin());
            senha.setText(Fachada.getInstance().getRepositorioUsuarios().getUltimoUsuario().getSenha());
        }
        try {
            if (ControladorUsuarios.getInstance().getUsersLogin().getUltimoUsuario() != null &&
                    !checkBox.isSelected()){
                checkBox.setSelected(true);
                login.setText(ControladorUsuarios.getInstance().getUsersLogin().getUltimoUsuario().getLogin());
                senha.setText(ControladorUsuarios.getInstance().getUsersLogin().getUltimoUsuario().getSenha());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    void fazerLogin() {
        String usuario = login.getText();
        String key = senha.getText();
        boolean b = checkBox.isSelected();
        try {
            Fachada.getInstance().fazerLogin(usuario, key, b);
            ScreamControl.getInstance().telaPrincipal();
        }
        catch (Exception a ){
            testador.setText("*usuario ou senha errados");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlerLogin that = (ControlerLogin) o;
        return Objects.equals(testador, that.testador) && Objects.equals(login, that.login) && Objects.equals(senha, that.senha);
    }



}
