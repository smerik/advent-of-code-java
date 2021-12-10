package nl.smerik.adventofcode.aoc2021.model.submarine.navigation;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
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
        try {
            parseLine(line);
        } catch (NavigationSyntaxErrorException e) {
            return e.getUnexpectedPairType().getSyntaxPoints();
        }
        return 0;
    }

    /**
     * Determines the middle score (a.k.a. median) out of all completion scores for given lines.
     * @param lines the lines to
     * @return the middle score
     */
    public static Long determineMedianOfCompletionScores(final List<String> lines) {
        final List<Long> scores = calculateCompletionScores(lines).stream().sorted().toList();
        return scores.get(scores.size() / 2);
    }

    public static List<Long> calculateCompletionScores(final List<String> lines) {
        final List<Long> result = new ArrayList<>();
        for (final String line : lines) {
            try {
                result.add(calculateCompletionScore(line));
            } catch (NavigationSyntaxErrorException e) {
                LOG.debug("Skipping line with syntax error: {}", line);
            }
        }
        return result;
    }

    public static long calculateCompletionScore(final String line) throws NavigationSyntaxErrorException {
        final Deque<ChunkPairType> uncompletedChunks = parseLine(line);
        long result = 0;
        for (final ChunkPairType openingBracket : uncompletedChunks) {
            result = result * 5 + openingBracket.getMatchingBracketType().getCompletionPoints();
        }
        return result;
    }

    static Deque<ChunkPairType> parseLine(final String line) throws NavigationSyntaxErrorException {
        final Deque<ChunkPairType> chunks = new ArrayDeque<>();
        for (final char bracket : line.toCharArray()) {
            final ChunkPairType pairType = ChunkPairType.valueOfBracket(bracket);
            if (!pairType.isClosingCharacter()) {
                chunks.push(pairType);
                continue;
            }

            if (chunks.isEmpty()) {
                throw new NavigationSyntaxErrorException(pairType);
            }

            final ChunkPairType openPairType = chunks.pop();
            if (!pairType.matchesWith(openPairType)) {
                throw new NavigationSyntaxErrorException(pairType);
            }
        }
        return chunks;
    }
}
