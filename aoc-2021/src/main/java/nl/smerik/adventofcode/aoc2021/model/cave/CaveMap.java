package nl.smerik.adventofcode.aoc2021.model.cave;

import lombok.Getter;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CaveMap {

    private final Map<Point, Integer> heightByLocations;

    public CaveMap(final List<String> heightmap) {
        heightByLocations = parseHeightmap(heightmap);
    }

    public static int calculateRiskLevel(final int height) {
        return 1 + height;
    }

    private Map<Point, Integer> parseHeightmap(final List<String> heightmap) {
        if (heightmap.isEmpty()) {
            return new HashMap<>();
        }

        final Map<Point, Integer> result = new HashMap<>();
        for (int y = 0; y < heightmap.size(); y++) {
            for (int x = 0; x < heightmap.get(0).length(); x++) {
                final int height = Character.getNumericValue(heightmap.get(y).charAt(x));
                result.put(new Point(x, y), height);
            }
        }
        return result;
    }

    public Set<Point> findLowPoints() {
        return heightByLocations.keySet().stream().filter(this::isLowPoint).collect(Collectors.toSet());
    }

    private boolean isLowPoint(final Point point) {
        final int lowestAdjacent = getAdjacentLocations(point).stream()
                .map(heightByLocations::get)
                .filter(Objects::nonNull)
                .mapToInt(v -> v)
                .min()
                .orElseThrow();
        return heightByLocations.get(point) < lowestAdjacent;
    }

    private Set<Point> getAdjacentLocations(final Point point) {
        return Set.of(
                new Point(point.x, point.y - 1),
                new Point(point.x, point.y + 1),
                new Point(point.x - 1, point.y),
                new Point(point.x + 1, point.y)
        );
    }

    public int sumRiskLevels() {
        return findLowPoints().stream().mapToInt(heightByLocations::get).map(CaveMap::calculateRiskLevel).sum();
    }
}
