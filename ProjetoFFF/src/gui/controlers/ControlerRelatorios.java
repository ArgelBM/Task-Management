package gui.controlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import negocio.Fachada;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class ControlerRelatorios implements Initializable {
    @FXML
    private ChoiceBox<String> choicebox;
    @FXML
    private ChoiceBox<String> choicebox2;
    @FXML
    private BarChart<String, Number> graf1;
    @FXML
    private BarChart<String, Number> graf2;
    @FXML
    private ChoiceBox<String> mes1;
    @FXML
    private ChoiceBox<String> mes2;

    private ObservableList<String> relatorio = FXCollections.observableArrayList(
            "Anual",
            "Mensal",
            "Semanal"
    );
    private ObservableList<String> meses = FXCollections.observableArrayList(
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio" ,
            "Junho",
            "Julho",
            "Agosto",
            "Setembro",
            "Outubro",
            "Novembro",
            "Dezembro"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox.setItems(relatorio);
        choicebox2.setItems(relatorio);
        mes1.setItems(meses);
        mes2.setItems(meses);
        choicebox.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue) {
                case "Anual":
                    mes1.setVisible(false);
                    iniciarGraficoAnual(graf1);
                    break;
                case "Mensal":
                    mes1.setVisible(true);
                    break;
                case "Semanal":
                    mes1.setVisible(false);
                    iniciarGraficoSemanal(graf1);
                    break;
                default:
                    break;
            }
        });
        choicebox2.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue) {
                case "Anual":
                    mes2.setVisible(false);
                    iniciarGraficoAnual(graf2);
                    break;
                case "Mensal":
                    mes2.setVisible(true);
                    break;
                case "Semanal":
                    mes2.setVisible(false);
                    iniciarGraficoSemanal(graf2);
                    break;
                default:
                    break;
            }
        });

        mes1.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            iniciarGraficoMensal(graf1, newValue);
        });
        mes2.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            iniciarGraficoMensal(graf2, newValue);
        });

    }

    private void iniciarGraficoAnual(BarChart chart){
        chart.getData().clear();
        chart.setBarGap(0);
        chart.setCategoryGap(10);
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.getXAxis().setLabel("Meses");
        double barWidth = 40;
        if(graf1.equals(chart)) {
            chart.getYAxis().setLabel("Quantidade de Tarefas");
            graf1.getData().forEach(series -> {
                series.getData().forEach(data -> {
                    data.getNode().setStyle("-fx-bar-width: " + barWidth + "; -fx-bar-align: CENTER;");
                });
            });
        } else {
            chart.getYAxis().setLabel("Quantidade de Pomodoro");
            graf2.getData().forEach(series -> {
                series.getData().forEach(data -> {
                    data.getNode().setStyle("-fx-bar-width: " + barWidth + "; -fx-bar-align: CENTER;");
                });
            });
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        if(graf1.equals(chart)) {
            series.getData().add(new XYChart.Data<>("Janeiro", Fachada.getInstance().contarTarefasConcluidasNoMes(1)));
            series.getData().add(new XYChart.Data<>("Fevereiro", Fachada.getInstance().contarTarefasConcluidasNoMes(2)));
            series.getData().add(new XYChart.Data<>("Março", Fachada.getInstance().contarTarefasConcluidasNoMes(3)));
            series.getData().add(new XYChart.Data<>("Abril", Fachada.getInstance().contarTarefasConcluidasNoMes(4)));
            series.getData().add(new XYChart.Data<>("Maio", Fachada.getInstance().contarTarefasConcluidasNoMes(5)));
            series.getData().add(new XYChart.Data<>("Maio", Fachada.getInstance().contarTarefasConcluidasNoMes(6)));
            series.getData().add(new XYChart.Data<>("Julho", Fachada.getInstance().contarTarefasConcluidasNoMes(7)));
            series.getData().add(new XYChart.Data<>("Agosto", Fachada.getInstance().contarTarefasConcluidasNoMes(8)));
            series.getData().add(new XYChart.Data<>("Setembro", Fachada.getInstance().contarTarefasConcluidasNoMes(9)));
            series.getData().add(new XYChart.Data<>("Outubro", Fachada.getInstance().contarTarefasConcluidasNoMes(10)));
            series.getData().add(new XYChart.Data<>("Novembro", Fachada.getInstance().contarTarefasConcluidasNoMes(11)));
            series.getData().add(new XYChart.Data<>("Dezembro", Fachada.getInstance().contarTarefasConcluidasNoMes(12)));
        } else{
            series.getData().add(new XYChart.Data<>("Janeiro", Fachada.getInstance().contarTarefasConcluidasNoMes(1)));
            series.getData().add(new XYChart.Data<>("Fevereiro", Fachada.getInstance().contarTarefasConcluidasNoMes(2)));
            series.getData().add(new XYChart.Data<>("Março", Fachada.getInstance().contarTarefasConcluidasNoMes(3)));
            series.getData().add(new XYChart.Data<>("Abril", Fachada.getInstance().contarTarefasConcluidasNoMes(4)));
            series.getData().add(new XYChart.Data<>("Maio", Fachada.getInstance().contarTarefasConcluidasNoMes(5)));
            series.getData().add(new XYChart.Data<>("Maio", Fachada.getInstance().contarTarefasConcluidasNoMes(6)));
            series.getData().add(new XYChart.Data<>("Julho", Fachada.getInstance().contarTarefasConcluidasNoMes(7)));
            series.getData().add(new XYChart.Data<>("Agosto", Fachada.getInstance().contarTarefasConcluidasNoMes(8)));
            series.getData().add(new XYChart.Data<>("Setembro", Fachada.getInstance().contarTarefasConcluidasNoMes(9)));
            series.getData().add(new XYChart.Data<>("Outubro", Fachada.getInstance().contarTarefasConcluidasNoMes(10)));
            series.getData().add(new XYChart.Data<>("Novembro", Fachada.getInstance().contarTarefasConcluidasNoMes(11)));
            series.getData().add(new XYChart.Data<>("Dezembro", Fachada.getInstance().contarTarefasConcluidasNoMes(12)));
        }
        chart.getData().add(series);
    }

    private void iniciarGraficoMensal(BarChart chart, String mes){
        chart.getData().clear();
        chart.setBarGap(0);
        chart.setCategoryGap(10);
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.getXAxis().setLabel("Dias");
        double barWidth = 40;
        if(graf1.equals(chart)) {
            chart.getYAxis().setLabel("Quantidade de Tarefas");
            graf1.getData().forEach(series -> {
                series.getData().forEach(data -> {
                    data.getNode().setStyle("-fx-bar-width: " + barWidth + "; -fx-bar-align: CENTER;");
                });
            });
        } else {
            chart.getYAxis().setLabel("Quantidade de Pomodoro");
            graf2.getData().forEach(series -> {
                series.getData().forEach(data -> {
                    data.getNode().setStyle("-fx-bar-width: " + barWidth + "; -fx-bar-align: CENTER;");
                });
            });
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        Map<String, Month> meses = new HashMap<>();
        meses.put("Janeiro", Month.JANUARY);
        meses.put("Fevereiro", Month.FEBRUARY);
        meses.put("Março", Month.MARCH);
        meses.put("Abril", Month.APRIL);
        meses.put("Maio", Month.MAY);
        meses.put("Junho", Month.JUNE);
        meses.put("Julho", Month.JULY);
        meses.put("Agosto", Month.AUGUST);
        meses.put("Setembro", Month.SEPTEMBER);
        meses.put("Outubro", Month.OCTOBER);
        meses.put("Novembro", Month.NOVEMBER);
        meses.put("Dezembro", Month.DECEMBER);

        LocalDate primeiroDiaDoMes = YearMonth.now().atDay(1);
        Month mesSelecionado = meses.get(mes);
        for (int i = 1; i <= primeiroDiaDoMes.lengthOfMonth(); i++) {
            int diaDoMes = i;
            int quantidadeTarefas = Fachada.getInstance().contarTarefasConcluidasNoDiaPorMes(diaDoMes, mesSelecionado);
            series.getData().add(new XYChart.Data<>(Integer.toString(i), quantidadeTarefas));
            System.out.println("dIA:" + Integer.toString(i) + "QT:" + quantidadeTarefas);
        }
        chart.getData().add(series);
    }

    private void iniciarGraficoSemanal(BarChart chart){
        chart.getData().clear();
        chart.setBarGap(0);
        chart.setCategoryGap(10);
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.getXAxis().setLabel("Dias da Semana");
        double barWidth = 40;
        if(graf1.equals(chart)) {
            chart.getYAxis().setLabel("Quantidade de Tarefas");
            graf1.getData().forEach(series -> {
                series.getData().forEach(data -> {
                    data.getNode().setStyle("-fx-bar-width: " + barWidth + "; -fx-bar-align: CENTER;");
                });
            });
        } else {
            chart.getYAxis().setLabel("Quantidade de Pomodoro");
            graf2.getData().forEach(series -> {
                series.getData().forEach(data -> {
                    data.getNode().setStyle("-fx-bar-width: " + barWidth + "; -fx-bar-align: CENTER;");
                });
            });
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        if(graf1.equals(chart)) {
            series.getData().add(new XYChart.Data<>("Seg", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.MONDAY)));
            series.getData().add(new XYChart.Data<>("Ter", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.TUESDAY)));
            series.getData().add(new XYChart.Data<>("Qua", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.WEDNESDAY)));
            series.getData().add(new XYChart.Data<>("Qui", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.THURSDAY)));
            series.getData().add(new XYChart.Data<>("Sex", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.FRIDAY)));
            series.getData().add(new XYChart.Data<>("Sáb", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.SATURDAY)));
            series.getData().add(new XYChart.Data<>("Dom", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.SUNDAY)));
        } else{
            series.getData().add(new XYChart.Data<>("Seg", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.MONDAY)));
            series.getData().add(new XYChart.Data<>("Ter", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.TUESDAY)));
            series.getData().add(new XYChart.Data<>("Qua", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.WEDNESDAY)));
            series.getData().add(new XYChart.Data<>("Qui", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.THURSDAY)));
            series.getData().add(new XYChart.Data<>("Sex", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.FRIDAY)));
            series.getData().add(new XYChart.Data<>("Sáb", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.SATURDAY)));
            series.getData().add(new XYChart.Data<>("Dom", Fachada.getInstance().contarTarefasConcluidasNaUltimaSemanaPorDia(DayOfWeek.SUNDAY)));
        }
        chart.getData().add(series);
    }

}
