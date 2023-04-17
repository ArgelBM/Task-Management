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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlerConfiguracao implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nome.setText(ControladorTasks.getInstance().getUsuarioAtivo().getNomeUsuario());
        data.setValue(ControladorTasks.getInstance().getUsuarioAtivo().getDataNascimento());
        nomeDeUsuario.setText(ControladorTasks.getInstance().getUsuarioAtivo().getNomeUsuario());
        login.setText(ControladorTasks.getInstance().getUsuarioAtivo().getLogin());


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
            ControladorUsuarios.getInstance().remover(ControladorTasks.getInstance().getUsuarioAtivo());
            ScreamControl.getInstance().telaLogin();
        }else if(result.get() == ButtonType.CANCEL){
            System.out.println("Nunca!");
        }
    }

    @FXML
    void salvarMudancas(ActionEvent event) {

        try {
            if (confirmacaoDaSenha.getText().equals(ControladorTasks.getInstance().getUsuarioAtivo().getSenha())) {

                ControladorTasks.getInstance().getUsuarioAtivo().setDataNascimento(data.getValue());
                ControladorTasks.getInstance().getUsuarioAtivo().setNomeUsuario(nome.getText());
                ControladorUsuarios.getInstance().salvarMudancas();

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
