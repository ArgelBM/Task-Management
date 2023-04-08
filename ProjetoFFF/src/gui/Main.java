package gui;

import gui.controlers.ControlerInicial;
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
    //private static Scene telaPrincipalScene;

    @Override
    public void start(Stage stagePrimary) throws IOException {

        //faz o stage apontar para o stagePrimary
        stage = stagePrimary;

        //carrega tela de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("telas//TelaInicial.fxml"));
        Parent fxmlMain = loader.load();
        mainScene = new Scene(fxmlMain, 700, 500);

        ControlerInicial a = loader.getController();
        ControlerInicial.getInstance().setContentArea(a.getContentArea());

        //carrega tela principal
        //Parent fxmlPrincipal = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("telas//TelaPrincipal.fxml")));
        //telaPrincipalScene = new Scene(fxmlPrincipal, 700, 500);


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
            //case "telaPrincipal" -> stage.setScene(telaPrincipalScene);

        }
    }

    public static void fecharTela(){
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}