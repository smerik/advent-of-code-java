package nl.smerik.adventofcode.aoc2020.model.boarding;

import lombok.Getter;

@Getter
public enum SeatLocationIdentifier {

    FRONT('F', HalfType.LOWER),
    BACK('B', HalfType.UPPER),

    LEFT('L', HalfType.LOWER),
    RIGHT('R', HalfType.UPPER);

    private final int token;
    private final HalfType halfType;

    SeatLocationIdentifier(final char token, final HalfType halfType) {
        this.token = token;
        this.halfType = halfType;
    }

    enum HalfType {
        LOWER,
        UPPER
    }

    public static SeatLocationIdentifier valueOfToken(final int token) {
        for (final SeatLocationIdentifier identifier : values()) {
            if (identifier.token == token) {
                return identifier;
            }
        }
        throw new IllegalArgumentException("Invalid token '" + token + "'");
    }


}
