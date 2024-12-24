package nl.smerik.adventofcode.aoc2024.model.maze.reindeer;

public enum TileType {

    EMPTY('.'),
    END('E'),
    START('S'),
    WALL('#');

    private final char token;

    TileType(final char token) {
        this.token = token;
    }

    public static TileType valueOfToken(char token) {
        for (final TileType type : TileType.values()) {
            if (type.token == token) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown token " + token);
    }

    @Override
    public String toString() {
        return String.valueOf(token);
    }
}
