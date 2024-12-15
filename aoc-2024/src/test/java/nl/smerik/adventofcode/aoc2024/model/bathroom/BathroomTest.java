package nl.smerik.adventofcode.aoc2024.model.bathroom;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BathroomTest {

    @Value("classpath:input/day-14/example-01.txt")
    private Resource example01Resource;

    private static Stream<Arguments> provideSourceForMapRobotsByPosition() {
        return Stream.of(
                //@formatter:off
                // Initial state: no moves on 0 seconds
                Arguments.of(1, 0, new Point( 0, 0)),
                Arguments.of(1,   0, new Point( 2, 0)),
                Arguments.of(2,   0, new Point( 3, 0)),
                Arguments.of(1,   0, new Point( 6, 3)),
                Arguments.of(1,   0, new Point( 7, 3)),
                Arguments.of(1,   0, new Point( 9, 3)),
                Arguments.of(1,   0, new Point(10, 3)),
                Arguments.of(1,   0, new Point( 0, 4)),
                Arguments.of(1,   0, new Point( 2, 4)),
                Arguments.of(1,   0, new Point( 9, 5)),
                Arguments.of(1,   0, new Point( 7, 6)),
                // State after 100 seconds
                Arguments.of(2, 100, new Point( 6, 0)),
                Arguments.of(1, 100, new Point( 9, 0)),
                Arguments.of(1, 100, new Point( 0, 2)),
                Arguments.of(1, 100, new Point( 1, 3)),
                Arguments.of(1, 100, new Point( 2, 3)),
                Arguments.of(1, 100, new Point( 5, 4)),
                Arguments.of(1, 100, new Point( 3, 5)),
                Arguments.of(2, 100, new Point( 4, 5)),
                Arguments.of(1, 100, new Point( 1, 6)),
                Arguments.of(1, 100, new Point( 6, 6))
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForMapRobotsByPosition")
    void testMapRobotsByPosition(final int expected, final int seconds, final Point position) {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final Bathroom bathroom = new Bathroom(11, 7, lines);
        bathroom.move(seconds);
        final Map<Point, Set<Robot>> robotsByPosition = bathroom.mapRobotsByPosition();
        assertEquals(expected, robotsByPosition.get(position).size());
    }

    @Test
    void testCalculateSafetyFactor() {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final Bathroom bathroom = new Bathroom(11, 7, lines);
        bathroom.move(100);
        assertEquals(12, bathroom.calculateSafetyFactor());
    }

    @Test
    void testMapRobotsByQuadrant() {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final Bathroom bathroom = new Bathroom(11, 7, lines);
        bathroom.move(100);
        final Map<Integer, Set<Robot>> result = bathroom.mapRobotsByQuadrant();
        assertEquals(1, result.get(1).size());
        assertEquals(3, result.get(2).size());
        assertEquals(4, result.get(3).size());
        assertEquals(1, result.get(4).size());
    }
}