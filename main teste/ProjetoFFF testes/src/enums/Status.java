package enums;

import exceptions.ArgumentoInvalidoException;

public enum Status {
        CONCLUIDA("Concluída"),
        PENDENTE("Pendente"),
        FAZENDO("Fazendo");

        private final String status;

        Status(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }

        public static Status fromString(String text) throws ArgumentoInvalidoException {
            for (Status s : Status.values()) {
                if (s.status.equalsIgnoreCase(text)) {
                    return s;
                }
            }
            throw new ArgumentoInvalidoException("Valor inválido para status.");
        }
}
