package negocio.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private List<Task> task = new ArrayList<>();
    private String nomeUsuario;
    private LocalDate dataNascimento;
    private int id;
    private String login;
    private String senha;

    public Usuario(List<Task> task, String nomeUsuario, LocalDate dataNascimento, int id, String login, String senha) {
        this.task = task;
        this.nomeUsuario = nomeUsuario;
        this.dataNascimento = dataNascimento;
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
