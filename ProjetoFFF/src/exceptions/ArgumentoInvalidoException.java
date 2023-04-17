package exceptions;

public class ArgumentoInvalidoException extends Exception {
    private Object elemento;// Objeto relacionado à exceção (usuário, tarefa, etc.)

    public ArgumentoInvalidoException(String mensagem){
        super(mensagem);
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
