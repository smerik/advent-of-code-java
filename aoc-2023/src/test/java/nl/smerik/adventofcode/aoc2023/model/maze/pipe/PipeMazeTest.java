package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PipeMazeTest {

    private static final List<String> INPUT_EXAMPLE_01 = List.of(
            // @formatter:off
            ".....",
            ".S-7.",
            ".|.|.",
            ".L-J.",
            "....."
            // @formatter:on
    );

    private static final List<String> INPUT_EXAMPLE_01_MORE_COMPLEX = List.of(
            // @formatter:off
            "-L|F7",
            "7S-7|",
            "L|7||",
            "-L-J|",
            "L|-JF"
            // @formatter:on
    );

    private static final List<String> INPUT_EXAMPLE_02 = List.of(
            // @formatter:off
            "..F7.",
            ".FJ|.",
            "SJ.L7",
            "|F--J",
            "LJ..."
            // @formatter:on
    );

    private static final List<String> INPUT_EXAMPLE_02_EXTRA_NON_LOOP = List.of(
            // @formatter:off
            "7-F7-",
            ".FJ|7",
            "SJLL7",
            "|F--J",
            "LJ.LJ"
            // @formatter:on
    );

    public static Stream<Arguments> determineStepCountFromStartToFarthestPointSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(4, INPUT_EXAMPLE_01               ),
                Arguments.of(4, INPUT_EXAMPLE_01_MORE_COMPLEX  ),
                Arguments.of(8, INPUT_EXAMPLE_02               ),
                Arguments.of(8, INPUT_EXAMPLE_02_EXTRA_NON_LOOP)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("determineStepCountFromStartToFarthestPointSource")
    void testDetermineStepCountFromStartToFarthestPoint(final int expectedCount, final List<String> input) {
        final PipeMaze maze = new PipeMaze(input);
        assertEquals(expectedCount, maze.determineStepCountFromStartToFarthestPoint());
    }

    public static Stream<Arguments> findSingleGiantLoopSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 8, INPUT_EXAMPLE_01),
                Arguments.of( 8, INPUT_EXAMPLE_01_MORE_COMPLEX),
                Arguments.of(16, INPUT_EXAMPLE_02),
                Arguments.of(16, INPUT_EXAMPLE_02_EXTRA_NON_LOOP)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findSingleGiantLoopSource")
    void testFindSingleGiantLoop(final int expectedLoopSize, final List<String> input) {
        final PipeMaze maze = new PipeMaze(input);
        assertEquals(expectedLoopSize, maze.findSingleGiantLoop().size());
    }

    @Test
    void testRenderMaze() {
        final String expectedResult =
                // @formatter:off
                "┐─┌┐─" + System.lineSeparator() +
                "·┌┘│┐" + System.lineSeparator() +
                "S┘└└┐" + System.lineSeparator() +
                "│┌──┘" + System.lineSeparator() +
                "└┘·└┘" + System.lineSeparator();
                // @formatter:off
        final PipeMaze maze = new PipeMaze(INPUT_EXAMPLE_02_EXTRA_NON_LOOP);
        assertEquals(expectedResult, maze.render(false));
    }

    @Test
    void testRenderMazeMarkedLoop() {
        final String expectedResult =
                // @formatter:off
                "┐─╔╗─" + System.lineSeparator() +
                "·╔╝║┐" + System.lineSeparator() +
                "S╝└╚╗" + System.lineSeparator() +
                "║╔══╝" + System.lineSeparator() +
                "╚╝·└┘" + System.lineSeparator();
                // @formatter:off
        final PipeMaze maze = new PipeMaze(INPUT_EXAMPLE_02_EXTRA_NON_LOOP);
        assertEquals(expectedResult, maze.render(true));
    }
}