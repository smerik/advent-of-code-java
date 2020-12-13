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
        final Ferry ferry = new Ferry(new Point());
        ferry.navigate(instructions);
        assertEquals(new Point(17, -8), ferry.getLocation());
    }
}