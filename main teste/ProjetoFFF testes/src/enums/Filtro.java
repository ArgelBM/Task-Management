package enums;

public enum Filtro {
    STATUS("status"),
    PRIORIDADE("prioridade"),
    COR("cor"),
    USUARIO("usuário");

    private final String descricao;

    Filtro(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public static Filtro fromString(String descricao) {
        for (Filtro filtro : Filtro.values()) {
            if (filtro.descricao.equalsIgnoreCase(descricao)) {
                return filtro;
            }
        }
        throw new IllegalArgumentException("Filtro inválido: " + descricao);
    }
}

