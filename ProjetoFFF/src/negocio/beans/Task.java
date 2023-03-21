package negocio.beans;

import enums.Prioridades;
import enums.Status;

import java.time.LocalDate;

public class Task {
    private String titulo;
    private String conteudo;
    private Status status;
    private LocalDate dataCriada;
    private Prioridades prioridades;
    private List<Pomodoro> pomodoros;

    public Task(String titulo, String conteudo, Status status, LocalDate dataCriada, Prioridades prioridades, List<Pomodoro> pomodoros) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.status = status;
        this.dataCriada = dataCriada;
        this.prioridades = prioridades;
        this.pomodoros = pomodoros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataCriada() {
        return dataCriada;
    }

    public void setDataCriada(LocalDate dataCriada) {
        this.dataCriada = dataCriada;
    }

    public Prioridades getPrioridades() {
        return prioridades;
    }

    public void setPrioridades(Prioridades prioridades) {
        this.prioridades = prioridades;
    }

    public List<Pomodoro> getPomodoros() {
        return pomodoros;
    }

    public void setPomodoros(List<Pomodoro> pomodoros) {
        this.pomodoros = pomodoros;
    }


}
