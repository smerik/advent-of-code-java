package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2023.model.maze.pipe.TileType.*;
import static org.junit.jupiter.api.Assertions.*;

class PipeTest {

    public static Stream<Arguments> canConnectToSource() {
        final Point pointCenter = new Point(0, 0);
        final Point pointNorth = new Point(0, 1);
        final Point pointEast = new Point(1, 0);
        final Point pointSouth = new Point(0, -1);
        final Point pointWest = new Point(-1, 0);
        return Stream.of(
                // @formatter:off
                // PIPE_VERTICAL - PIPE_VERTICAL
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, PIPE_VERTICAL)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, PIPE_VERTICAL)),
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, PIPE_VERTICAL)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, PIPE_VERTICAL)),
                // PIPE_VERTICAL - PIPE_HORIZONTAL
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, PIPE_HORIZONTAL)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, PIPE_HORIZONTAL)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, PIPE_HORIZONTAL)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, PIPE_HORIZONTAL)),
                // PIPE_VERTICAL - PIPE_BEND_NORTH_EAST
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, PIPE_BEND_NORTH_EAST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, PIPE_BEND_NORTH_EAST)),
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, PIPE_BEND_NORTH_EAST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, PIPE_BEND_NORTH_EAST)),
                // PIPE_VERTICAL - PIPE_BEND_NORTH_WEST
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, PIPE_BEND_NORTH_WEST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, PIPE_BEND_NORTH_WEST)),
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, PIPE_BEND_NORTH_WEST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, PIPE_BEND_NORTH_WEST)),
                // PIPE_VERTICAL - PIPE_BEND_SOUTH_WEST
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, PIPE_BEND_SOUTH_WEST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, PIPE_BEND_SOUTH_WEST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, PIPE_BEND_SOUTH_WEST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, PIPE_BEND_SOUTH_WEST)),
                // PIPE_VERTICAL - PIPE_BEND_SOUTH_EAST
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, PIPE_BEND_SOUTH_EAST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, PIPE_BEND_SOUTH_EAST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, PIPE_BEND_SOUTH_EAST)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, PIPE_BEND_SOUTH_EAST)),
                // PIPE_VERTICAL - GROUND
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, GROUND)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, GROUND)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, GROUND)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, GROUND)),
                // PIPE_VERTICAL - STARTING_POSITION
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointNorth, STARTING_POSITION)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointEast, STARTING_POSITION)),
                Arguments.of(true , new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointSouth, STARTING_POSITION)),
                Arguments.of(false, new Pipe(pointCenter, PIPE_VERTICAL), new Pipe(pointWest, STARTING_POSITION)),
                // TODO: test below too + make test implementation smarter
                //    PIPE_HORIZONTAL('-'),
                //    PIPE_BEND_NORTH_EAST('L'),
                //    PIPE_BEND_NORTH_WEST('J'),
                //    PIPE_BEND_SOUTH_WEST('7'),
                //    PIPE_BEND_SOUTH_EAST('F'),
                //    GROUND('.'),
                //    STARTING_POSITION('S');
                // STARTING_POSITION - GROUND
                Arguments.of(false, new Pipe(pointCenter, STARTING_POSITION), new Pipe(pointNorth, GROUND)),
                Arguments.of(false, new Pipe(pointCenter, STARTING_POSITION), new Pipe(pointEast, GROUND)),
                Arguments.of(false, new Pipe(pointCenter, STARTING_POSITION), new Pipe(pointSouth, GROUND)),
                Arguments.of(false, new Pipe(pointCenter, STARTING_POSITION), new Pipe(pointWest, GROUND))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("canConnectToSource")
    void testCanConnectTo(final boolean expectedResult, final Pipe pipe1, final Pipe pipe2) {
        assertEquals(expectedResult, pipe1.canConnectTo(pipe2));
    }
}