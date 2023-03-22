package negocio.beans;

import enums.Prioridades;
import enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Task {
    private String nome;
    private String conteudo;
    private Status status;
    private LocalDate dataCriada;
    private LocalDate dataConclusao;
    private Prioridades prioridades;
    private List<Pomodoro> pomodoros;
    private String cor;
    private Usuario usuario;

    public Task(String nome, String conteudo, Status status, LocalDate dataCriada, LocalDate dataConclusao, Prioridades prioridades, List<Pomodoro> pomodoros, String cor, Usuario usuario) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.status = status;
        this.dataCriada = dataCriada;
        this.dataConclusao = dataConclusao;
        this.prioridades = prioridades;
        this.pomodoros = pomodoros;
        this.cor = cor;
        this.usuario = usuario;
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

    public Prioridades getPrioridades() {
        return prioridades;
    }

    public void setPrioridades(Prioridades prioridades) {
        this.prioridades = prioridades;
    }
    public String getCor() {
        switch (this.prioridades) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
