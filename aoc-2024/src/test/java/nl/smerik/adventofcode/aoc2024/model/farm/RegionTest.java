package nl.smerik.adventofcode.aoc2024.model.farm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @Test
    void testAddPlot() {
        // Given
        final Region region = new Region(1, 'A', 3, 4);
        // When
        region.addPlot(2, 3);
        // Then
        assertEquals(5, region.getPerimeter());
        assertEquals(7, region.getSideCount());
        assertEquals(2, region.getPlotCount());
    }

    @Test
    void testCalculateFencingPrice() {
        // Given
        final Region region = new Region(2, 'B', 3, 4);
        // When
        assertEquals(3, region.calculateFencingPrice());
    }

    @Test
    void testCalculateFencingPriceOnDiscount() {
        // Given
        final Region region = new Region(2, 'B', 3, 4);
        // When
        assertEquals(4, region.calculateFencingPriceOnDiscount());
    }
}