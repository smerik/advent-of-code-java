package nl.smerik.adventofcode.aoc2024.model.farm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @Test
    void testAddPlot() {
        // Given
        final Region region = new Region(1, 'A', 3);
        // When
        region.addPlot(2);
        // Then
        assertEquals(2, region.getPlotCount());
        assertEquals(5, region.getPerimeter());
    }

    @Test
    void testCalculateFencingPrice() {
        // Given
        final Region region = new Region(2, 'B', 3);
        // When
        assertEquals(3, region.calculateFencingPrice());
    }
}