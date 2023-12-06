package nl.smerik.adventofcode.aoc2023.model.game.boatrace;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceTest {

    private static Stream<Arguments> determineNumberOfWaysToWinRaceSource() {
        // @formatter:off
        return Stream.of(
                Arguments.of(4, 7,     9),
                Arguments.of(8, 15,   40),
                Arguments.of(9, 30,  200)
        );
        // @formatter:on
    }

    @ParameterizedTest
    @MethodSource("determineNumberOfWaysToWinRaceSource")
    void testDetermineWaysToWinRace(final int expectedNumberOfWaysToWin, final int raceDuration, final int recordDistance) {
        final Race race = new Race(raceDuration, recordDistance);
        assertEquals(expectedNumberOfWaysToWin, race.determineNumberOfWaysToWinRace());
    }

    @Test
    void testDetermineWaysToWinRace() {
        final Race race = new Race(7, 9);
        final Map<Integer, Integer> result = race.determineWaysToWinRace();
        final Map<Integer, Integer> expectedResult = Map.of(
                // @formatter:off
                2, 10,
                3, 12,
                4, 12,
                5, 10
                // @formatter:off
        );
        assertEquals(expectedResult, result);
    }
}