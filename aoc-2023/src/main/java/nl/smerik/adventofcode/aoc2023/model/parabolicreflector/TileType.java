package nl.smerik.adventofcode.aoc2023.model.parabolicreflector;

import lombok.Getter;

@Getter
public enum TileType {

    ROUNDED_ROCK('O'),
    CUBE_SHAPED_ROCK('#'),
    EMPTY_SPACE('.');

    private final char token;

    TileType(final char token) {
        this.token = token;
    }

    public static TileType valueOfToken(final char token) {
        for (final TileType tileType : values()) {
            if (tileType.token == token) {
                return tileType;
            }
        }
        throw new IllegalArgumentException("Invalid token '" + token + "'");
    }

    @Override
    public String toString() {
        return String.valueOf(token);
    }
}
