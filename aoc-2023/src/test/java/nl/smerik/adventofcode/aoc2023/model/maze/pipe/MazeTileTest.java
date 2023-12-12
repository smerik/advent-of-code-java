package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2023.model.maze.pipe.TileType.*;
import static org.junit.jupiter.api.Assertions.*;

class MazeTileTest {

    public static Stream<Arguments> canConnectToSource() {
        final Point pointCenter = new Point(0, 0);
        final Point pointNorth = new Point(0, 1);
        final Point pointEast = new Point(1, 0);
        final Point pointSouth = new Point(0, -1);
        final Point pointWest = new Point(-1, 0);
        return Stream.of(
                // @formatter:off
                // PIPE_VERTICAL - PIPE_VERTICAL
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, PIPE_VERTICAL)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, PIPE_VERTICAL)),
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, PIPE_VERTICAL)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, PIPE_VERTICAL)),
                // PIPE_VERTICAL - PIPE_HORIZONTAL
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, PIPE_HORIZONTAL)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, PIPE_HORIZONTAL)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, PIPE_HORIZONTAL)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, PIPE_HORIZONTAL)),
                // PIPE_VERTICAL - PIPE_BEND_NORTH_EAST
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, PIPE_BEND_NORTH_EAST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, PIPE_BEND_NORTH_EAST)),
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, PIPE_BEND_NORTH_EAST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, PIPE_BEND_NORTH_EAST)),
                // PIPE_VERTICAL - PIPE_BEND_NORTH_WEST
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, PIPE_BEND_NORTH_WEST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, PIPE_BEND_NORTH_WEST)),
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, PIPE_BEND_NORTH_WEST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, PIPE_BEND_NORTH_WEST)),
                // PIPE_VERTICAL - PIPE_BEND_SOUTH_WEST
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, PIPE_BEND_SOUTH_WEST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, PIPE_BEND_SOUTH_WEST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, PIPE_BEND_SOUTH_WEST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, PIPE_BEND_SOUTH_WEST)),
                // PIPE_VERTICAL - PIPE_BEND_SOUTH_EAST
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, PIPE_BEND_SOUTH_EAST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, PIPE_BEND_SOUTH_EAST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, PIPE_BEND_SOUTH_EAST)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, PIPE_BEND_SOUTH_EAST)),
                // PIPE_VERTICAL - GROUND
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, GROUND)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, GROUND)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, GROUND)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, GROUND)),
                // PIPE_VERTICAL - STARTING_POSITION
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointNorth, STARTING_POSITION)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointEast, STARTING_POSITION)),
                Arguments.of(true , new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointSouth, STARTING_POSITION)),
                Arguments.of(false, new MazeTile(pointCenter, PIPE_VERTICAL), new MazeTile(pointWest, STARTING_POSITION)),
                // TODO: test below too + make test implementation smarter
                //    PIPE_HORIZONTAL('-'),
                //    PIPE_BEND_NORTH_EAST('L'),
                //    PIPE_BEND_NORTH_WEST('J'),
                //    PIPE_BEND_SOUTH_WEST('7'),
                //    PIPE_BEND_SOUTH_EAST('F'),
                //    GROUND('.'),
                //    STARTING_POSITION('S');
                // STARTING_POSITION - GROUND
                Arguments.of(false, new MazeTile(pointCenter, STARTING_POSITION), new MazeTile(pointNorth, GROUND)),
                Arguments.of(false, new MazeTile(pointCenter, STARTING_POSITION), new MazeTile(pointEast, GROUND)),
                Arguments.of(false, new MazeTile(pointCenter, STARTING_POSITION), new MazeTile(pointSouth, GROUND)),
                Arguments.of(false, new MazeTile(pointCenter, STARTING_POSITION), new MazeTile(pointWest, GROUND))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("canConnectToSource")
    void testCanConnectTo(final boolean expectedResult, final MazeTile tile1, final MazeTile tile2) {
        assertEquals(expectedResult, tile1.canConnectTo(tile2));
    }
}