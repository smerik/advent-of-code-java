package nl.smerik.adventofcode.aoc2020.model.conway;

import nl.smerik.adventofcode.aoc2020.model.geom.Point3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CubeTest {

    @Test
    void toggleActiveState() {
        final Cube cube = new Cube(new Point3D(0, 0, 0));
        assertFalse(cube.isActive());

        cube.toggleActiveState();
        assertTrue(cube.isActive());
    }
}