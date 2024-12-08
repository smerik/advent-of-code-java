package nl.smerik.adventofcode.aoc2024.model.antenna;

import java.awt.*;
import java.util.List;
import java.util.*;

public class EasterAntennasMap {

    private final Map<Character, List<Point>> antennaLocationsByFrequency;
    private final char[][] grid;

    public EasterAntennasMap(final List<String> lines) {
        this.antennaLocationsByFrequency = new HashMap<>();
        this.grid = parseLines(lines);
    }

    private char[][] parseLines(final List<String> lines) {
        final char[][] result = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            result[y] = lines.get(y).toCharArray();
            for (int x = 0; x < result[y].length; x++) {
                if (result[y][x] != '.') {
                    antennaLocationsByFrequency.computeIfAbsent(result[y][x], k -> new ArrayList<>()).add(new Point(x, y));
                }
            }
        }
        return result;
    }

    public int countUniqueAntinodeLocations() {
        final Map<Character, Set<Point>> antinodesByFrequency = determineAntinodesByFrequency();
        final Set<Point> result = new HashSet<>();
        antinodesByFrequency.values().forEach(result::addAll);
        return result.size();
    }

    private Map<Character, Set<Point>> determineAntinodesByFrequency() {
        final Map<Character, Set<Point>> result = new HashMap<>();
        for (final Map.Entry<Character, List<Point>> entry : antennaLocationsByFrequency.entrySet()) {
            result.put(entry.getKey(), new HashSet<>(determineAntinodesForFrequency(entry.getValue())));
        }
        return result;
    }

    private List<Point> determineAntinodesForFrequency(final List<Point> antennasForFrequencyEntry) {
        final List<Point> result = new ArrayList<>();
        final int size = antennasForFrequencyEntry.size();
        for (int i = 0; i < size; i++) {
            final Point location1 = antennasForFrequencyEntry.get(i);
            for (final Point location2 : antennasForFrequencyEntry.subList(i + 1, size)) {
                result.addAll(determineAntinodes(location1, location2));
            }
        }
        return result;
    }

    private List<Point> determineAntinodes(final Point location1, final Point location2) {
        final int distanceX = location2.x - location1.x;
        final int distanceY = location2.y - location1.y;
        final List<Point> result = new ArrayList<>();
        final Point antinode1 = new Point(location1.x - distanceX, location1.y - distanceY);
        if (isLocationInsideGrid(antinode1)) {
            result.add(antinode1);
        }

        final Point antinode2 = new Point(location2.x + distanceX, location2.y + distanceY);
        if (isLocationInsideGrid(antinode2)) {
            result.add(antinode2);
        }
        return result;
    }

    private boolean isLocationInsideGrid(final Point location) {
        return location.y >= 0 && location.y < grid.length && location.x >= 0 && location.x < grid[0].length;
    }
}
