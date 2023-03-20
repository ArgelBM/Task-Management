package negocio.beans;

import enums.Prioridades;

public class Categoria {

    private String nome;
    private String cor;
    private Prioridades prioridade;

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

    public Prioridades getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridades prioridade) {
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
