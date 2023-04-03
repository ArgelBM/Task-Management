package gui;

import gui.login.ControlerLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class TelaLogin extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //carrega tela de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login//TelaLogin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 700, 500);

        stage.initStyle(StageStyle.UNDECORATED);

        // escolhe a scene
        stage.setScene(scene);

        ControlerLogin controller  = loader.getController();
        controller.setStage(stage);

        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}