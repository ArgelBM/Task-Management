package negocio.beans;

import dados.TaskRepository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Usuario implements Serializable {
    private TaskRepository task ;
    private String nomeUsuario;
    private LocalDate dataNascimento;
    private int id;
    private String login;
    private String senha;
    private List<String> cor;
    private List<String> prioridade;
    private List<String> status;

    public Usuario( String nomeUsuario, LocalDate dataNascimento, int id, String login, String senha) {
        this.task = new TaskRepository();
        this.nomeUsuario = nomeUsuario;
        this.dataNascimento = dataNascimento;
        this.id = id;
        this.login = login;
        this.senha = senha;
        //
        cor = Arrays.asList("vermelho","verde", "azul","");
        prioridade = Arrays.asList("importante","irrelevante","");
        status = Arrays.asList("concluído","pendente", "fazendo");

    }

    public TaskRepository getTask() {
        return task;
    }

    public void setTask(TaskRepository task) {
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
