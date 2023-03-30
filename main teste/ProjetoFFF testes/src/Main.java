import dados.PomodoroRepository;
import dados.TaskRepository;
import dados.UsuariosRepository;
import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.beans.Pomodoro;
import negocio.beans.Task;
import negocio.beans.Usuario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ArgumentoInvalidoException, ElementoJaExisteException, ElementoNaoEncontradoException {

        Scanner a = new Scanner(System.in);

        Usuario usurioativo = null;


        UsuariosRepository repositorioUsers = new UsuariosRepository();
        PomodoroRepository repositoriopomodoro = new PomodoroRepository();

        char loop = 'a';

        while( loop == 'a') {


            System.out.println("1 para criar conta: ");

            System.out.println("2 para fazer loguin: ");

            System.out.println("3 para sair");

            String escolha = a.nextLine();

            switch (escolha) {
                case "1" -> {
                    try {
                        System.out.println("digite o nome do usuario: ");
                        String login = a.nextLine();
                        System.out.println("digite a senha :");
                        String senha = a.nextLine();
                        System.out.println("qual o nome do usuario ? : ");
                        String nomeUsuario = a.nextLine();
                        repositorioUsers.adicionar(new Usuario(new TaskRepository(), nomeUsuario, null, 123, login, senha));
                    } catch (InputMismatchException erro) {
                        System.out.println("usuario ou senha invalidos!");
                    }
                }
                case "2" -> {
                    try {
                        System.out.println("digite o nome do usuario: ");
                        String login = a.nextLine();
                        System.out.println("digite a senha :");
                        String senha = a.nextLine();
                        if (repositorioUsers.fazerLogin(login, senha) != null) {
                            usurioativo = repositorioUsers.fazerLogin(login, senha);
                            loop = 'b';
                        }
                        else {
                            System.out.println("algo deu errado");
                        }

                    } catch (InputMismatchException erro) {
                        System.out.println("usuario ou senha invalidos!");
                    }
                }
                case "3" -> {

                    loop = 'c';
                }
            }


            while ( loop == 'b'){

                System.out.printf("%nBem vindo %s%n", usurioativo.getNomeUsuario());

                System.out.println("digite 1 para criar um todo");
                System.out.println("digite 2 para ver seus todoos");
                System.out.println("digite 3 para sair do seu usuario");

                escolha = a.nextLine();

                switch (escolha) {
                    case "1" -> {
                        System.out.println("Nome da task: ");
                        String nome = a.nextLine();
                        System.out.println("descrição : ");
                        String conteudo = a.nextLine();
                        usurioativo.getTask().adicionar(new Task(nome, conteudo));
                    }
                    case "2" -> {
                        if (usurioativo.getTask() == null) {
                            System.out.println("Sua lista esta vazia!");
                        } else {
                            for (Task taskk : usurioativo.getTask().listarTodos()) {
                                System.out.printf("%n==== %s ====%n%n %s%n", taskk.getNome(), taskk.getConteudo());
                            }
                        }
                    }
                    case "3" -> {
                        usurioativo = null;
                        loop = 'a';
                    }
                    case "4" ->{
                        System.out.println("Deseja iniciar um pomodoro para alguma tarefa ? :");
                        System.out.println("Digite 1 para sim e 2 para não.");
                        String escolha2 = a.nextLine();
                        switch (escolha2){
                            case "1" ->{

                                for (Task taskk : usurioativo.getTask().listarTodos()) {
                                    System.out.printf("%n==== %s ====%n%n %s%n", taskk.getNome(), taskk.getConteudo());
                                }

                                System.out.println("digite o nome da task que vc quer adicionar esse pomodoro");
                                String nomem = a.nextLine();

                                repositoriopomodoro.adicionar(new Pomodoro(usurioativo.getTask().listarPorNome(nomem)));

                            }
                            case "2" ->{
                                repositoriopomodoro.adicionar(new Pomodoro(null));
                            }
                        }


                    }
                }

            }

            if(loop == 'c'){
                System.out.println("saindo . . .");
            }





        }
        a.close();
    }

}
