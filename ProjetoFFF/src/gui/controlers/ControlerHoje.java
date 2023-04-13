package gui.controlers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import negocio.ControladorTasks;
import negocio.beans.Task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerHoje implements Initializable{


    @FXML
    private VBox tarefas;

    @FXML
    private TextField novaTarefa;

    @FXML
    private VBox tpConcluidas;

    @FXML
    private TitledPane tp;

    private List<Task> repository = ControladorTasks.getInstance().listarTarefas();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarTarefas();
        carregarTarefasConcluidas();

        ControladorTasks.getInstance().getRepositorio().addChangeListener(tasks -> {
            tarefas.getChildren().clear();
            tpConcluidas.getChildren().clear();
            iniciarTarefas();
            carregarTarefasConcluidas();
        });
    }


    private void iniciarTarefas() {

        List<Task> tarefasPendentesOuHoje = repository.stream()
                .filter(t -> (t.getDataConclusao() == null || t.getDataConclusao().isEqual(LocalDate.now())) &&
                        t.getClassificacao().getStatusDaTask().contains("pendente"))
                .toList();

        for (Task task : tarefasPendentesOuHoje){
            try {
                FXMLLoader tela = new FXMLLoader(getClass().getResource("/gui/telas/Item.fxml"));
                HBox item = tela.load();
                ControlerItem controlerItem = tela.getController();
                controlerItem.setTask(task);

                tarefas.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void carregarTarefasConcluidas() {

        List<Task> tarefasConcluidas = repository.stream()
                .filter(t -> "concluida".equals(t.getClassificacao().getStatusDaTask()))
                .toList();

        for (Task task : tarefasConcluidas){
            try {
                FXMLLoader tela = new FXMLLoader(getClass().getResource("/gui/telas/Item.fxml"));
                HBox item = tela.load();
                ControlerItem controlerItem = tela.getController();
                controlerItem.setTask(task);

                tpConcluidas.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("chamou o metodo carregarTarefasConcluidas");
    }

    @FXML
    void adicionarTarefa() {

        if (!novaTarefa.getText().isEmpty()) {
            try {
                Task tarefa = new Task(novaTarefa.getText(),"", LocalDate.now(),null, null);
                ControladorTasks.getInstance().adicionar(tarefa);
                ControladorTasks.getInstance().marcarComoPendente(tarefa);
            } catch (Exception e) {
                e.printStackTrace();
            }
            novaTarefa.clear();
        }
    }
}
