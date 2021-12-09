package nl.smerik.adventofcode.aoc2021.model.cave;

import lombok.Getter;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                                                              .mapToInt(heightByLocations::get)
                                                              .min()
                                                              .orElseThrow();
        return heightByLocations.get(point) < lowestAdjacent;
    }

    private Set<Point> getAdjacentLocations(final Point point) {
        return Stream.of(new Point(point.x, point.y - 1),
                        new Point(point.x, point.y + 1),
                        new Point(point.x - 1, point.y),
                        new Point(point.x + 1, point.y))
                     .filter(heightByLocations::containsKey)
                     .collect(Collectors.toSet());
    }

    public int sumRiskLevels() {
        return findLowPoints().stream().mapToInt(heightByLocations::get).map(CaveMap::calculateRiskLevel).sum();
    }

    public Integer multiplyThreeLargestBasins() {
        return determineThreeLargestBasins().stream().reduce(1, (a, b) -> a * b);
    }

    public List<Integer> determineThreeLargestBasins() {
        return findLowPoints().stream().map(this::calculateBasinSize).sorted(Comparator.reverseOrder()).limit(3).toList();
    }

    public int calculateBasinSize(final Point lowPoint) {
        return findBasinLocations(lowPoint).size();
    }

    // TODO: refactor because this code is really ugly
    public Set<Point> findBasinLocations(final Point lowPoint) {
        final Set<Point> result = new HashSet<>();
        result.add(lowPoint);

        final Set<Point> checkedLocations = new HashSet<>();
        checkedLocations.add(lowPoint);

        final Set<Point> locationsToCheck = new HashSet<>(getAdjacentLocations(lowPoint));
        while (!locationsToCheck.isEmpty()) {
            final Set<Point> locationsFlowing = new HashSet<>();
            for (final Point location : locationsToCheck) {
                if (isLocationFlowingToLowPoint(location, lowPoint)) {
                    locationsFlowing.add(location);
                }
                checkedLocations.add(location);
            }
            result.addAll(locationsFlowing);
            locationsToCheck.clear();
            for (final Point location : locationsFlowing) {
                locationsToCheck.addAll(getAdjacentLocations(location));
                locationsToCheck.removeAll(checkedLocations);
            }
        }
        return result;
    }

    private boolean isLocationFlowingToLowPoint(final Point location, final Point lowPoint) {
        final Integer locationHeight = heightByLocations.get(location);
        if (locationHeight == null) {
            return false;
        }

        if (locationHeight == 9) {
            return false;
        }
        return locationHeight > heightByLocations.get(lowPoint);
    }
}
