package nl.smerik.adventofcode.aoc2020.model.lobby;

import lombok.Getter;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Tests the {@link LobbyLayout} class.
 */
@Getter
public class LobbyLayout {

    private final Map<Point, Tile> tilesByPosition;

    public LobbyLayout(final List<String> lines) {
        this.tilesByPosition = identifyTiles(lines);
    }

    private Map<Point, Tile> identifyTiles(final List<String> lines) {
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
     * Flips the tile floor in the lobby for given amount of days.
     * <p>
     * Every day, the tiles are all flipped according to the following rules:
     * <ul>
     *     <li>Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
     *     <li>Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
     * </ul>
     * Here, tiles immediately adjacent means the six tiles directly touching the tile in question.
     * <p>
     * The rules are applied simultaneously to every tile; put another way,
     * it is first determined which tiles need to be flipped, then they are all flipped at the same time.
     *
     * @param dayCount the amount of days to flip
     */
    public void flipTiles(final int dayCount) {
        for (int i = 0; i < dayCount; i++) {
            flipTiles();
        }
    }

    public void flipTiles() {
        final Set<Tile> tilesToFlip = new HashSet<>();

        final Map<Point, Tile> blackTilesByPosition = this.tilesByPosition.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getColor() == TileColor.BLACK)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // For all black tiles find their neighbour tiles.
        // If the neighbour position is not in `tilesByPosition` a new tile will be added with the white side facing up
        final Set<Tile> tilesToCheck = blackTilesByPosition.values()
                .stream()
                .map(Tile::getNeighboursPositions)
                .flatMap(Set::stream)
                .map(p -> this.tilesByPosition.computeIfAbsent(p, Tile::new))
                .collect(Collectors.toSet());
        // Do not forget to check the black tiles which might not be part of the collection of neighbours as well
        tilesToCheck.addAll(blackTilesByPosition.values());

        for (final Tile tile : tilesToCheck) {
            // Find all adjacent tile positions first
            final Set<Point> blackNeighboursPositions = tile.getNeighboursPositions();
            // Then keep intersection between the neighbour positions and all known black tile positions
            blackNeighboursPositions.retainAll(blackTilesByPosition.keySet());

            if (blackTilesByPosition.containsKey(tile.getPosition())) {
                // Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
                if (blackNeighboursPositions.isEmpty() || blackNeighboursPositions.size() > 2) {
                    tilesToFlip.add(tile);
                }
            } else {
                // Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
                if (blackNeighboursPositions.size() == 2) {
                    tilesToFlip.add(tile);
                }
            }
        }
        tilesToFlip.forEach(Tile::flip);
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
