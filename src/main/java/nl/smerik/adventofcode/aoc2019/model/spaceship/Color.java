package nl.smerik.adventofcode.aoc2019.model.spaceship;

import lombok.Getter;

@Getter
public enum Color {

    BLACK(0),
    WHITE(1);

    private int code;

    Color(final int code) {
        this.code = code;
    }

    public static Color valueOfColorCode(final int code) {
        for (final Color color : values()) {
            if (color.code == code) {
                return color;
            }
        }
        throw new IllegalArgumentException("Unknown color code " + code);
    }
}
