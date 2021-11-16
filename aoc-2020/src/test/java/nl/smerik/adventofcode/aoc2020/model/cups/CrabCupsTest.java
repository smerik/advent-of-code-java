package nl.smerik.adventofcode.aoc2020.model.cups;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CrabCupsTest {

    @Test
    void testConstructorPart1() {
        final CrabCups cups = new CrabCups("389125467", 9);
        final Map<Integer, Integer> expected = Map.ofEntries(
                entry(3, 8),
                entry(8, 9),
                entry(9, 1),
                entry(1, 2),
                entry(2, 5),
                entry(5, 4),
                entry(4, 6),
                entry(6, 7),
                entry(7, 3)
        );
        assertEquals(expected, cups.getNextCupByCupLabel());
        assertEquals(1, cups.getMinCupLabel());
        assertEquals(9, cups.getMaxCupLabel());
        assertEquals(3, cups.getCurrentCupLabel());
        assertEquals(0, cups.getMoveCount());
    }

    @Test
    void testConstructorPart2() {
        final CrabCups cups = new CrabCups("389125467", 1000000);
        assertEquals(1000000, cups.getNextCupByCupLabel().size());
        assertEquals(8, cups.getNextCupByCupLabel().get(3));
        assertEquals(7, cups.getNextCupByCupLabel().get(6));
        assertEquals(10, cups.getNextCupByCupLabel().get(7));
        assertEquals(11, cups.getNextCupByCupLabel().get(10));
        assertEquals(3, cups.getNextCupByCupLabel().get(1000000));
        assertEquals(1, cups.getMinCupLabel());
        assertEquals(1000000, cups.getMaxCupLabel());
        assertEquals(3, cups.getCurrentCupLabel());
        assertEquals(0, cups.getMoveCount());
    }


    private static Stream<Arguments> simulateExample01() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 1, Map.ofEntries(entry(3, 2), entry(2, 8), entry(8, 9), entry(9, 1), entry(1, 5), entry(5, 4), entry(4, 6), entry(6, 7), entry(7, 3))),
                Arguments.of( 2, Map.ofEntries(entry(3, 2), entry(2, 5), entry(5, 4), entry(4, 6), entry(6, 7), entry(7, 8), entry(8, 9), entry(9, 1), entry(1, 3))),
                Arguments.of( 3, Map.ofEntries(entry(3, 4), entry(4, 6), entry(6, 7), entry(7, 2), entry(2, 5), entry(5, 8), entry(8, 9), entry(9, 1), entry(1, 3))),
                Arguments.of( 4, Map.ofEntries(entry(4, 6), entry(6, 7), entry(7, 9), entry(9, 1), entry(1, 3), entry(3, 2), entry(2, 5), entry(5, 8), entry(8, 4))),
                Arguments.of( 5, Map.ofEntries(entry(4, 1), entry(1, 3), entry(3, 6), entry(6, 7), entry(7, 9), entry(9, 2), entry(2, 5), entry(5, 8), entry(8, 4))),
                Arguments.of( 6, Map.ofEntries(entry(4, 1), entry(1, 9), entry(9, 3), entry(3, 6), entry(6, 7), entry(7, 2), entry(2, 5), entry(5, 8), entry(8, 4))),
                Arguments.of( 7, Map.ofEntries(entry(4, 1), entry(1, 9), entry(9, 2), entry(2, 5), entry(5, 8), entry(8, 3), entry(3, 6), entry(6, 7), entry(7, 4))),
                Arguments.of( 8, Map.ofEntries(entry(4, 1), entry(1, 5), entry(5, 8), entry(8, 3), entry(3, 9), entry(9, 2), entry(2, 6), entry(6, 7), entry(7, 4))),
                Arguments.of( 9, Map.ofEntries(entry(5, 7), entry(7, 4), entry(4, 1), entry(1, 8), entry(8, 3), entry(3, 9), entry(9, 2), entry(2, 6), entry(6, 5))),
                Arguments.of(10, Map.ofEntries(entry(5, 8), entry(8, 3), entry(3, 7), entry(7, 4), entry(4, 1), entry(1, 9), entry(9, 2), entry(2, 6), entry(6, 5)))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("simulateExample01")
    void testSimulate(final int simulateCount, final Map<Integer, Integer> expectedCups) {
        final CrabCups cups = new CrabCups("389125467", 9);
        cups.simulate(simulateCount);
        assertEquals(expectedCups, cups.getNextCupByCupLabel());
    }

    @Test
    void testSimulateWithManyCups() {
        final CrabCups cups = new CrabCups("389125467", 1000000);
        cups.simulate(10000000);
        assertEquals(List.of(934001, 159792), cups.findCupsWithStars());
        assertEquals(149245887792L, cups.multiplyCupsWithStars());
    }

    @Test
    void testGetCupLabeling() {
        final CrabCups cups = new CrabCups("389125467", 9);
        assertEquals("25467389", cups.getCupLabeling());
    }
}