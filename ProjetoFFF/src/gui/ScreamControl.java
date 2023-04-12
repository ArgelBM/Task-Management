package gui;

import gui.controlers.ControlerInicial;
import gui.controlers.ControlerPrincipal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class ScreamControl {

    private static ScreamControl instance;

    public static ScreamControl getInstance(){
        if(instance == null){
            instance = new ScreamControl();
        }
        return instance;
    }

    private Stage stage;

public void telaPrincipal() throws IOException {

    if(Main.getStage() != null){
        Main.fecharTela();
    }

    if (stage != null) {
        stage.close();
    }
    stage = new Stage();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("telas/telaPrincipal.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root, 1000, 664);

    ControlerPrincipal a = loader.getController();
    ControlerPrincipal.getInstance().setContentArea(a.getContentArea());

    stage.setScene(scene);
    stage.setTitle("Task Management");
    stage.getIcons().add(new Image("img/logocor.png"));
    stage.show();

}

public void telaLogin() throws IOException {

        stage.close();
        stage = new Stage();

    //carrega tela de login
    FXMLLoader loader = new FXMLLoader(getClass().getResource("telas/TelaInicial.fxml"));
    Parent fxmlMain = loader.load();
    Scene mainScene = new Scene(fxmlMain, 700, 500);

    ControlerInicial a = loader.getController();
    ControlerInicial.getInstance().setContentArea(a.getContentArea());

    //tira borda
    stage.initStyle(StageStyle.UNDECORATED);

    // escolhe a scene
    stage.setScene(mainScene);

    //mostra a tela
    stage.show();

}

    public void fecharTela(){
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }
}
