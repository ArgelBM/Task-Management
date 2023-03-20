package negocio.beans;

import java.time.LocalDate;

public interface IPessoa {
    String getNome();
    void setNome(String nome);
    String getCpf();
    void setCpf(String cpf);
    LocalDate getDataNascimento();
    void setDataNascimento(LocalDate dataNascimento);
    String getEndereco();
    void setEndereco(String endereco);
    int getIdade();
    void calcularIdade();
    String toString();
}
