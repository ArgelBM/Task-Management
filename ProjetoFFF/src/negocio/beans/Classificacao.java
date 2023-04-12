package negocio.beans;

import java.io.Serializable;
import java.util.Objects;

public class Classificacao implements Serializable {
    private String corDaTask;
    private String prioridadeDaTask;
    private String statusDaTask;

    public Classificacao(String corDaTask, String prioridadeDaTask, String statusDaTask) {
        this.corDaTask = corDaTask;
        this.prioridadeDaTask = prioridadeDaTask;
        this.statusDaTask = statusDaTask;
        if (statusDaTask.equals("")){
            statusDaTask = "pendente";
        }
    }

    public String getCorDaTask() {
        return corDaTask;
    }

    public void setCorDaTask(String corDaTask) {
        this.corDaTask = corDaTask;
    }

    public String getPrioridadeDaTask() {
        return prioridadeDaTask;
    }

    public void setPrioridadeDaTask(String prioridadeDaTask) {
        this.prioridadeDaTask = prioridadeDaTask;
    }

    public String getStatusDaTask() {
        return statusDaTask;
    }

    public void setStatusDaTask(String statusDaTask) {
        this.statusDaTask = statusDaTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classificacao that = (Classificacao) o;
        return Objects.equals(corDaTask, that.corDaTask) && Objects.equals(prioridadeDaTask, that.prioridadeDaTask) && Objects.equals(statusDaTask, that.statusDaTask);
    }

}
