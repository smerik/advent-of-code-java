package nl.smerik.adventofcode.aoc2019.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WireTest {

    @Test
    void getCoordinates() {
        final List<Point> expectedCoordinates = Arrays.asList(
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 1), new Point(1, 2),
                new Point(0, 2), new Point(-1, 2), new Point(-2, 2),
                new Point(-2, 1), new Point(-2, 0), new Point(-2, -1), new Point(-2, -2)
        );
        final Wire wire = new Wire("R1,U2,L3,D4");
        assertEquals(expectedCoordinates, wire.getCoordinates());
    }


    @Test
    void getNumberOfStepsToPoint() {
        final Wire wire1 = new Wire("R8,U5,L5,D3");
        assertEquals(15, wire1.getNumberOfStepsToPoint(new Point(6, 5)));
        assertEquals(20, wire1.getNumberOfStepsToPoint(new Point(3, 3)));

        final Wire wire2 = new Wire("U7,R6,D4,L4");
        assertEquals(15, wire2.getNumberOfStepsToPoint(new Point(6, 5)));
        assertEquals(20, wire2.getNumberOfStepsToPoint(new Point(3, 3)));
    }
}