package nl.smerik.adventofcode.aoc2020.model.cups;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrabCupsTest {

    @Test
    void testConstructor() {
        final CrabCups cups = new CrabCups("389125467");
        assertEquals(List.of(3, 8, 9, 1, 2, 5, 4, 6, 7), cups.getCups());
        assertEquals(1, cups.getMinCupLabel());
        assertEquals(9, cups.getMaxCupLabel());
        assertEquals(3, cups.getCurrentCupLabel());
        assertEquals(0, cups.getMoveCount());
    }


    private static Stream<Arguments> simulateExample01() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 1, List.of(3, 2, 8, 9, 1, 5, 4, 6, 7)),
                Arguments.of( 2, List.of(3, 2, 5, 4, 6, 7, 8, 9, 1)),
                Arguments.of( 3, List.of(3, 4, 6, 7, 2, 5, 8, 9, 1)),
                Arguments.of( 4, List.of(4, 6, 7, 9, 1, 3, 2, 5, 8)),
                Arguments.of( 5, List.of(4, 1, 3, 6, 7, 9, 2, 5, 8)),
                Arguments.of( 6, List.of(4, 1, 9, 3, 6, 7, 2, 5, 8)),
                Arguments.of( 7, List.of(4, 1, 9, 2, 5, 8, 3, 6, 7)),
                Arguments.of( 8, List.of(4, 1, 5, 8, 3, 9, 2, 6, 7)),
                Arguments.of( 9, List.of(5, 7, 4, 1, 8, 3, 9, 2, 6)),
                Arguments.of(10, List.of(5, 8, 3, 7, 4, 1, 9, 2, 6))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("simulateExample01")
    void testSimulate(final int simulateCount, final List<Integer> expectedCups) {
        final CrabCups cups = new CrabCups("389125467");
        cups.simulate(simulateCount);
        assertEquals(expectedCups, cups.getCups());
    }


    @Test
    void testGetCupLabeling() {
        final CrabCups cups = new CrabCups("389125467");
        assertEquals("25467389", cups.getCupLabeling());
    }
}