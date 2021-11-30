package nl.smerik.adventofcode.aoc2019.model.arcade;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
public class Screen {

    private final Map<Point, Tile> tiles;

    public Screen() {
        this.tiles = new HashMap<>();
    }

    public void draw(final Point point, final Tile.Type type) {
        tiles.put(point, new Tile(point, type));
    }

    public void render() {
        final StringBuilder builder = new StringBuilder();
        final int minX = this.tiles.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = this.tiles.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = this.tiles.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = this.tiles.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = maxY ; y >= minY; y--) {
            for (int x = minX ; x <= maxX; x++) {
                builder.append(this.tiles.get(new Point(x, y)).getType().getRenderToken());
            }
            builder.append(System.lineSeparator());
        }
        LOG.debug("\n{}", builder);
    }

    public Tile findAny(Tile.Type type) {
        return tiles.values().stream()
                .filter(tile -> tile.getType().equals(type))
                .findFirst()
                .orElseThrow();
    }

    public long countTilesByType(final Tile.Type type) {
        return this.tiles.values().stream()
                .filter(tile -> tile.getType().equals(type))
                .count();
    }
}
