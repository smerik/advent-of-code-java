package nl.smerik.adventofcode.aoc2020.model.ferry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.Point;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FerryTest {

    private static Stream<Arguments> provideSourceForCalculateManhattanDistance() {
        return Stream.of(
                Arguments.of(new Point(), new Point(17, -8), 25),
                Arguments.of(new Point(), new Point(214, 72), 286),
                Arguments.of(new Point(-10, -5), new Point(-5, -5), 5),
                Arguments.of(new Point(-10, -5), new Point(10, 5), 30)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForCalculateManhattanDistance")
    void calculateManhattanDistance(final Point startPosition,
                                    final Point distancePosition,
                                    final int expectedManhattanDistance) {
        final Ferry ferry = new Ferry(startPosition);
        assertEquals(expectedManhattanDistance, ferry.calculateManhattanDistance(distancePosition));
    }

    @Test
    void navigate() {
        final List<String> instructions = List.of(
                "F10",
                "N3",
                "F7",
                "R90",
                "F11"
        );
        final Point startPosition = new Point();
        final Ferry ferry = new Ferry(startPosition);
        ferry.navigate(instructions);

        final Point ferryLocation = ferry.getLocation();
        assertEquals(new Point(17, -8), ferryLocation);
        assertEquals(25, ferry.calculateManhattanDistance(startPosition));
    }

    @Test
    void navigateWithWaypoint() {
        final List<String> instructions = List.of(
                "F10",
                "N3",
                "F7",
                "R90",
                "F11"
        );
        final Point startPosition = new Point();
        final Point waypoint = new Point(10, 1);
        final Ferry ferry = new Ferry(startPosition, waypoint);
        ferry.navigateWithWaypoint(instructions);
        assertEquals(new Point(214, -72), ferry.getLocation());
        assertEquals(286, ferry.calculateManhattanDistance(startPosition));
    }

    private static Stream<Arguments> provideSourceForWaypointRotation() {
        return Stream.of(
                Arguments.of(List.of("L90"), new Point(10, 10)),
                Arguments.of(List.of("R270"), new Point(10, 10)),
                Arguments.of(List.of("L180"), new Point(-8, 10)),
                Arguments.of(List.of("R180"), new Point(-8, 10)),
                Arguments.of(List.of("L270"), new Point(-8, -8)),
                Arguments.of(List.of("R90"), new Point(-8, -8))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForWaypointRotation")
    void waypointRotation(final List<String> instructions, final Point expectedWaypointLocation) {
        final Point startPosition = new Point(1, 1);
        final Point waypoint = new Point(10, -8);
        final Ferry ferry = new Ferry(startPosition, waypoint);
        ferry.navigateWithWaypoint(instructions);
        assertEquals(expectedWaypointLocation, ferry.getWayPoint());
    }
}