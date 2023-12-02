package nl.smerik.adventofcode.aoc2023.model.game.cube;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CubeGameAdministratorTest {

    private final List<String> example01part01 = List.of(
        // @formatter:off
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        // @formatter:on
    );

    @Test
    void calcSumOfIDsForPossibleGames() {
        final CubeGameAdministrator administrator = new CubeGameAdministrator(example01part01);
        final Map<String, Integer> game = Map.of("red", 12, "green", 13, "blue", 14);
        assertEquals(8, administrator.calcSumOfIDsForPossibleGames(game));
    }
}