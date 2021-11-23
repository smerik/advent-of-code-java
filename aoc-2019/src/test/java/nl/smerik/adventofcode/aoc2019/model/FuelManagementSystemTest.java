package nl.smerik.adventofcode.aoc2019.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuelManagementSystemTest {

    @Test
    void getIntersectionsBetweenWires() {
        final Set<Point> intersections = new HashSet<>(Arrays.asList(new Point(0, 0), new Point(3, 3), new Point(6, 5)));

        final FuelManagementSystem fms = new FuelManagementSystem();
        fms.addWire(new Wire("R8,U5,L5,D3"));
        fms.addWire(new Wire("U7,R6,D4,L4"));

        final Set<Point> result = fms.getIntersectionsBetweenWires();

        assertEquals(intersections, result);
    }

    // TODO: refactor into @ParameterizedTest
    @Test
    void getManhattanDistanceFromCentralPortToClosestIntersection() {
        final FuelManagementSystem fms = new FuelManagementSystem();
        fms.addWire(new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72"));
        fms.addWire(new Wire("U62,R66,U55,R34,D71,R55,D58,R83"));

        final int result = fms.getManhattanDistanceFromCentralPortToClosestIntersection();
        assertEquals(159, result);


        final FuelManagementSystem fms2 = new FuelManagementSystem();
        fms2.addWire(new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"));
        fms2.addWire(new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"));
        final int result2 = fms2.getManhattanDistanceFromCentralPortToClosestIntersection();
        assertEquals(135, result2);
    }

    // TODO: refactor into @ParameterizedTest
    @Test
    void getFewestCombinedStepsToReachAnIntersection() {
        final FuelManagementSystem fms = new FuelManagementSystem();
        fms.addWire(new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72"));
        fms.addWire(new Wire("U62,R66,U55,R34,D71,R55,D58,R83"));

        final int result = fms.getFewestCombinedStepsToReachAnIntersection();
        assertEquals(610, result);


        final FuelManagementSystem fms2 = new FuelManagementSystem();
        fms2.addWire(new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"));
        fms2.addWire(new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"));
        final int result2 = fms2.getFewestCombinedStepsToReachAnIntersection();
        assertEquals(410, result2);
    }
}