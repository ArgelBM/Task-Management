package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import negocio.ControladorTasks;
import negocio.beans.Task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerImportante implements Initializable {

    @FXML
    private VBox tarefas;

    @FXML
    private TextField novaTarefa;

    private List<Task> repository = ControladorTasks.getInstance().listarTarefas();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarTarefas();

        ControladorTasks.getInstance().addChangeListener(tasks -> {
            tarefas.getChildren().clear();
            iniciarTarefas();
        });
    }

    private void iniciarTarefas() {

        List<Task> tarefasImportante = repository.stream()
                .filter(t -> ("pendente".equals(t.getClassificacao().getStatusDaTask())) &&
                        "importante".equals(t.getClassificacao().getPrioridadeDaTask()))
                .toList();

        for (Task task : tarefasImportante){
            try {
                FXMLLoader tela = new FXMLLoader(getClass().getResource("/gui/telas/Item.fxml"));
                HBox item = tela.load();
                ControlerItem controlerItem = tela.getController();
                controlerItem.setTask(task);
                if("importante".equals(task.getClassificacao().getPrioridadeDaTask())){
                    controlerItem.setStar("STAR");
                }
                else {
                    controlerItem.setStar("STAR_BORDER");
                }
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
                Task tarefa = new Task(novaTarefa.getText(),"", LocalDate.now(),null, null);
                ControladorTasks.getInstance().adicionar(tarefa);
                ControladorTasks.getInstance().marcarComoImportante(tarefa);
                ControladorTasks.getInstance().marcarComoPendente(tarefa);
            } catch (Exception e) {
                e.printStackTrace();
            }
            novaTarefa.clear();
            System.out.println("adicionou como importante");
        }
    }
}
