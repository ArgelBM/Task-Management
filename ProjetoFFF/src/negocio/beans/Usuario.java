package negocio.beans;

import enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private List<Task> task = new ArrayList<>();
    private String nomeUsuario;
    private LocalDate dataNascimento;
    private String id;
    private String login;
    private String senha;

    public Usuario(List<Task> task, String nomeUsuario, LocalDate dataNascimento, String id, String login, String senha) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
