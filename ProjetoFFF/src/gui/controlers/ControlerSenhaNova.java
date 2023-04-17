package gui.controlers;

import gui.Main;
import gui.ScreamControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import negocio.ControladorTasks;
import negocio.ControladorUsuarios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerSenhaNova implements Initializable {

    @FXML
    private PasswordField confirmacaoDaSenha;

    @FXML
    private PasswordField confirmacaoDaSenha1;

    @FXML
    private Label exceptions;

    @FXML
    private Button fechar;

    boolean principal;


    @FXML
    void fecha() {
        Main.fecharTela();
        if (ScreamControl.getInstance().getStage() != null) {
            ScreamControl.getInstance().fecharTela();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ControlerPrincipal.getInstance().ultimoControlador instanceof ControlerConfiguracao){
            principal = true;
        }
        else {
            principal = false;
            fechar.setVisible(true);
        }
    }

    @FXML
    void salvar() throws IOException {
        if(principal == true){
            salvarMudancas();
            ControlerPrincipal.getInstance().carregarTelaConfiguracao();
        } else {
            salvarMudancas();
            ControlerInicial.getInstance().carregarTelaLogin();
        }
    }

    private void salvarMudancas() {

        try {
            if (confirmacaoDaSenha.getText().equals(ControladorTasks.getInstance().getUsuarioAtivo().getSenha())) {

                ControladorTasks.getInstance().getUsuarioAtivo().setSenha(confirmacaoDaSenha1.getText());
                ControladorUsuarios.getInstance().salvarMudancas();
            }
            else {
                exceptions.setText("Algo deu errado :( ...  Digite novamente sua senha");
            }
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }

}
