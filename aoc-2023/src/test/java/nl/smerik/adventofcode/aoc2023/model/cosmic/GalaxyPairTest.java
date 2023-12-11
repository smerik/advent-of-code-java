package nl.smerik.adventofcode.aoc2023.model.cosmic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyPairTest {

    public static Stream<Arguments> findShortestPathLengthSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 9, new GalaxyPair(new Point( 1,  6), new Point( 5, 11))), // Galaxies 5 & 9
                Arguments.of(15, new GalaxyPair(new Point( 0,  4), new Point( 9, 10))),   // Galaxies 1 & 7
                Arguments.of(17, new GalaxyPair(new Point( 0,  2), new Point(12,  7))),  // Galaxies 3 & 6
                Arguments.of( 5, new GalaxyPair(new Point( 0, 11), new Point( 5, 11)))  // Galaxies 8 & 9
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findShortestPathLengthSource")
    void testFindShortestPathLength(final int expectedResult, final GalaxyPair pair) {
        assertEquals(expectedResult, pair.findShortestPathLength());
    }

    public static Stream<Arguments> equalsSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, new GalaxyPair(new Point(1, 1), new Point(2, 2)), new GalaxyPair(new Point(1, 1), new Point(3, 3))),
                Arguments.of(true , new GalaxyPair(new Point(1, 1), new Point(2, 2)), new GalaxyPair(new Point(1, 1), new Point(2, 2))),
                Arguments.of(true , new GalaxyPair(new Point(1, 1), new Point(2, 2)), new GalaxyPair(new Point(2, 2), new Point(1, 1)))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("equalsSource")
    void testEquals(final boolean expectedResult, final GalaxyPair pair1, final GalaxyPair pair2) {
        assertEquals(expectedResult, pair1.equals(pair2));
    }
}