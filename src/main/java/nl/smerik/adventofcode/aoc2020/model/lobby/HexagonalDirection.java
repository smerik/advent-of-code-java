package nl.smerik.adventofcode.aoc2020.model.lobby;

import java.util.Arrays;
import java.util.List;

public enum HexagonalDirection {

    EAST("e"),
    SOUTHEAST("se"),
    SOUTHWEST("sw"),
    WEST("w"),
    NORTHWEST("nw"),
    NORTHEAST("ne");

    private final String code;

    HexagonalDirection(final String code) {
        this.code = code;
    }

    /**
     * Maps given step code into a direction.
     *
     * @param code the step code
     * @return the direction
     */
    public static HexagonalDirection valueOfCode(final String code) {
        for (final HexagonalDirection direction : values()) {
            if (direction.code.equals(code)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid code '" + code + "'");
    }

    /**
     * Parses given steps into a list of directions.
     *
     * @param steps a series of directions
     * @return the list of directions
     */
    public static List<HexagonalDirection> parseSteps(final String steps) {
        // First do some replacements within the line so the step codes can be split up into individual codes
        final String[] stepCodes = steps.replace("e", "e,")
                .replace("w", "w,")
                .split(",");
        // Now map the codes into a list of directions
        return Arrays.stream(stepCodes).map(HexagonalDirection::valueOfCode).toList();
    }
}
