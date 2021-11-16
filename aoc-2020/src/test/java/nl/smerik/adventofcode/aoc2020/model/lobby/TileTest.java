package nl.smerik.adventofcode.aoc2020.model.lobby;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.Point;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2020.model.lobby.HexagonalDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the class {@link Tile}.
 */
class TileTest {

    private static Stream<Arguments> examplePart01() {
        return Stream.of(
                // @formatter:off
                Arguments.of(new Point(6, 0), List.of(EAST, SOUTHEAST, NORTHEAST, EAST)      ), // esenee
                Arguments.of(new Point(1, 1), List.of(EAST, SOUTHEAST, WEST)                 ), // esew
                Arguments.of(new Point(0, 0), List.of(NORTHWEST, WEST, SOUTHWEST, EAST, EAST))  // nwwswee
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("examplePart01")
    void testMove(final Point expectedLocation, List<HexagonalDirection> directions) {
        final Tile tile = new Tile();
        assertEquals(expectedLocation, tile.move(directions));
    }

    @Test
    void testFlip() {
        final Tile tile = new Tile();
        assertEquals(0, tile.getFlipCount());
        assertEquals(TileColor.WHITE, tile.getColor());

        tile.flip();
        assertEquals(1, tile.getFlipCount());
        assertEquals(TileColor.BLACK, tile.getColor());

        tile.flip();
        assertEquals(2, tile.getFlipCount());
        assertEquals(TileColor.WHITE, tile.getColor());
    }

    @Test
    void testGetNeighboursPositions() {
        final Set<Point> expectedPositions = Set.of(
                new Point(9,3),
                new Point(8,2),
                new Point(6,2),
                new Point(5,3),
                new Point(6,4),
                new Point(8,4)
        );

        final Tile tile = new Tile();
        tile.getPosition().move(7,3);
        final Set<Point> neighboursPositions = tile.getNeighboursPositions();

        assertEquals(expectedPositions, neighboursPositions);
    }
}