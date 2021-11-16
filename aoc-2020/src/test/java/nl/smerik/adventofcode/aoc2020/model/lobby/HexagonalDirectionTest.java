package nl.smerik.adventofcode.aoc2020.model.lobby;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2020.model.lobby.HexagonalDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the enum {@link HexagonalDirection}.
 */
class HexagonalDirectionTest {

    private static Stream<Arguments> sourceForValueOfCode() {
        return Stream.of(
                // @formatter:off
                Arguments.of( "e" , EAST     ),
                Arguments.of( "se", SOUTHEAST),
                Arguments.of( "sw", SOUTHWEST),
                Arguments.of( "w" , WEST     ),
                Arguments.of( "nw", NORTHWEST),
                Arguments.of( "ne", NORTHEAST)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("sourceForValueOfCode")
    void testValueOfCode(final String stepCode, final HexagonalDirection expectedDirection) {
        assertEquals(expectedDirection, HexagonalDirection.valueOfCode(stepCode));
    }


    private static Stream<Arguments> sourceForParseSteps() {
        return Stream.of(
                // @formatter:off
                Arguments.of( "esenee"    , List.of(EAST, SOUTHEAST, NORTHEAST, EAST)                      ), // example part 01
                Arguments.of( "e"         , List.of(EAST)                                                  ),
                Arguments.of( "se"        , List.of(SOUTHEAST)                                             ),
                Arguments.of( "sw"        , List.of(SOUTHWEST)                                             ),
                Arguments.of( "w"         , List.of(WEST)                                                  ),
                Arguments.of( "nw"        , List.of(NORTHWEST)                                             ),
                Arguments.of( "ne"        , List.of(NORTHEAST)                                             ),
                Arguments.of( "eseswwnwne", List.of(EAST, SOUTHEAST, SOUTHWEST, WEST, NORTHWEST, NORTHEAST))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("sourceForParseSteps")
    void testParseSteps(final String steps, final List<HexagonalDirection> expectedDirections) {
        assertEquals(expectedDirections, HexagonalDirection.parseSteps(steps));
    }
}