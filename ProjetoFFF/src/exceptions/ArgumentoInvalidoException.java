package exceptions;

public class ArgumentoInvalidoException extends Exception {
    private Object elemento;

    public ArgumentoInvalidoException(Object obj){
        super("Voce digitou o argumento errado, digite corretamente");
        this.elemento = obj;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
