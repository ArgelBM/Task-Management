package negocio.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class Task implements Serializable {
    private String nome;
    private String conteudo;
    private LocalDate dataCriada;
    private LocalDate dataPrevisao;
    private LocalDate dataConclusao;
    private LocalDate dataConclusaoAnterior;
    private List<Pomodoro> pomodoros;
    private Classificacao classificacao;

    public Task(String nome, String conteudo, LocalDate dataCriada, LocalDate dataPrevisao, LocalDate dataConclusao, List<Pomodoro> pomodoros) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.dataCriada = dataCriada;
        this.dataPrevisao = dataPrevisao;
        this.dataConclusao = dataConclusao;
        this.pomodoros = pomodoros;
        classificacao = new Classificacao("","","");
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
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


    public LocalDate getDataCriada() {
        return dataCriada;
    }

    public void setDataCriada(LocalDate dataCriada) {
        this.dataCriada = dataCriada;
    }

    public LocalDate getDataConclusaoAnterior() {
        return this.dataConclusaoAnterior;
    }

    public void setDataConclusaoAnterior(LocalDate dataConclusaoAnterior) {
        this.dataConclusaoAnterior = dataConclusaoAnterior;
    }

    public LocalDate getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(LocalDate dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }
}
