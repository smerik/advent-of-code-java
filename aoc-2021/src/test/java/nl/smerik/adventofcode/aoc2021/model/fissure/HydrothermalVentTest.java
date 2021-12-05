package nl.smerik.adventofcode.aoc2021.model.fissure;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HydrothermalVentTest {

    final List<String> LINE_SEGMENTS_EXAMPLE_PART_01 = List.of(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
    );

    @Test
    void testInit() {
        final HydrothermalVent vent = new HydrothermalVent(List.of("1,1 -> 1,3", "9,7 -> 7,7"));
        final Map<Point, Integer> coveredPointCountByCoordinates = vent.getCoveredByCoordinates();
        assertEquals(6, coveredPointCountByCoordinates.keySet().size());

        assertEquals(1, coveredPointCountByCoordinates.get(new Point(1,1)));
        assertEquals(1, coveredPointCountByCoordinates.get(new Point(1,2)));
        assertEquals(1, coveredPointCountByCoordinates.get(new Point(1,3)));

        assertEquals(1, coveredPointCountByCoordinates.get(new Point(9,7)));
        assertEquals(1, coveredPointCountByCoordinates.get(new Point(8,7)));
        assertEquals(1, coveredPointCountByCoordinates.get(new Point(7,7)));
    }

    @Test
    void testCountOverlappingPoints() {
        final HydrothermalVent vent = new HydrothermalVent(LINE_SEGMENTS_EXAMPLE_PART_01);
        assertEquals(5, vent.countOverlappingPoints());
    }
}