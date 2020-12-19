package nl.smerik.adventofcode.aoc2020.model.conway;

import nl.smerik.adventofcode.aoc2020.model.geom.Point4D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HyperCubeTest {

    @Test
    void toggleActiveState() {
        final HyperCube cube = new HyperCube(new Point4D(0, 0, 0, 0));
        assertFalse(cube.isActive());

        cube.toggleActiveState();
        assertTrue(cube.isActive());
    }
}