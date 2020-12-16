package nl.smerik.adventofcode.aoc2020.model.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryGameTest {

    private static Stream<Arguments> provideSourceForDetermineNthSpokenNumber() {
        return Stream.of(
                // @formatter:off
                // For example, suppose the starting numbers are 0,3,6:
                Arguments.of(new int[]{0, 3, 6},    4,   0),
                Arguments.of(new int[]{0, 3, 6},    5,   3),
                Arguments.of(new int[]{0, 3, 6},    6,   3),
                Arguments.of(new int[]{0, 3, 6},    7,   1),
                Arguments.of(new int[]{0, 3, 6},    8,   0),
                Arguments.of(new int[]{0, 3, 6},    9,   4),
                Arguments.of(new int[]{0, 3, 6},   10,   0),
                Arguments.of(new int[]{0, 3, 6}, 2020, 436),

                // Here are a few more examples:
                Arguments.of(new int[]{1, 3, 2}, 2020,    1),
                Arguments.of(new int[]{2, 1, 3}, 2020,   10),
                Arguments.of(new int[]{1, 2, 3}, 2020,   27),
                Arguments.of(new int[]{2, 3, 1}, 2020,   78),
                Arguments.of(new int[]{3, 2, 1}, 2020,  438),
                Arguments.of(new int[]{3, 1, 2}, 2020, 1836),

                // Part Two
                Arguments.of(new int[]{0, 3, 6}, 30000000,  175594),
                Arguments.of(new int[]{1, 3, 2}, 30000000,    2578),
                Arguments.of(new int[]{2, 1, 3}, 30000000, 3544142),
                Arguments.of(new int[]{1, 2, 3}, 30000000,  261214),
                Arguments.of(new int[]{2, 3, 1}, 30000000, 6895259),
                Arguments.of(new int[]{3, 2, 1}, 30000000,      18),
                Arguments.of(new int[]{3, 1, 2}, 30000000,     362)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDetermineNthSpokenNumber")
    void determineNthSpokenNumber(final int[] startingNumbers, final int nthNumber, final int expectedSpokenNumber) {
        final MemoryGame game = new MemoryGame(startingNumbers);
        assertEquals(expectedSpokenNumber, game.determineNthSpokenNumber(nthNumber));
    }
}