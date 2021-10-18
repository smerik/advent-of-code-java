package nl.smerik.adventofcode.aoc2020.model.jigsaw;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        LOG.info("{}{}", System.lineSeparator(), buildArrangedImage(true));
        LOG.info("{}", System.lineSeparator());
        LOG.info("{}{}", System.lineSeparator(), buildArrangedImage(false));
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
    public String buildArrangedImage(final boolean withSpacedBorders) {
        final Map<Point, Tile> tilesMappedByIdentity = tiles.stream().filter(Tile::isArranged).collect(Collectors.toMap(Tile::getPosition, Function.identity()));
        final int minX = tilesMappedByIdentity.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = tilesMappedByIdentity.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = tilesMappedByIdentity.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = tilesMappedByIdentity.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int numberOfTileRows = withSpacedBorders ? 10 : 9;
        final int tileRowFirstIndex = withSpacedBorders ? 0 : 1;
        final int tileColumnFirstIndex = withSpacedBorders ? 0 : 1;
        final int tileColumnLastIndex = withSpacedBorders ? 10 : 9;

        final StringBuilder builder = new StringBuilder();
        for (int y = maxY; y >= minY; y--) {
            for (int tileRowIndex = tileRowFirstIndex; tileRowIndex < numberOfTileRows; tileRowIndex++) {
                for (int x = minX; x <= maxX; x++) {
                    final Tile tile = tilesMappedByIdentity.get(new Point(x, y));
                    builder.append(Arrays.copyOfRange(tile.getContent()[tileRowIndex], tileColumnFirstIndex, tileColumnLastIndex));
                    if (withSpacedBorders  && x < maxX) {
                        builder.append(' ');
                    }
                }
                builder.append(System.lineSeparator());
            }
            if (withSpacedBorders) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.append(System.lineSeparator()).toString();
    }

    public int calculateWaterRoughness() {
        final Set<Point> seaMonstersCoordinates = findSeaMonstersCoordinatesInSea().stream()
                                                                              .flatMap(Collection::stream)
                                                                              .collect(Collectors.toSet());
        final Set<Point> hashLocationsInSea = findHashCoordinatesInSea();
        hashLocationsInSea.removeAll(seaMonstersCoordinates);
        return hashLocationsInSea.size();
    }

    public Set<Set<Point>> findSeaMonstersCoordinatesInSea() {
        final Set<Set<Point>> result = new HashSet<>(new HashSet<>());
        final SeaMonster monster = new SeaMonster();
        for (int i = 0; i < 8; i++) {
            result.addAll(findSeaMonstersCoordinatesInSea(monster));
            // Try all rotations first before flipping the monster horizontally
            if (i == 3) {
                monster.flipHorizontally();
            } else {
                monster.rotateCW();
            }
        }
        return result;
    }

    private Set<Set<Point>> findSeaMonstersCoordinatesInSea(final SeaMonster monster) {
        final Set<Set<Point>> result = new HashSet<>(new HashSet<>());

        final Set<Point> hashCoordinates = findHashCoordinatesInSea();
        final int minX = hashCoordinates.stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = hashCoordinates.stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = hashCoordinates.stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = hashCoordinates.stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                final Set<Point> monsterCoordinates = monster.translate(x, y);
                if (hashCoordinates.containsAll(monsterCoordinates)) {
                    result.add(monsterCoordinates);
                }
            }
        }
        return result;
    }

    private Set<Point> findHashCoordinatesInSea() {
        final Set<Point> result = new HashSet<>();
        final String[] lines = buildArrangedImage(false).split(System.lineSeparator());
        for (int y = 0; y < lines.length; y++) {
            int x = 0;
            while ((x = lines[y].indexOf('#', x)) != -1) {
                result.add(new Point(x, y));
                x++;
            }
        }
        return result;
    }
}
