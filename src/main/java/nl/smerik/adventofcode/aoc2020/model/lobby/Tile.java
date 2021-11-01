package nl.smerik.adventofcode.aoc2020.model.lobby;

import lombok.Data;

import java.awt.Point;
import java.util.List;

@Data
public class Tile {

    private final Point position;
    private int flipCount;
    private TileColor color;

    public Tile() {
        this.position = new Point();
        this.color = TileColor.WHITE;
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
}
