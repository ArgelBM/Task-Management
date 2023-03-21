package negocio;

public class ControladorUsuarios {
    private static ControladorUsuarios instance;

    public static ControladorUsuarios getInstance(){
        if(instance == null){
            instance = new ControladorUsuarios();
        }
        return instance;
    }
}
