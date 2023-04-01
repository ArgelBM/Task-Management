package negocio.beans;

import enums.Prioridade;

import java.io.Serializable;

public class Categoria implements Serializable {

    private String nome;
    private String cor;
    private Prioridade prioridade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", cor='" + cor + '\'' +
                ", prioridade=" + prioridade +
                '}';
    }
}
