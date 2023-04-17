package exceptions;

public class ElementoNaoEncontradoException extends Exception {
    private Object elemento;

    public ElementoNaoEncontradoException(Object obj){
        super(obj + "Objeto nao existe no repositorio");
        this.elemento = obj;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
