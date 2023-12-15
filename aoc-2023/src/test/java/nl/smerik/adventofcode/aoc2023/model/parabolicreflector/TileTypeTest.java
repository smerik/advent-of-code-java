package nl.smerik.adventofcode.aoc2023.model.parabolicreflector;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileTypeTest {

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "ROUNDED_ROCK    , O",
            "CUBE_SHAPED_ROCK, #",
            "EMPTY_SPACE     , ."
            // @formatter:on
    })
    void valueOfToken(final TileType expectedResult, final char token) {
        assertEquals(expectedResult, TileType.valueOfToken(token));
    }
}