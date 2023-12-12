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

    private static final List<String> INPUT_EXAMPLE_03 = List.of(
            // @formatter:off
            "...........",
            ".S-------7.",
            ".|F-----7|.",
            ".||.....||.",
            ".||.....||.",
            ".|L-7.F-J|.",
            ".|..|.|..|.",
            ".L--J.L--J.",
            "..........."
            // @formatter:on
    );

    private static final List<String> INPUT_EXAMPLE_04 = List.of(
            // @formatter:off
            ".F----7F7F7F7F-7....",
            ".|F--7||||||||FJ....",
            ".||.FJ||||||||L7....",
            "FJL7L7LJLJ||LJ.L-7..",
            "L--J.L7...LJS7F-7L7.",
            "....F-J..F7FJ|L7L7L7",
            "....L7.F7||L7|.L7L7|",
            ".....|FJLJ|FJ|F7|.LJ",
            "....FJL-7.||.||||...",
            "....L---J.LJ.LJLJ..."
            // @formatter:on
    );

    private static final List<String> INPUT_EXAMPLE_05 = List.of(
            // @formatter:off
            "FF7FSF7F7F7F7F7F---7",
            "L|LJ||||||||||||F--J",
            "FL-7LJLJ||||||LJL-77",
            "F--JF--7||LJLJ7F7FJ-",
            "L---JF-JLJ.||-FJLJJ7",
            "|F|F-JF---7F7-L7L|7|",
            "|FFJF7L7F-JF7|JL---7",
            "7-L-JL7||F7|L7F-7F7|",
            "L.L7LFJ|||||FJL7||LJ",
            "L7JLJL-JLJLJL--JLJ.L"
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

    public static Stream<Arguments> countTilesEnclosedByLoopSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(  4, INPUT_EXAMPLE_03),
                Arguments.of(  8, INPUT_EXAMPLE_04),
                Arguments.of( 10, INPUT_EXAMPLE_05)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("countTilesEnclosedByLoopSource")
    void testCountTilesEnclosedByLoop(final int expectedCount, final List<String> input) {
        final PipeMaze maze = new PipeMaze(input);
        assertEquals(expectedCount, maze.findTilesEnclosedByLoop().size());
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
                "S╝I╚╗" + System.lineSeparator() +
                "║╔══╝" + System.lineSeparator() +
                "╚╝·└┘" + System.lineSeparator();
                // @formatter:off
        final PipeMaze maze = new PipeMaze(INPUT_EXAMPLE_02_EXTRA_NON_LOOP);
        assertEquals(expectedResult, maze.render(true));
    }
}