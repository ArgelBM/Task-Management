package gui.controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import negocio.Fachada;
import negocio.beans.Task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerListaGenerica implements Initializable {
    @FXML
    private TextField novaTarefa;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox tarefas;

    @FXML
    private Label titulo;

    @FXML
    private TitledPane tp;

    @FXML
    private VBox tpConcluidas;

    private List<Task> repository = Fachada.getInstance().listarTarefas();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitulo(getTitulo());
        iniciarTarefas();
        carregarTarefasConcluidas();

        tpConcluidas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getTarget() instanceof CheckBox) {
                ((CheckBox) event.getTarget()).fire();
            }
        });

        Fachada.getInstance().addChangeListener(tasks -> {
            tarefas.getChildren().clear();
            tpConcluidas.getChildren().clear();
            iniciarTarefas();
            carregarTarefasConcluidas();
        });
    }

    private void iniciarTarefas() {
        List<Task> tarefasPendentesFiltro = repository.stream()
                .filter(t -> "pendente".equals(t.getClassificacao().getStatusDaTask()) &&
                        getTitulo().equals(t.getClassificacao().getFiltro()))
                .toList();

        for (Task task : tarefasPendentesFiltro){
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

    private void carregarTarefasConcluidas() {
        List<Task> tarefasConcluidas = repository.stream()
                .filter(t -> "concluida".equals(t.getClassificacao().getStatusDaTask()) &&
                        getTitulo().equals(t.getClassificacao().getFiltro()))
                .toList();

        for (Task task : tarefasConcluidas){
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
                controlerItem.getCheckbox().setSelected(true);

                tpConcluidas.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void adicionarTarefa() {
        if (!novaTarefa.getText().isEmpty()) {
            try {
                Task tarefa = new Task(novaTarefa.getText(),"", LocalDate.now(), null,null, null);
                Fachada.getInstance().adicionar(tarefa);
                Fachada.getInstance().marcarComoPendente(tarefa);
                Fachada.getInstance().setFiltro(tarefa,getTitulo());
            } catch (Exception e) {
                e.printStackTrace();
            }
            novaTarefa.clear();
            ControlerPrincipal.getInstance().fecharTela("RIGHT");
        }
    }

    public void setTitulo(String nome) {
        titulo.setText(nome);
    }

    public String getTitulo() {
        return titulo.getText();
    }
}
