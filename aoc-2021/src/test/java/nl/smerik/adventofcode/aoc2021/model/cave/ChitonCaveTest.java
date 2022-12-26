package nl.smerik.adventofcode.aoc2021.model.cave;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChitonCaveTest {

    private static final List<String> RISK_TILE_EXAMPLE_01 = List.of(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581"
    );

    List<Point> EXAMPLE_01_PATH_WITH_LOWEST_RISK = List.of(
            // @formatter:off
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 2), new Point(1,2), new Point(2, 2), new Point(3, 2), new Point(4, 2), new Point(5, 2), new Point(6, 2),
            new Point(6, 3), new Point(7, 3),
            new Point(7, 4),
            new Point(7, 5), new Point(8, 5),
            new Point(8, 6),
            new Point(8, 7),
            new Point(8, 8), new Point(9, 8),
            new Point(9, 9)
            // @formatter:on
    );

    @Test
    void findPathWithLowestTotalRisk() {
        // Given
        final ChitonCave map = new ChitonCave(RISK_TILE_EXAMPLE_01);
        // When
        final List<Point> pathWithLowestTotalRisk = map.findPathWithLowestTotalRisk();
        // Then
        assertEquals(EXAMPLE_01_PATH_WITH_LOWEST_RISK, pathWithLowestTotalRisk);
    }

    @Test
    void calculateTotalRisk() {
        final ChitonCave map = new ChitonCave(RISK_TILE_EXAMPLE_01);
        assertEquals(40, map.calculateTotalRisk());
    }

    @Test
    void calculateTotalRiskUsingTileDimensionMultiplier() {
        final ChitonCave cave = new ChitonCave(RISK_TILE_EXAMPLE_01, 5);
        // then
        assertEquals(315, cave.calculateTotalRisk());
    }
}