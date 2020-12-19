package nl.smerik.adventofcode.aoc2020.model.geom;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Point4DTest {

    @Test
    void getNeighbours() {
        final Point4D point = new Point4D(0, 0, 0, 0);

        final Set<Point4D> neighbours = point.getNeighbours();
        assertEquals(80, neighbours.size());
        assertFalse(neighbours.contains(new Point4D(0, 0, 0, 0)));
    }
}