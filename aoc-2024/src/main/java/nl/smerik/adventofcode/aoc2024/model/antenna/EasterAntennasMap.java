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

    public int countUniqueAntinodeLocations(final boolean applyResonantHarmonics) {
        final Map<Character, Set<Point>> antinodesByFrequency = determineAntinodesByFrequency(applyResonantHarmonics);
        final Set<Point> result = new HashSet<>();
        antinodesByFrequency.values().forEach(result::addAll);
        return result.size();
    }

    private Map<Character, Set<Point>> determineAntinodesByFrequency(final boolean applyResonantHarmonics) {
        final Map<Character, Set<Point>> result = new HashMap<>();
        for (final Map.Entry<Character, List<Point>> entry : antennaLocationsByFrequency.entrySet()) {
            result.put(entry.getKey(), determineAntinodesForFrequency(entry.getValue(), applyResonantHarmonics));
        }
        return result;
    }

    private Set<Point> determineAntinodesForFrequency(final List<Point> antennasForFrequencyEntry, final boolean applyResonantHarmonics) {
        final Set<Point> result = new HashSet<>();
        final int size = antennasForFrequencyEntry.size();
        for (int i = 0; i < size; i++) {
            final Point location1 = antennasForFrequencyEntry.get(i);
            for (final Point location2 : antennasForFrequencyEntry.subList(i + 1, size)) {
                result.addAll(determineAntinodes(location1, location2, applyResonantHarmonics));
            }
        }
        return result;
    }

    private Set<Point> determineAntinodes(final Point location1, final Point location2, final boolean applyResonantHarmonics) {
        final int distanceX = location2.x - location1.x;
        final int distanceY = location2.y - location1.y;
        final Set<Point> result = determineAntinodesBefore(location1, distanceX, distanceY, applyResonantHarmonics);
        result.addAll(determineAntinodesAfter(location2, distanceX, distanceY, applyResonantHarmonics));
        if (applyResonantHarmonics) {
            // Also add the antenna locations
            result.add(location1);
            result.add(location2);
        }
        return result;
    }

    private Set<Point> determineAntinodesBefore(final Point antennaLocation, final int distanceX, final int distanceY, final boolean applyResonantHarmonics) {
        final Set<Point> result = new HashSet<>();
        Point location = antennaLocation;
        while (isLocationInsideGrid(location = new Point(location.x - distanceX, location.y - distanceY))) {
            result.add(location);
            if (!applyResonantHarmonics) {
                break;
            }
        }
        return result;
    }

    private Set<Point> determineAntinodesAfter(final Point antennaLocation, final int distanceX, final int distanceY, final boolean applyResonantHarmonics) {
        final Set<Point> result = new HashSet<>();
        Point location = antennaLocation;
        while (isLocationInsideGrid(location = new Point(location.x + distanceX, location.y + distanceY))) {
            result.add(location);
            if (!applyResonantHarmonics) {
                break;
            }
        }
        return result;
    }

    private boolean isLocationInsideGrid(final Point location) {
        return location.y >= 0 && location.y < grid.length && location.x >= 0 && location.x < grid[0].length;
    }
}
