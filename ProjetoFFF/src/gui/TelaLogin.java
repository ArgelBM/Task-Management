package gui;

import gui.login.ControlerLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaLogin extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //carrega tela de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login//TelaLogin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 700, 500);

        Stage stage1 = new Stage();

        stage.setTitle("Task Management!");

        // escolhe a scene
        stage.setScene(scene);

        // mostra para o controlador onde esta o stage
        ControlerLogin controller  = loader.getController();
        controller.setStage(stage);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}