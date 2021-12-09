package nl.smerik.adventofcode.aoc2021.model.cave;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CaveMapTest {

    private static final List<String> HEIGHTMAP_EXAMPLE_01 = List.of(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
    );

    @Test
    void testInit() {
        final CaveMap caveMap = new CaveMap(HEIGHTMAP_EXAMPLE_01);
        assertEquals(2, caveMap.getHeightByLocations().get(new Point(0, 0)));
        assertEquals(0, caveMap.getHeightByLocations().get(new Point(9, 0)));
        assertEquals(9, caveMap.getHeightByLocations().get(new Point(0, 4)));
        assertEquals(8, caveMap.getHeightByLocations().get(new Point(9, 4)));
    }

    @Test
    void testFindLowPoints() {
        final CaveMap caveMap = new CaveMap(HEIGHTMAP_EXAMPLE_01);
        final Set<Point> result = caveMap.findLowPoints();
        assertEquals(Set.of(new Point(1, 0), new Point(9, 0), new Point(2, 2), new Point(6, 4)), result);
        assertEquals(4, result.size());
        assertEquals(1, caveMap.getHeightByLocations().get(new Point(1, 0)));
        assertEquals(0, caveMap.getHeightByLocations().get(new Point(9, 0)));
        assertEquals(5, caveMap.getHeightByLocations().get(new Point(2, 2)));
        assertEquals(5, caveMap.getHeightByLocations().get(new Point(6, 4)));
    }


    private static Stream<Arguments> riskLevels() {
        return Stream.of(
                Arguments.of(2, 1),
                Arguments.of(1, 0),
                Arguments.of(6, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("riskLevels")
    void testCalculateRiskLevel(final int expectedRiskLevel, final int height) {
        assertEquals(expectedRiskLevel, CaveMap.calculateRiskLevel(height));
    }

    @Test
    void testSumRiskLevels() {
        final CaveMap caveMap = new CaveMap(HEIGHTMAP_EXAMPLE_01);
        assertEquals(15, caveMap.sumRiskLevels());
    }

    @Test
    void testCalculateBasinSize() {
        final CaveMap caveMap = new CaveMap(HEIGHTMAP_EXAMPLE_01);
        assertEquals(3, caveMap.calculateBasinSize(new Point(new Point(1, 0))));
    }


    private static Stream<Arguments> basinLocations() {
        return Stream.of(
                Arguments.of(new Point(1, 0), Set.of(new Point(0, 0), new Point(1, 0), new Point(0, 1))),
                Arguments.of(new Point(9, 0), Set.of(new Point(5, 0), new Point(6, 0), new Point(7, 0), new Point(8, 0), new Point(9, 0), new Point(6, 1), new Point(8, 1), new Point(9, 1), new Point(9, 2))),
                Arguments.of(new Point(2, 2), Set.of(new Point(2, 1), new Point(3, 1), new Point(4, 1), new Point(1, 2), new Point(2, 2), new Point(3, 2), new Point(4, 2), new Point(5, 2), new Point(0, 3), new Point(1, 3), new Point(2, 3), new Point(3, 3), new Point(4, 3), new Point(1, 4))),
                Arguments.of(new Point(6, 4), Set.of(new Point(7, 2), new Point(6, 3), new Point(7, 3), new Point(8, 3), new Point(5, 4), new Point(6, 4), new Point(7, 4), new Point(8, 4), new Point(9, 4)))
        );
    }

    @ParameterizedTest
    @MethodSource("basinLocations")
    void testFindBasinLocations(final Point lowPoint, final Set<Point> expectedBasinLocations) {
        final CaveMap caveMap = new CaveMap(HEIGHTMAP_EXAMPLE_01);
        assertEquals(expectedBasinLocations, caveMap.findBasinLocations(lowPoint));
    }

    @Test
    void testDetermineThreeLargestBasins() {
        final CaveMap caveMap = new CaveMap(HEIGHTMAP_EXAMPLE_01);
        assertEquals(List.of(14, 9, 9), caveMap.determineThreeLargestBasins());
    }

    @Test
    void testMultiplyThreeLargestBasins() {
        final CaveMap caveMap = new CaveMap(HEIGHTMAP_EXAMPLE_01);
        assertEquals(1134, caveMap.multiplyThreeLargestBasins());
    }
}