package nl.smerik.adventofcode.aoc2023.model.cosmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosmicTest {

    private static final List<String> INPUT_EXAMPLE_01 = List.of(
            // @formatter:off
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#....."
            // @formatter:on
    );

    @Test
    void testFindEmptyColumns() {
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        assertEquals(List.of(2, 5, 8), cosmic.findEmptyColums());
    }

    @Test
    void testFindEmptyRows() {
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        assertEquals(List.of(3, 7), cosmic.findEmptyRows());
    }

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            " 374,   2",
            "1030,  10",
            "8410, 100"
            // @formatter:on
    })
    void testSumShortestPathBetweenAllGalaxyPairs(final int expectedResult, final int expansionFactor) {
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        cosmic.setExpansionFactor(expansionFactor);
        assertEquals(expectedResult, cosmic.sumShortestPathBetweenAllGalaxyPairs());
    }

    public static Stream<Arguments> findShortestPathLengthSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 9, new GalaxyPair(new Point(1, 5), new Point(4, 9))), // Galaxies 5 & 9
                Arguments.of(15, new GalaxyPair(new Point(3, 0), new Point(7, 8))), // Galaxies 1 & 7
                Arguments.of(17, new GalaxyPair(new Point(0, 2), new Point(9, 6))), // Galaxies 3 & 6
                Arguments.of( 5, new GalaxyPair(new Point(0, 9), new Point(4, 9)))  // Galaxies 8 & 9
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findShortestPathLengthSource")
    void testFindShortestPathLength(final int expectedResult, final GalaxyPair pair) {
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        cosmic.setExpansionFactor(2);
        assertEquals(expectedResult, cosmic.findShortestPathLength(pair));
    }

    @Test
    void testFindGalaxyPairs() {
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        assertEquals(36, cosmic.findGalaxyPairs().size());
    }

    @Test
    void testFindGalaxies() {
        // Given
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        // When
        final Set<Point> result = cosmic.findGalaxies();
        // Then
        assertEquals(9, result.size());
    }

    @Test
    void testRender() {
        final String expectedResult =
                // @formatter:off
                "...#......" + System.lineSeparator() +
                ".......#.." + System.lineSeparator() +
                "#........." + System.lineSeparator() +
                ".........." + System.lineSeparator() +
                "......#..." + System.lineSeparator() +
                ".#........" + System.lineSeparator() +
                ".........#" + System.lineSeparator() +
                ".........." + System.lineSeparator() +
                ".......#.." + System.lineSeparator() +
                "#...#....." + System.lineSeparator();
        // @formatter:on
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        assertEquals(expectedResult, cosmic.render());
    }
}