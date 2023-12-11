package nl.smerik.adventofcode.aoc2023.model.cosmic;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Cosmic {

    public static final char GALAXY_TOKEN = '#';
    public static final char EMPTY_SPACE_TOKEN = '.';

    private char[][] universe;

    public Cosmic(final List<String> lines) {
        universe = parseLines(lines);
    }

    private char[][] parseLines(final List<String> lines) {
        int lengthX = lines.get(0).length();
        int lengthY = lines.size();
        final char[][] result = new char[lengthY][lengthX];

        for (int y = 0; y < lengthY; y++) {
            result[y] = lines.get(y).toCharArray();
        }
        return result;
    }

    public void expandUniverse() {
        final List<Integer> emptyColumns = findEmptyColums();
        final List<Integer> emptyRows = findEmptyRows();
        expandUniverseColumns(emptyColumns);
        expandUniverseRows(emptyRows);
    }

    private void expandUniverseColumns(final List<Integer> columns) {
        if (columns.isEmpty()) {
            return;
        }
        final int newWidth = universe[0].length + columns.size();
        final char[][] result = new char[universe.length][newWidth];

        int srcPos = 0;
        int destPos = 0;
        for (final int expandColumn : columns) {
            int length = expandColumn - srcPos;
            for (int y = 0; y < universe.length; y++) {
                System.arraycopy(universe[y], srcPos, result[y], destPos, length);
                result[y][destPos + length] = EMPTY_SPACE_TOKEN;
            }
            srcPos = expandColumn;
            destPos = destPos + length + 1;
        }
        // Fill the remaining columns
        int length = result[0].length - destPos;
        for (int y = 0; y < universe.length; y++) {
            System.arraycopy(universe[y], srcPos, result[y], destPos, length);
        }
        this.universe = result;
    }

    private void expandUniverseRows(final List<Integer> rows) {
        if (rows.isEmpty()) {
            return;
        }
        final int newHeight = universe.length + rows.size();
        final char[][] result = new char[newHeight][universe[0].length];

        int insertCount = 0;
        for (int y = 0; y < universe.length; y++) {
            if (rows.contains(y)) {
                Arrays.fill(result[y + insertCount], EMPTY_SPACE_TOKEN);
                insertCount++;
            }
            System.arraycopy(universe[y], 0, result[y + insertCount], 0, universe[y].length);
        }
        this.universe = result;
    }

    public List<Integer> findEmptyColums() {
        final List<Integer> result = new ArrayList<>();
        for (int x = 0; x < universe[0].length; x++) {
            for (int y = 0; y < universe.length; y++) {
                if (universe[y][x] == GALAXY_TOKEN) {
                    break;
                }
                if (y == universe.length - 1) {
                    result.add(x);
                }
            }
        }
        return result;
    }

    public List<Integer> findEmptyRows() {
        final List<Integer> result = new ArrayList<>();
        for (int y = 0; y < universe.length; y++) {
            for (int x = 0; x < universe[0].length; x++) {
                if (universe[y][x] == GALAXY_TOKEN) {
                    break;
                }
                if (x == universe[x].length - 1) {
                    result.add(y);
                }
            }
        }
        return result;
    }

    public int sumShortestPathBetweenAllGalaxyPairs() {
        return findGalaxyPairs().stream().map(GalaxyPair::findShortestPathLength).mapToInt(Integer::intValue).sum();
    }

    public Set<GalaxyPair> findGalaxyPairs() {
        final Set<Point> galaxies = findGalaxies();
        return galaxies.stream()
                .flatMap(galaxy1 -> galaxies.stream()
                        .filter(galaxy2 -> !galaxy1.equals(galaxy2))
                        .map(galaxy2 -> new GalaxyPair(galaxy1, galaxy2)))
                .collect(Collectors.toSet());
    }

    public Set<Point> findGalaxies() {
        final Set<Point> result = new HashSet<>();
        for (int y = 0; y < universe.length; y++) {
            for (int x = 0; x < universe[0].length; x++) {
                if (universe[y][x] == GALAXY_TOKEN) {
                    result.add(new Point(x, y));
                }
            }
        }
        return result;
    }

    public String render() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final char[] rows : universe) {
            stringBuilder.append(rows).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
