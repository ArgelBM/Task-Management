package gui.controlers;

import gui.Main;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import negocio.ControladorUsuarios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControlerPrincipal implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
                nomeDeUsuario.setText(ControladorUsuarios.getInstance().usuarioAtivo().getNomeUsuario());
                login.setText(ControladorUsuarios.getInstance().usuarioAtivo().getLogin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private BorderPane contentArea;

    @FXML
    private Label login;

    @FXML
    private Label nomeDeUsuario;

    public ControlerPrincipal(){

    }

    @FXML
    void sair() {
        Main.fecharTela();

    }
}
