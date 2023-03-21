package negocio;

import dados.CategoriasRepository;
import dados.PomodoroRepository;
import dados.TaskRepository;
import dados.UsuariosRepository;

public class Fachada {
    private static Fachada instancia;
    private static final CategoriasRepository categoriRepository = new CategoriasRepository();
    private static final TaskRepository tarefaRepository = new TaskRepository();
    private static final UsuariosRepository usuarioRepository = new UsuariosRepository();
    private static final PomodoroRepository podomoroRepository = new PomodoroRepository();

    private final ControladorCategorias controladorCategorias;
    private final ControladorTasks controladorTasks;
    private final ControladorUsuarios controladorUsuarios;
    private final ControladorPomodoro controladorPomodoro;

    private Fachada(){
        controladorCategorias = new ControladorCategorias(CategoriasRepository);
        controladorTasks = new ControladorTasks(TaskRepository);
        controladorUsuarios = new ControladorUsuarios(UsuariosRepository);
        controladorPomodoro = new ControladorPomodoro(PomodoroRepository)
    }
    public static Fachada getInstance(){
        if(instancia == null){
            instancia = new Fachada();
        }
        return instancia;
    }
}
