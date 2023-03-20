package exceptions;

public class DeletarFalhouException extends RuntimeException {
    private Object elemento;

    public DeletarFalhouException(Object obj){
        super("Deletar falhou");
        this.elemento = obj;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
