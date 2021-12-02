package nl.smerik.adventofcode.aoc2021.model.submarine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubmarineTest {

    private static final List<String> COMMANDS_EXAMPLE_PART_01 = List.of(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
    );

    private static Stream<Arguments> moveCommand() {
        // @formatter:off
        return Stream.of(
            Arguments.of("forward 5", 5,  0),
            Arguments.of("down 5"   , 0,  5),
            Arguments.of("up 3"     , 0, -3)
        );
        // @formatter:on
    }

    @ParameterizedTest
    @MethodSource("moveCommand")
    void testMoveCommand(final String command, final int horizontalPosition, final int depth) {
        final Submarine submarine = new Submarine();
        submarine.move(command);
        assertEquals(horizontalPosition, submarine.getHorizontalPosition());
        assertEquals(depth, submarine.getDepth());
    }

    @Test
    void testMoveCommandUnknownDirection() {
        final Submarine submarine = new Submarine();
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> submarine.move("backward 1"));
        assertEquals("Unknown direction 'backward'", exception.getMessage());
    }


    @Test
    void testMoveCommands() {
        final Submarine submarine = new Submarine();
        submarine.move(COMMANDS_EXAMPLE_PART_01);
        assertEquals(15, submarine.getHorizontalPosition());
        assertEquals(10, submarine.getDepth());
    }

    @Test
    void testCalculateSolutionDay2Part1() {
        final Submarine submarine = new Submarine();
        submarine.move("forward 15");
        submarine.move("down 10");
        assertEquals(150, submarine.calculateSolutionDay2Part1());
    }
}