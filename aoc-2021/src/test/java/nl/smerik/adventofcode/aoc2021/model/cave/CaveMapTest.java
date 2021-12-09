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
}