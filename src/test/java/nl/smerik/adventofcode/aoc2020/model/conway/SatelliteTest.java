package nl.smerik.adventofcode.aoc2020.model.conway;

import nl.smerik.adventofcode.aoc2020.model.geom.Point3D;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SatelliteTest {

    private final List<String> example01Part01 = List.of(
            ".#.",
            "..#",
            "###"
    );

    @Test
    void parseRegionOfCubes() {
        final Satellite satellite = new Satellite(example01Part01);
        assertEquals(9, satellite.getConwayCubes().size());

        assertFalse(satellite.getConwayCubes().get(new Point3D(0, 0, 0)).isActive());
        assertTrue(satellite.getConwayCubes().get(new Point3D(1, 0, 0)).isActive());
        assertFalse(satellite.getConwayCubes().get(new Point3D(2, 0, 0)).isActive());

        assertFalse(satellite.getConwayCubes().get(new Point3D(0, 1, 0)).isActive());
        assertFalse(satellite.getConwayCubes().get(new Point3D(1, 1, 0)).isActive());
        assertTrue(satellite.getConwayCubes().get(new Point3D(2, 1, 0)).isActive());

        assertTrue(satellite.getConwayCubes().get(new Point3D(0, 2, 0)).isActive());
        assertTrue(satellite.getConwayCubes().get(new Point3D(1, 2, 0)).isActive());
        assertTrue(satellite.getConwayCubes().get(new Point3D(2, 2, 0)).isActive());
    }

    @Test
    void execute() {
        final Satellite satellite = new Satellite(example01Part01);
        satellite.execute(1);
        final Set<Point3D> activePoints = satellite.getConwayCubes().values().stream()
                                                                         .filter(Cube::isActive)
                                                                         .map(Cube::getPoint)
                                                                         .collect(Collectors.toSet());

        assertEquals(11, activePoints.size());
        assertTrue(activePoints.contains(new Point3D(0,1,-1)));
        assertTrue(activePoints.contains(new Point3D(1,3,-1)));
        assertTrue(activePoints.contains(new Point3D(2,2,-1)));

        assertTrue(activePoints.contains(new Point3D(0,1,0)));
        assertTrue(activePoints.contains(new Point3D(1,2,0)));
        assertTrue(activePoints.contains(new Point3D(1,3,0)));
        assertTrue(activePoints.contains(new Point3D(2,1,0)));
        assertTrue(activePoints.contains(new Point3D(2,2,0)));

        assertTrue(activePoints.contains(new Point3D(0,1,1)));
        assertTrue(activePoints.contains(new Point3D(1,3,1)));
        assertTrue(activePoints.contains(new Point3D(2,2,1)));
    }

    @Test
    void countActiveCubes() {
        final Satellite satellite = new Satellite(example01Part01);
        satellite.execute(6);

        assertEquals(112, satellite.countActiveCubes());
    }
}