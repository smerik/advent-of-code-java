package nl.smerik.adventofcode.aoc2023.model.cosmic;

import lombok.Setter;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Cosmic {

    public static final char GALAXY_TOKEN = '#';

    private final char[][] universe;
    private final List<Integer> emptyRows;
    private final List<Integer> emptyColumns;

    @Setter
    private int expansionFactor;

    public Cosmic(final List<String> lines) {
        universe = parseLines(lines);
        emptyRows = findEmptyRows();
        emptyColumns = findEmptyColums();
        expansionFactor = 1;
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

    public long sumShortestPathBetweenAllGalaxyPairs() {
        return findGalaxyPairs().stream().map(this::findShortestPathLength).mapToLong(Long::valueOf).sum();
    }

    public long findShortestPathLength(final GalaxyPair pair) {
        final int diffX = Math.abs(pair.galaxy2().x - pair.galaxy1().x);
        final int diffY = Math.abs(pair.galaxy2().y - pair.galaxy1().y);
        final long emptyRowsCount = countEmptyRowsBetween(pair);
        final long emptyColumnsCount = countEmptyColumnsBetween(pair);
        return diffX + diffY + (emptyRowsCount + emptyColumnsCount) * (expansionFactor - 1);
    }

    private int countEmptyColumnsBetween(final GalaxyPair pair) {
        return countEmptySpaceBetween(emptyColumns, pair.galaxy1().x, pair.galaxy2().x);
    }

    private int countEmptyRowsBetween(final GalaxyPair pair) {
        return countEmptySpaceBetween(emptyRows, pair.galaxy1().y, pair.galaxy2().y);
    }

    private int countEmptySpaceBetween(final List<Integer> emptyDigits, final int digit1, final int digit2) {
        final int min = Math.min(digit1, digit2);
        final int max = Math.max(digit1, digit2);
        int result = 0;
        for (final Integer digit : emptyDigits) {
            if (digit > min && digit < max) {
                result++;
            }
        }
        return result;
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
