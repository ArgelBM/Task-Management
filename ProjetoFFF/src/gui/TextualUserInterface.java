package gui;

import exceptions.ArgumentoInvalidoException;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoEncontradoException;
import negocio.ControladorPomodoro;
import negocio.ControladorTasks;
import negocio.ControladorUsuarios;
import negocio.Fachada;
import negocio.beans.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextualUserInterface {
    private final Scanner scanner;
    private final Fachada fachada;

    public TextualUserInterface(){
        this.scanner = new Scanner(System.in);
        this.fachada = new Fachada();
    }

    public void exibirMenuPrincipal(){
        int opcao;
        do{
            System.out.println("== Menu Principal ==");
            System.out.println("1. Gerenciar Tarefas");
            System.out.println("2. Gerenciar Usuários");
            System.out.println("3. Gerar Relatórios");
            System.out.println("4. Iniciar Pomodoro");
            System.out.println("0. Sair");
            System.out.println("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> exibirMenuTarefas();
                case 2 -> exibirMenuUsuarios();
                case 3 -> exibirMenuRelatorios();
                case 4 -> exibirTelaPomodoro();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
            System.out.println();
        }while (opcao != 0);
    }

    private void exibirMenuTarefas(){
        int opcao;
        do{
            System.out.println("== Gerenciar Tarefas ==");
            System.out.println("1. Listar Tarefas");
            System.out.println("2. Adicionar Tarefa");
            System.out.println("3. Editar Tarefa");
            System.out.println("4. Marcar Tarefa como Concluída");
            System.out.println("5. Excluir Tarefa");
            System.out.println("6. Editar Categoria de Tarefa");
            System.out.println("0. Voltar");
            System.out.printf("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> listarTarefas();
                case 2 -> adicionarTarefa();
                case 3 -> editarTarefa();
                case 4 -> marcarTarefaConcluida();
                case 5 -> excluirTarefa();
                case 6 -> editarCategoriaTarefa();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
            System.out.println();
        }while (opcao != 0);
    }

    private void listarTarefas(){
        System.out.println("== Listar Tarefas ==");
        this.fachada.listarTodasTarefas();
    }

    public void adicionarTarefa() {
        System.out.println("=== Adicionar Tarefa ===");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        LocalDate dataLimite = null;
        while (dataLimite == null) {
            System.out.print("Data limite (formato dd/mm/aaaa): ");
            String dataLimiteStr = scanner.nextLine();
            try {
                dataLimite = LocalDate.parse(dataLimiteStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Tente novamente.");
            }
        }

        int prioridade = -1;
        while (prioridade < 0 || prioridade > 5) {
            System.out.print("Prioridade (0-5): ");
            try {
                prioridade = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }

        try {
            ControladorTasks.adicionarTarefa(titulo, descricao, dataLimite, prioridade);
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (ElementoJaExisteException e) {
            System.out.println("Erro: a tarefa já existe.");
        } catch (ArgumentoInvalidoException e) {
            System.out.println("Erro: argumento inválido.");
        }
    }



    private void editarTarefa() {
        System.out.println("Editar Tarefa");

        try {
            System.out.println("Digite o id da tarefa que deseja editar:");
            int id = scanner.nextInt();
            scanner.nextLine();

            Task tarefa = controladorTasks.buscarTarefaPorId(id);

            if (tarefa != null) {
                System.out.println("Digite o novo título da tarefa:");
                String novoTitulo = scanner.nextLine();

                System.out.println("Digite a nova descrição da tarefa:");
                String novaDescricao = scanner.nextLine();

                System.out.println("Digite a nova categoria da tarefa:");
                String novaCategoria = scanner.nextLine();

                System.out.println("Digite a nova data de início da tarefa (no formato DD/MM/AAAA):");
                LocalDate novaDataInicio = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.println("Digite a nova data de término da tarefa (no formato DD/MM/AAAA):");
                LocalDate novaDataFim = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                tarefa.setTitulo(novoTitulo);
                tarefa.setDescricao(novaDescricao);
                tarefa.setCategoria(novaCategoria);
                tarefa.setDataInicio(novaDataInicio);
                tarefa.setDataFim(novaDataFim);

                controladorTasks.atualizarTarefa(tarefa);

                System.out.println("Tarefa editada com sucesso!");
            } else {
                System.out.println("Tarefa não encontrada!");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Certifique-se de digitar a data no formato DD/MM/AAAA.");
        } catch (ElementoNaoEncontradoException | ArgumentoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void marcarTarefaConcluida() {
        System.out.println("Marcar Tarefa como Concluída");
        try {
            System.out.println("Digite o ID da tarefa que deseja marcar como concluída:");
            int idTarefa = scanner.nextInt();
            scanner.nextLine();

            controladorTasks.marcarTarefaConcluida(idTarefa);

            System.out.println("Tarefa marcada como concluída com sucesso!");
        } catch (ElementoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void excluirTarefa() {
        System.out.println("== Excluir Tarefa ==");

        System.out.println("Digite o ID da tarefa que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            controladorTasks.excluirTarefa(id);
            System.out.println("Tarefa excluída com sucesso!");
        } catch (ElementoNaoEncontradoException e) {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private void editarCategoriaTarefa() {
        System.out.println("Editar categoria da tarefa");

        try {
            System.out.println("Digite o ID da tarefa que deseja editar a categoria:");
            int idTarefa = scanner.nextInt();
            scanner.nextLine(); // Consumir o caractere de nova linha deixado pelo nextInt()

            System.out.println("Digite a nova categoria da tarefa:");
            String novaCategoria = scanner.nextLine();

            controladorTasks.editarCategoriaTarefa(idTarefa, novaCategoria);

            System.out.println("Categoria da tarefa atualizada com sucesso!");
        } catch (ElementoNaoEncontradoException | ArgumentoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exibirMenuUsuarios() {
        int opcao;
        do {
            System.out.println("== Gerenciar Usuários ==");
            System.out.println("1. Listar Usuários");
            System.out.println("2. Adicionar Usuário");
            System.out.println("3. Editar Usuário");
            System.out.println("4. Excluir Usuário");
            System.out.println("0. Voltar");
            System.out.printf("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> listarUsuarios();
                case 2 -> adicionarUsuario();
                case 3 -> editarUsuario();
                case 4 -> excluirUsuario();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
            System.out.println();
        } while (opcao != 0);
    }

    private void listarUsuarios(){
        System.out.println("== Listar Usuários ==");
        for(Usuario usuario : controladorUsuarios.listarTodosUsuarios()){
            System.out.println(usuario);
        }
    }

    private void adicionarUsuario() {
        System.out.println("Adicionar Usuário");

        try {
            System.out.println("Digite o nome do usuário:");
            String nome = scanner.nextLine();

            System.out.println("Digite o e-mail do usuário:");
            String email = scanner.nextLine();

            System.out.println("Digite a senha do usuário:");
            String senha = scanner.nextLine();

            Usuario usuario = new Usuario(nome, email, senha);
            controladorUsuarios.adicionarUsuario(usuario);

            System.out.println("Usuário adicionado com sucesso!");
        } catch (ElementoJaExisteException | ArgumentoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editarUsuario() {
        System.out.println("Editar Usuário");

        System.out.println("Digite o id do usuário a ser editado:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Usuario usuario = controladorUsuarios.buscarUsuario(id);
        if(usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        try {
            System.out.println("Digite o novo nome do usuário:");
            String nome = scanner.nextLine();
            usuario.setNome(nome);

            System.out.println("Digite o novo e-mail do usuário:");
            String email = scanner.nextLine();
            usuario.setEmail(email);

            System.out.println("Digite a nova senha do usuário:");
            String senha = scanner.nextLine();
            usuario.setSenha(senha);

            System.out.println("Usuário editado com sucesso!");
        } catch (ArgumentoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void excluirUsuario() {
        System.out.println("== Excluir Usuário ==");

        System.out.println("Digite o nome do usuário que deseja excluir:");
        String nome = scanner.nextLine();

        try {
            controladorUsuarios.removerUsuario(nome);
            System.out.println("Usuário removido com sucesso!");
        } catch (ElementoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exibirMenuRelatorios() {
        int opcao;
        do {
            System.out.println("== Relatórios ==");
            System.out.println("1. Tarefas realizadas por mês");
            System.out.println("2. Pomodoros usados por tarefa");
            System.out.println("0. Voltar");
            System.out.printf("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> relatorioTarefasRealizadasPorMes();
                case 2 -> relatorioPomodorosPorTarefa();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
            System.out.println();
        } while (opcao != 0);
    }

    private void relatorioTarefasRealizadasPorMes() {
        System.out.println("== Tarefas realizadas por mês ==");
        Map<YearMonth, Integer> tarefasPorMes = controladorTasks.getTarefasRealizadasPorMes();
        if (tarefasPorMes.isEmpty()) {
            System.out.println("Não há tarefas realizadas.");
            return;
        }
        System.out.println("Mês\t\tTarefas realizadas");
        for (YearMonth mes : tarefasPorMes.keySet()) {
            System.out.printf("%s\t%d\n", mes.toString(), tarefasPorMes.get(mes));
        }
    }

    private void relatorioPomodorosPorTarefa() {
        System.out.println("== Pomodoros por tarefa ==");
        List<Tarefa> tarefas = controladorTasks.listarTodasTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas cadastradas.");
            return;
        }
        int totalPomodoros = 0;
        for (Tarefa tarefa : tarefas) {
            int numPomodoros = tarefa.getNumPomodoros();
            System.out.printf("Tarefa: %s\tPomodoros: %d\n", tarefa.getTitulo(), numPomodoros);
            totalPomodoros += numPomodoros;
        }
        double mediaPomodorosPorTarefa = (double) totalPomodoros / tarefas.size();
        System.out.printf("Média de pomodoros por tarefa: %.2f\n", mediaPomodorosPorTarefa);
    }

    private void exibirTelaPomodoro(Task task) {
        int tempoDeTrabalho = 25; // tempo de trabalho em minutos
        int tempoDeDescanso = 5; // tempo de descanso em minutos

        System.out.println("Iniciando tarefa " + task.getDescricao() + "...");
        System.out.println("Tempo de trabalho: " + tempoDeTrabalho + " minutos.");
        System.out.println("Tempo de descanso: " + tempoDeDescanso + " minutos.");

        int numPomodoros = 0;
        int tempoTotalTrabalho = 0;

        while (tempoTotalTrabalho < task.getTempoEstimado()) {
            // inicia o cronômetro de trabalho
            for (int i = tempoDeTrabalho; i >= 0; i--) {
                System.out.println("Tempo restante de trabalho: " + i + " minutos.");
                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            numPomodoros++;
            tempoTotalTrabalho += tempoDeTrabalho;

            // toca um som ou exibe um alerta para indicar que é hora de descansar
            System.out.println("Hora de descansar por " + tempoDeDescanso + " minutos!");

            // inicia o cronômetro de descanso
            for (int i = tempoDeDescanso; i >= 0; i--) {
                System.out.println("Tempo restante de descanso: " + i + " minutos.");
                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // toca um som ou exibe um alerta para indicar que o descanso acabou
            System.out.println("Hora de voltar ao trabalho!");
        }

        System.out.println("Tarefa concluída!");
        System.out.println("Número de pomodoros utilizados: " + numPomodoros);
        double mediaPomodorosPorTarefa = (double) numPomodoros / task.getTempoEstimado();
        System.out.println("Média de pomodoros por tarefa: " + mediaPomodorosPorTarefa);
    }


}
