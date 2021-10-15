package nl.smerik.adventofcode.aoc2020.model.jigsaw;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Slf4j
@Data
public class Jigsaw {

    private Set<Tile> tiles;

    public Jigsaw(final List<String> lines) {
        this.tiles = parseLines(lines);
        linkAdjacentTiles(tiles);
        arrangeAdjacentTiles(tiles);
        LOG.info("{}{}", System.lineSeparator(), buildArrangedImageWithSpacedBorders());
    }

    private Set<Tile> parseLines(final List<String> lines) {
        final Set<Tile> result = new HashSet<>();
        List<String> tileLines = new ArrayList<>();
        for (final String line : lines) {
            if (line.isBlank()) {
                if (!tileLines.isEmpty()) {
                    result.add(new Tile(tileLines));
                }
                tileLines = new ArrayList<>();
            } else {
                tileLines.add(line);
            }
        }
        return result;
    }

    private void linkAdjacentTiles(final Set<Tile> tiles) {
        for (final Tile tile : tiles) {
            for (final Tile tileToAlignTo : tiles) {
                if (tile.isAdjacent(tileToAlignTo)) {
                    tile.addAdjacent(tileToAlignTo);
                }
            }
        }
    }

    private void arrangeAdjacentTiles(final Set<Tile> tiles) {
        final Tile tile = tiles.stream().findAny().orElseThrow();
        tile.setPosition(new Point());
        tile.arrangeAdjacentTiles(new HashSet<>());
    }

    /**
     * Multiplies the tile corner ID values and returns the result.
     *
     * @return the result of the multiplied corner ID values
     */
    public Long multiplyCornerTileIDs() {
        return tiles.stream()
                    .filter(Tile::isCorner)
                    .flatMapToLong(tile -> LongStream.of(tile.getId()))
                    .reduce(1, (a, b) -> a * b);
    }

    /**
     * Builds an image of all arranged tiles with a whitespace border between.
     *
     * @return the arranged image
     */
    public String buildArrangedImageWithSpacedBorders() {
        final Map<Point, Tile> tilesMappedByIdentity = tiles.stream().filter(Tile::isArranged).collect(Collectors.toMap(Tile::getPosition, Function.identity()));
        final int minX = tilesMappedByIdentity.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = tilesMappedByIdentity.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = tilesMappedByIdentity.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = tilesMappedByIdentity.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int numberOfTileRows = 10;

        final StringBuilder builder = new StringBuilder();
        for (int y = maxY; y >= minY; y--) {
            for (int tileRowIndex = 0; tileRowIndex < numberOfTileRows; tileRowIndex++) {
                for (int x = minX; x <= maxX; x++) {
                    final Tile tile = tilesMappedByIdentity.get(new Point(x, y));
                    builder.append(tile.getContent()[tileRowIndex]).append(' ');
                }
                builder.append(System.lineSeparator());
            }
            builder.append(System.lineSeparator());
        }
        return builder.append(System.lineSeparator()).toString();
    }
}
