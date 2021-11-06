package nl.smerik.adventofcode.aoc2020.model.lobby;

import lombok.Data;

import java.awt.Point;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Tile {

    private final Point position;
    private int flipCount;
    private TileColor color = TileColor.WHITE;

    public Tile() {
        this.position = new Point();
    }

    public Tile(final Point point) {
        this.position = point;
    }

    /**
     * Moves the tile following given directions.
     *
     * @param directions the directions to move to
     * @return the location the tile has been moved to
     */
    public Point move(final List<HexagonalDirection> directions) {
        for (final HexagonalDirection direction : directions) {
            switch (direction) {
                // https://www.redblobgames.com/grids/hexagons/#coordinates-doubled
                // “double-width” horizontal layout; doubles column values
                case EAST -> this.position.translate(2, 0);
                case SOUTHEAST -> this.position.translate(1, 1);
                case SOUTHWEST -> this.position.translate(-1, 1);
                case WEST -> this.position.translate(-2, 0);
                case NORTHWEST -> this.position.translate(-1, -1);
                case NORTHEAST -> this.position.translate(1, -1);
                default -> throw new IllegalArgumentException("Direction not implemented: " + direction.name());
            }
        }
        return this.position;
    }

    /**
     * Flips the tile.
     * When the white color is facing up the tile will be flipped to black
     * and when the black color is facing up it will be flipped to white.
     */
    public void flip() {
        this.flipCount++;
        this.color = this.color == TileColor.WHITE ? TileColor.BLACK : TileColor.WHITE;
    }

    public Set<Point> getNeighboursPositions() {
        // TODO: quick 'n dirty implementation should be refactored
        final Point east = new Point(this.position);
        east.translate(2, 0);

        final Point southeast = new Point(this.position);
        southeast.translate(1, 1);

        final Point southwest = new Point(this.position);
        southwest.translate(-1, 1);

        final Point west = new Point(this.position);
        west.translate(-2, 0);

        final Point northwest = new Point(this.position);
        northwest.translate(-1, -1);

        final Point norteast = new Point(this.position);
        norteast.translate(1, -1);

        return Stream.of(east, southeast, southwest, west, northwest, norteast).collect(Collectors.toSet());
    }
}
