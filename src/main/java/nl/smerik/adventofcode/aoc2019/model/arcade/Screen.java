package nl.smerik.adventofcode.aoc2019.model.arcade;

import lombok.Getter;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Screen {

    private final Map<Point, Tile> tiles;

    public Screen() {
        this.tiles = new HashMap<>();
    }

    public void draw(final Point point, final Tile.Type type) {
        tiles.put(point, new Tile(point, type));
    }

    public long countTilesByType(final Tile.Type type) {
        return this.tiles.values().stream()
                .filter(tile -> tile.getType().equals(type))
                .count();
    }
}
