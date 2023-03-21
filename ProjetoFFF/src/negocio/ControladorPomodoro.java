package negocio;

public class ControladorPomodoro {
    private static ControladorPomodoro instance;

    public static ControladorPomodoro getInstance(){
        if(instance == null){
            instance = new ControladorPomodoro();
        }
        return instance;
    }
}
