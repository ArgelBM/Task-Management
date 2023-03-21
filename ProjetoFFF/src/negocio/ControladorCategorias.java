package negocio;

public class ControladorCategorias {
    private static ControladorCategorias instance;

    public static ControladorCategorias getInstance(){
        if(instance == null){
            instance = new ControladorCategorias();
        }
        return instance;
    }
}
