package nl.smerik.adventofcode.geom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    private static Stream<Arguments> translateArguments() {
        // @formatter:off
        return Stream.of(
            Arguments.of(new Point3D(10, 20, 30),  5,  6,  7, 15, 26, 37),
            Arguments.of(new Point3D(10, 20, 30), -5, -6, -7,  5, 14, 23)
        );
        // @formatter:on
    }

    @ParameterizedTest
    @MethodSource("translateArguments")
    void testTranslate(final Point3D point, final int dx, final int dy, final int dz,
                       final int expectedX, final int expectedY, final int expectedZ) {
        point.translate(dx, dy, dz);
        assertEquals(expectedX, point.getX());
        assertEquals(expectedY, point.getY());
        assertEquals(expectedZ, point.getZ());
    }

    @Test
    void getNeighbours() {
        final Point3D point = new Point3D();

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