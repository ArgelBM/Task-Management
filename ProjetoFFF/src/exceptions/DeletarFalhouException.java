package exceptions;

public class DeletarFalhouException extends Exception {

    public DeletarFalhouException(String mensagem){
        super("Deletar falhou!");
    }
}
