package exceptions;

public class AtualizacaoFalhouException extends RuntimeException {
    private Object elemento;

    public AtualizacaoFalhouException(Object obj){
        super("A atualizacao falhou");
        this.elemento = obj;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
