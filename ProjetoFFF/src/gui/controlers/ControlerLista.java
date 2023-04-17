package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerLista implements Initializable {
    @FXML
    private TextField lista;

    private static ControlerLista instance;

    public static ControlerLista getInstance(){
        if(instance == null){
            instance = new ControlerLista();
        }
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lista.setEditable(true);
        lista.requestFocus();
        lista.selectAll();


       lista.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                registrarNovoValor(lista);
                try {
                    carregaLista();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("TextField perdeu foco");
            }
        });

        //Se der enter atualiza a tarefa
        lista.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                registrarNovoValor(lista);
                try {
                    carregaLista();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void registrarNovoValor(TextField textField) {
        String novoValor = textField.getText();
        textField.setText(novoValor);
        lista.getParent().requestFocus();
    }
    @FXML
    void carregaLista() throws IOException {
        String titulo = lista.getText();
        ControlerPrincipal controlerPrincipal = ControlerPrincipal.getInstance();
        controlerPrincipal.carregarTela("/gui/telas/ListaGenerica.fxml", "CENTER");
        ControlerListaGenerica controlerListaGenerica = (ControlerListaGenerica) controlerPrincipal.getUltimoControlador();
        controlerListaGenerica.setTitulo(titulo);
    }

    public String getTitulo(){
        if(this.lista != null) {
            return lista.getText();
        }
        return " ";
    }

    public void setTitulo(String nome){
        lista.setText(nome);
    }
}
