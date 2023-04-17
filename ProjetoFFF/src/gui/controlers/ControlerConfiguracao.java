package gui.controlers;

import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoNaoEncontradoException;
import gui.ScreamControl;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import negocio.ControladorTasks;
import negocio.ControladorUsuarios;
import negocio.Fachada;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlerConfiguracao implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nome.setText(Fachada.getInstance().getUsuarioAtivo().getNomeUsuario());
        data.setValue(Fachada.getInstance().getUsuarioAtivo().getDataNascimento());
        nomeDeUsuario.setText(Fachada.getInstance().getUsuarioAtivo().getNomeUsuario());
        login.setText(Fachada.getInstance().getUsuarioAtivo().getLogin());


    }

    @FXML
    private PasswordField confirmacaoDaSenha;

    @FXML
    private DatePicker data;

    @FXML
    private Label exceptions;

    @FXML
    private Label login;

    @FXML
    private TextField nome;

    @FXML
    private Label nomeDeUsuario;

    @FXML
    void excluirConta(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXCLUIR CONTA");
        alert.setContentText("DESEJA DELETAR SUA CONTA?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isEmpty()){
            System.out.println("Alerta fechado");
        } else if(result.get() == ButtonType.OK){
            try {
            Fachada.getInstance().remover(Fachada.getInstance().getUsuarioAtivo());
        } catch (Exception a) {
            a.printStackTrace();
        }
            ScreamControl.getInstance().telaLogin();
        }else if(result.get() == ButtonType.CANCEL){
            System.out.println("Nunca!");
        }
    }

    @FXML
    void salvarMudancas(ActionEvent event) {

        try {
            if (confirmacaoDaSenha.getText().equals(Fachada.getInstance().getUsuarioAtivo().getSenha())) {

                Fachada.getInstance().getUsuarioAtivo().setDataNascimento(data.getValue());
                Fachada.getInstance().getUsuarioAtivo().setNomeUsuario(nome.getText());
               Fachada.getInstance().salvarMudancas();

                ScreamControl.getInstance().telaPrincipal();

            }
            else {
                exceptions.setText("Algo deu errado :( ...  Digite novamente sua senha");
            }
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    @FXML
    void sair() throws IOException {
        ScreamControl.getInstance().telaLogin();
    }

    @FXML
    void senhaNova(){
        try {
            ControlerPrincipal.getInstance().carregarTela("/gui/telas/SenhaNova.fxml", "CENTER");
        }
        catch (Exception a){
            System.out.println("erro");
            System.out.println(a);
        }
    }

}
