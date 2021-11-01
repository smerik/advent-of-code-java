package nl.smerik.adventofcode.aoc2020.model.lobby;

import lombok.Getter;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests the {@link LobbyLayout} class.
 */
@Getter
public class LobbyLayout {

    private final Map<Point, Tile> tilesByPosition;

    public LobbyLayout(final List<String> lines) {
        this.tilesByPosition = solve(lines);
    }

    private Map<Point, Tile> solve(final List<String> lines) {
        final Map<Point, Tile> result = new HashMap<>();

        for (final String line : lines) {
            final Tile tile = new Tile();
            final Point identifiedPosition = tile.move(HexagonalDirection.parseSteps(line));
            // Each time a tile is identified, it flips from white to black or from black to white
            final Tile identifiedTile = result.computeIfAbsent(identifiedPosition, k -> tile);
            identifiedTile.flip();
        }
        return result;
    }

    /**
     * Counts the tiles with given color facing up.
     *
     * @param color the facing up color
     * @return the total amount of tiles with given color facing up
     */
    public long countTilesWithColorUp(final TileColor color) {
        return tilesByPosition.values().stream().filter(tile -> tile.getColor() == color).count();
    }
}
