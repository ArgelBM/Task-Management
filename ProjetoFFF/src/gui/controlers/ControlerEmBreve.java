package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import negocio.ControladorTasks;
import negocio.Fachada;
import negocio.beans.Task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerEmBreve implements Initializable{


    @FXML
    private VBox tarefas;

    @FXML
    private TextField novaTarefa;

    private List<Task> repository = Fachada.getInstance().listarTarefas();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarTarefas();

        //Ficar checando se mudou algo no repositorio
        Fachada.getInstance().addChangeListener(tasks -> {
            tarefas.getChildren().clear();
            iniciarTarefas();
        });
    }


    private void iniciarTarefas() {

        List<Task> tarefasAgendadas = repository.stream()
                .filter(t -> t.getDataPrevisao() != null)
                .toList();

        for (Task task : tarefasAgendadas){
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
                Task tarefa = new Task(novaTarefa.getText(),"", LocalDate.now(), LocalDate.now(),null, null);
                Fachada.getInstance().adicionar(tarefa);
                Fachada.getInstance().marcarComoPendente(tarefa);
            } catch (Exception e) {
                e.printStackTrace();
            }
            novaTarefa.clear();
            ControlerPrincipal.getInstance().fecharTela("RIGHT");
        }
    }
}
