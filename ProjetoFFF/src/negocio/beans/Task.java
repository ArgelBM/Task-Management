package negocio.beans;

import enums.Prioridade;
import enums.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class Task implements Serializable {
    private String nome;
    private String conteudo;
    private Status status;
    private LocalDate dataCriada;
    private LocalDate dataConclusao;
    private Prioridade prioridade;
    private List<Pomodoro> pomodoros;
    private String cor;

    public Task(String nome, String conteudo, Status status, LocalDate dataCriada, LocalDate dataConclusao, Prioridade prioridade, List<Pomodoro> pomodoros, String cor) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.status = status;
        this.dataCriada = dataCriada;
        this.dataConclusao = dataConclusao;
        this.prioridade = prioridade;
        this.pomodoros = pomodoros;
        this.cor = cor;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public List<Pomodoro> getPomodoros() {
        return pomodoros;
    }

    public void setPomodoros(List<Pomodoro> pomodoros) {
        this.pomodoros = pomodoros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Prioridade getPrioridades() {
        return prioridade;
    }

    public void setPrioridades(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
    public String getCor() {
        switch (this.prioridade) {
            case IMPORTANTE:
                return "vermelho";
            case IRRELEVANTE:
                return "amarelo";
            case FAZ_SE_DER_TEMPO:
                return "azul";
            default:
                return "preto";
        }
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

}
