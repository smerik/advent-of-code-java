package nl.smerik.adventofcode.aoc2023.model.cosmic;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.Set;

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
    void testExpandUniverse() {
        final String expectedResult =
                // @formatter:off
                "....#........" + System.lineSeparator() +
                ".........#..." + System.lineSeparator() +
                "#............" + System.lineSeparator() +
                "............." + System.lineSeparator() +
                "............." + System.lineSeparator() +
                "........#...." + System.lineSeparator() +
                ".#..........." + System.lineSeparator() +
                "............#" + System.lineSeparator() +
                "............." + System.lineSeparator() +
                "............." + System.lineSeparator() +
                ".........#..." + System.lineSeparator() +
                "#....#......." + System.lineSeparator();
        // @formatter:on
        // Given
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        // When
        cosmic.expandUniverse();
        // Then
        assertEquals(expectedResult, cosmic.render());
    }

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

    @Test
    void testSumShortestPathBetweenAllGalaxyPairs() {
        final Cosmic cosmic = new Cosmic(INPUT_EXAMPLE_01);
        cosmic.expandUniverse();
        assertEquals(374, cosmic.sumShortestPathBetweenAllGalaxyPairs());
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