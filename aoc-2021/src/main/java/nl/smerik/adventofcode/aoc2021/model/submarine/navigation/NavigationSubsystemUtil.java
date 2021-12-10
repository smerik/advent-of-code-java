package nl.smerik.adventofcode.aoc2021.model.submarine.navigation;

import java.util.*;

public final class NavigationSubsystemUtil {

    private NavigationSubsystemUtil() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Calculates the total syntax error score for given lines with chunks.
     *
     * @param lines the lines to calculate the score for
     * @return the total score
     */
    public static int calculateTotalSyntaxErrorScore(final List<String> lines) {
        return lines.stream().mapToInt(NavigationSubsystemUtil::calculateSyntaxErrorScore).sum();
    }

    /**
     * Calculates the syntax error score for given lines with chunks.
     *
     * @param line the line with chunks
     * @return the syntax error score
     */
    public static int calculateSyntaxErrorScore(final String line) {
        final Deque<ChunkPairType> chunks = new ArrayDeque<>();
        for (final char bracket : line.toCharArray()) {
            final ChunkPairType pairType = ChunkPairType.valueOfBracket(bracket);
            if (!pairType.isClosingCharacter()) {
                chunks.push(pairType);
                continue;
            }

            if (chunks.isEmpty()) {
                return pairType.getPoints();
            }

            final ChunkPairType openPairType = chunks.pop();
            if (!pairType.matchesWith(openPairType)) {
                return pairType.getPoints();
            }
        }
        return 0;
    }
}
