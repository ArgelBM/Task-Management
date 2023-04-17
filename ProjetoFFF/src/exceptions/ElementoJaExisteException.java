package exceptions;

public class ElementoJaExisteException extends Exception {
    private Object elemento;

    public ElementoJaExisteException(Object obj){
        super(obj + "ja esta cadastrado");
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
