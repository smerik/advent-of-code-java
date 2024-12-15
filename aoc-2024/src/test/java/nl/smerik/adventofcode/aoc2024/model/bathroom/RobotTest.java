package nl.smerik.adventofcode.aoc2024.model.bathroom;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    private static Stream<Arguments> provideSourceForMove() {
        return Stream.of(
                //@formatter:off
                Arguments.of(new Point( 4, 1), 1),
                Arguments.of(new Point( 6, 5), 2),
                Arguments.of(new Point( 8, 2), 3),
                Arguments.of(new Point(10, 6), 4),
                Arguments.of(new Point( 1, 3), 5)
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForMove")
    void testMove(final Point expected, final int seconds) {
        // Given
        final Robot robot = new Robot(11, 7, new Point(2, 4), 2, -3);
        // When
        robot.move(seconds);
        // Then
        assertEquals(expected, robot.getCurrentPosition());
    }
}