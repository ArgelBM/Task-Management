package exceptions;

public class AtualizacaoFalhouException extends Exception {

    public AtualizacaoFalhouException(String mensagem){
        super("A atualizacao falhou!");
    }

}
