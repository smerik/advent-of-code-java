package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.Set;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2023.model.maze.pipe.TileType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TileTypeTest {

    public static Stream<Arguments> getConnectablePointsSource() {
        final Point pointNorth = new Point(0, 1);
        final Point pointEast = new Point(1, 0);
        final Point pointSouth = new Point(0, -1);
        final Point pointWest = new Point(-1, 0);
        return Stream.of(
                //@formatter:off
                Arguments.of(PIPE_VERTICAL       , Set.of(pointNorth, pointSouth)),
                Arguments.of(PIPE_HORIZONTAL     , Set.of(pointEast, pointWest)),
                Arguments.of(PIPE_BEND_NORTH_EAST, Set.of(pointNorth, pointEast)),
                Arguments.of(PIPE_BEND_NORTH_WEST, Set.of(pointNorth, pointWest)),
                Arguments.of(PIPE_BEND_SOUTH_WEST, Set.of(pointSouth, pointWest)),
                Arguments.of(PIPE_BEND_SOUTH_EAST, Set.of(pointSouth, pointEast)),
                Arguments.of(GROUND              , Set.of()),
                Arguments.of(STARTING_POSITION   , Set.of(pointNorth, pointEast, pointSouth, pointWest))
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("getConnectablePointsSource")
    void testGetConnectablePoints(final TileType type, final Set<Point> expectedPoints) {
        final Point centerPoint = new Point();
        assertEquals(expectedPoints, type.getConnectablePoints(centerPoint));
    }

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "PIPE_VERTICAL       , |",
            "PIPE_HORIZONTAL     , -",
            "PIPE_BEND_NORTH_EAST, L",
            "PIPE_BEND_NORTH_WEST, J",
            "PIPE_BEND_SOUTH_WEST, 7",
            "PIPE_BEND_SOUTH_EAST, F",
            "GROUND              , .",
            "STARTING_POSITION   , S"
            // @formatter:on
    })
    void testValueOfTile(final TileType expectedType, final char tile) {
        assertEquals(expectedType, TileType.valueOfTile(tile));
    }
}