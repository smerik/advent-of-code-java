package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

public class AsciiStringHelper {

    private static final int HASH_MULTIPLIER = 17;
    private static final int HASH_DIVIDER = 256;

    private AsciiStringHelper() {
        throw new java.lang.UnsupportedOperationException("This is a helper class and cannot be instantiated");
    }

    public static int hash(final String text) {
        int result = 0;
        for (final char character : text.toCharArray()) {
            result += character;
            result *= HASH_MULTIPLIER;
            result %= HASH_DIVIDER;
        }
        return result;
    }
}
