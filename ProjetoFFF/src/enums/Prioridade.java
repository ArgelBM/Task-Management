package enums;

import exceptions.ArgumentoInvalidoException;

public enum Prioridade {
        IMPORTANTE("Importante","vermelho"),
        IRRELEVANTE("Irrelevante","amarelo"),
        FAZ_SE_DER_TEMPO("Faz se der tempo","azul");

        private final String prioridade;
        private final String cor;

        Prioridade(String prioridade, String cor) {this.prioridade = prioridade; this.cor = cor;}


        @Override
        public String toString() {
            return prioridade;
        }
        public String getCor() { return cor;}

        public static Prioridade fromString(String text) throws ArgumentoInvalidoException {
            for (Prioridade p : Prioridade.values()) {
                if (p.prioridade.equalsIgnoreCase(text)) {
                    return p;
                }
            }
            throw new ArgumentoInvalidoException("Valor inválido para prioridade.");
        }
}

