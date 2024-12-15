package nl.smerik.adventofcode.aoc2024.model.warehouse;

import lombok.Getter;

@Getter
public enum Direction {

    UP('^', 0, -1),
    DOWN('v', 0, 1),
    LEFT('<', -1, 0),
    RIGHT('>', 1, 0);

    private final char token;
    private final int distanceX;
    private final int distanceY;

    Direction(final char token, final int distanceX, final int distanceY) {
        this.token = token;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
    }

    public static Direction valueOfToken(final char token) {
        for (final Direction direction : values()) {
            if (direction.token == token) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Unknown token " + token);
    }
}
