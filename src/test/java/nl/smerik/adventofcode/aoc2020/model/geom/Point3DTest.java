package nl.smerik.adventofcode.aoc2020.model.geom;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void getNeighbours() {
        final Point3D point = new Point3D(0, 0, 0);

        final Set<Point3D> neighbours = point.getNeighbours();
        assertEquals(26, neighbours.size());

        assertTrue(neighbours.contains(new Point3D(-1, -1, -1)));
        assertTrue(neighbours.contains(new Point3D(-1, -1, 0)));
        assertTrue(neighbours.contains(new Point3D(-1, -1, 1)));

        assertTrue(neighbours.contains(new Point3D(0, -1, -1)));
        assertTrue(neighbours.contains(new Point3D(0, -1, 0)));
        assertTrue(neighbours.contains(new Point3D(0, -1, -1)));

        assertTrue(neighbours.contains(new Point3D(1, -1, -1)));
        assertTrue(neighbours.contains(new Point3D(1, -1, 0)));
        assertTrue(neighbours.contains(new Point3D(1, -1, -1)));


        assertTrue(neighbours.contains(new Point3D(-1, 0, -1)));
        assertTrue(neighbours.contains(new Point3D(-1, 0, 0)));
        assertTrue(neighbours.contains(new Point3D(-1, 0, -1)));

        assertTrue(neighbours.contains(new Point3D(0, 0, -1)));
        assertFalse(neighbours.contains(new Point3D(0, 0, 0)));
        assertTrue(neighbours.contains(new Point3D(0, 0, -1)));

        assertTrue(neighbours.contains(new Point3D(1, 0, -1)));
        assertTrue(neighbours.contains(new Point3D(1, 0, 0)));
        assertTrue(neighbours.contains(new Point3D(1, 0, -1)));


        assertTrue(neighbours.contains(new Point3D(-1, 1, -1)));
        assertTrue(neighbours.contains(new Point3D(-1, 1, 0)));
        assertTrue(neighbours.contains(new Point3D(-1, 1, -1)));

        assertTrue(neighbours.contains(new Point3D(0, 1, -1)));
        assertTrue(neighbours.contains(new Point3D(0, 1, 0)));
        assertTrue(neighbours.contains(new Point3D(0, 1, -1)));

        assertTrue(neighbours.contains(new Point3D(1, 1, -1)));
        assertTrue(neighbours.contains(new Point3D(1, 1, 0)));
        assertTrue(neighbours.contains(new Point3D(1, 1, -1)));
    }
}