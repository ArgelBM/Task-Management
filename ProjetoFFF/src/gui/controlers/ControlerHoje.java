package gui.controlers;

import dados.TaskRepository;
import dados.UsuariosRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import negocio.ControladorTasks;
import negocio.ControladorUsuarios;
import negocio.beans.Task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerHoje implements Initializable {


    @FXML
    private VBox tarefas;

    @FXML
    private TextField novaTarefa;

    private List<Task> repository = ControladorTasks.getInstance().listarTarefas();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inciarTarefas();
        System.out.println("teste");
    }


    @FXML
    void inciarTarefas() {

        for (Task task : repository){
            try {
                FXMLLoader tela = new FXMLLoader(getClass().getResource("/gui/telas/Item.fxml"));
                Node item = tela.load();
                ControlerItem controlerItem = tela.getController();
                controlerItem.setNomeLabel(task.getNome());

                tarefas.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    void adicionarTarefa() {

        if (!novaTarefa.getText().isEmpty()) {
            try {
                ControladorTasks.getInstance().adicionar(new Task(novaTarefa.getText(),"", LocalDate.now(),null, null, ""));

                FXMLLoader tela = new FXMLLoader(getClass().getResource("/gui/telas/Item.fxml"));
                Node item = tela.load();
                ControlerItem controlerItem = tela.getController();
                controlerItem.setNomeLabel(novaTarefa.getText());

                tarefas.getChildren().add(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
            novaTarefa.clear();
        }
    }

}
