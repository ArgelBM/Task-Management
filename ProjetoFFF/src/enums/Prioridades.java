package enums;

import exceptions.ArgumentoInvalidoException;

public enum Prioridades {
        IMPORTANTE("Importante"),
        IRRELEVANTE("Irrelevante"),
        FAZ_SE_DER_TEMPO("Faz se der tempo");

        private final String prioridade;

        Prioridades(String prioridade) {
            this.prioridade = prioridade;
        }

        @Override
        public String toString() {
            return prioridade;
        }

        public static Prioridades fromString(String text) throws ArgumentoInvalidoException {
            for (Prioridades p : Prioridades.values()) {
                if (p.prioridade.equalsIgnoreCase(text)) {
                    return p;
                }
            }
            throw new ArgumentoInvalidoException("Valor inválido para prioridade.");
        }
}

