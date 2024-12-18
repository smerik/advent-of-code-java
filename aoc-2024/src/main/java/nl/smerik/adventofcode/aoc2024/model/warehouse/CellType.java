package nl.smerik.adventofcode.aoc2024.model.warehouse;

public enum CellType {

    BOX('O'),
    BOX_LEFT('['),
    BOX_RIGHT(']'),
    EMPTY('.'),
    ROBOT('@'),
    WALL('#');

    private final char token;

    CellType(final char token) {
        this.token = token;
    }

    public static CellType valueOfToken(final char token) {
        for (CellType cellType : CellType.values()) {
            if (cellType.token == token) {
                return cellType;
            }
        }
        throw new IllegalArgumentException("Unknown token " + token);
    }

    public boolean isBox() {
        return this == BOX || this == BOX_LEFT || this == BOX_RIGHT;
    }

    @Override
    public String toString() {
        return String.valueOf(token);
    }
}
