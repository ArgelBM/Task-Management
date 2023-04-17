package gui.controlers;

import exceptions.ElementoNaoEncontradoException;
import gui.Main;
import gui.ScreamControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import negocio.ControladorTasks;
import negocio.Fachada;
import negocio.beans.Usuario;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerEsqueceuSenha implements Initializable {

    @FXML
    private ChoiceBox<String> choicebox;

    private ObservableList<String> emailsMaisUsados = FXCollections.observableArrayList(
            "@gmail.com",
            "@hotmail.com",
            "@outlook.com"
    );

    @FXML
    private Label erro;

    @FXML
    private TextField login;

    @FXML
    void fecha(){
        Main.fecharTela();
        if (ScreamControl.getInstance().getStage() != null) {
            ScreamControl.getInstance().fecharTela();
        }
    }

    @FXML
    void volta(){
        try {
            ControlerInicial.getInstance().carregarTelaLogin();
        }
        catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    @FXML
    void senhaNova() throws ElementoNaoEncontradoException {
        Usuario usuario = Fachada.getInstance().procuraPorLogin(login.getText() + choicebox.getValue());
        Fachada.getInstance().setUsuarioAtivo(usuario);
        try {
            ControlerInicial.getInstance().carregarTela("/gui/telas/SenhaNova.fxml", "RIGHT");
        }
        catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox.setItems(emailsMaisUsados);
    }
}
