package nl.smerik.adventofcode.aoc2021.model.submarine.navigation;

import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;

@Getter
public enum ChunkPairType {

    ROUND_OPEN('(', false, 0),
    ROUND_CLOSE(')', true, 3),
    SQUARE_OPEN('[', false, 0),
    SQUARE_CLOSE(']', true, 57),
    CURLY_OPEN('{', false, 0),
    CURLY_CLOSE('}', true, 1197),
    ANGLE_OPEN('<', false, 0),
    ANGLE_CLOSE('>', true, 25137);

    private static final Map<ChunkPairType, ChunkPairType> matchesWithType = new EnumMap<>(ChunkPairType.class);
    static {
        matchesWithType.put(ROUND_OPEN, ROUND_CLOSE);
        matchesWithType.put(ROUND_CLOSE, ROUND_OPEN);
        matchesWithType.put(SQUARE_OPEN, SQUARE_CLOSE);
        matchesWithType.put(SQUARE_CLOSE, SQUARE_OPEN);
        matchesWithType.put(CURLY_OPEN, CURLY_CLOSE);
        matchesWithType.put(CURLY_CLOSE, CURLY_OPEN);
        matchesWithType.put(ANGLE_OPEN, ANGLE_CLOSE);
        matchesWithType.put(ANGLE_CLOSE, ANGLE_OPEN);
    }

    private final char bracket;
    private final boolean closingCharacter;
    private final int points;

    ChunkPairType(final char bracket, final boolean closingCharacter, final int points) {
        this.bracket = bracket;
        this.closingCharacter = closingCharacter;
        this.points = points;
    }

    public static ChunkPairType valueOfBracket(final char bracket) {
        for (final ChunkPairType type : values()) {
            if (type.bracket == bracket) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown bracket character '" + bracket + "'");
    }

    public boolean matchesWith(final ChunkPairType type) {
        return matchesWithType.get(this) == type;
    }
}
