package nl.smerik.adventofcode.aoc2023.model.game.cube;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CubeGameTest {

    @Test
    void getId() {
        CubeGame game = new CubeGame("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        assertEquals(5, game.getId());
    }

    private static Stream<Arguments> isGamePossibleSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(true, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
                Arguments.of(true, "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"),
                Arguments.of(false, "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"),
                Arguments.of(false, "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"),
                Arguments.of(true, "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("isGamePossibleSource")
    void isGamePossible(final boolean expectedResult, final String line) {
        CubeGame game = new CubeGame(line);
        assertEquals(expectedResult, game.isGamePossible(Map.of("red", 12, "green", 13, "blue", 14)));
    }

    private static Stream<Arguments> determineFewestNumberOfCubesSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(Map.of("red", 4, "green", 2, "blue", 6), "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
                Arguments.of(Map.of("red", 1, "green", 3, "blue", 4), "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"),
                Arguments.of(Map.of("red", 20, "green", 13, "blue", 6), "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"),
                Arguments.of(Map.of("red", 14, "green", 3, "blue", 15), "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"),
                Arguments.of(Map.of("red", 6, "green", 3, "blue", 2), "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("determineFewestNumberOfCubesSource")
    void isGamePossible(final Map<String, Integer> expectedResult, final String line) {
        CubeGame game = new CubeGame(line);
        assertEquals(expectedResult, game.determineFewestNumberOfCubes());
    }

    private static Stream<Arguments> calcPowerOfFewestNumberOfGamesSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(48, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
                Arguments.of(12, "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"),
                Arguments.of(1560, "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"),
                Arguments.of(630, "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"),
                Arguments.of(36, "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("calcPowerOfFewestNumberOfGamesSource")
    void calcPowerOfFewestNumberOfGames(final int expectedResult, final String line) {
        CubeGame game = new CubeGame(line);
        assertEquals(expectedResult, game.calcPowerOfFewestNumberOfGames());
    }
}