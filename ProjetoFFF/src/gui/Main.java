package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage stage;
    private static Scene mainScene;
    private static Scene telaPrincipalScene;
    private static Scene telaCadastro;

    @Override
    public void start(Stage stagePrimary) throws IOException {

        //faz o stage apontar para o stagePrimary
        stage = stagePrimary;

        //carrega tela de login
        Parent fxmlMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telas//TelaLogin.fxml")));
        mainScene = new Scene(fxmlMain, 700, 500);

        //carrega tela principal
        Parent fxmlPrincipal = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telas//TelaPrincipal.fxml")));
        telaPrincipalScene = new Scene(fxmlPrincipal, 700, 500);

        //carrega tela cadastro
        Parent fxmlCadastro =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telas//TelaCadastro.fxml")));
        telaCadastro = new Scene(fxmlCadastro, 300, 400);

        //tira borda
        stage.initStyle(StageStyle.UNDECORATED);

        // escolhe a scene
        stage.setScene(mainScene);

        //mostra a tela
        stage.show();

    }

    public static void mudarTela(String tela){
        switch (tela) {
            case "telaLogin" -> stage.setScene(mainScene);
            case "telaPrincipal" -> stage.setScene(telaPrincipalScene);
            case "telaCadastro" -> stage.setScene(telaCadastro);
        }
    }

    public static void fecharTela(){
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}