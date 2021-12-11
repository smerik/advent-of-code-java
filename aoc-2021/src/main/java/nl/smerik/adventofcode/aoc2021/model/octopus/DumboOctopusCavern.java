package nl.smerik.adventofcode.aoc2021.model.octopus;

import lombok.Getter;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class DumboOctopusCavern {

    private final Map<Point, DumboOctopus> octopusesByLocation;

    public DumboOctopusCavern(final List<String> measurements) {
        this.octopusesByLocation = parseMeasurements(measurements);
    }

    private Map<Point, DumboOctopus> parseMeasurements(final List<String> measurements) {
        if (measurements.isEmpty()) {
            return new HashMap<>();
        }

        final Map<Point, DumboOctopus> result = new HashMap<>();
        for (int y = 0; y < measurements.size(); y++) {
            for (int x = 0; x < measurements.get(0).length(); x++) {
                final Point location = new Point(x, y);
                final int energyLevel = Character.getNumericValue(measurements.get(y).charAt(x));
                result.put(location, new DumboOctopus(location, energyLevel));
            }
        }
        return result;
    }

    /**
     * Simulates the energy level increments for given amounts of steps
     * and returns the total flashes that occured.
     *
     * @param steps the amount of steps to simulate
     * @return the total flash count
     */
    public int simulateSteps(final int steps) {
        int totalflashCount = 0;
        for (int step = 0; step < steps; step++) {
            totalflashCount += simulate(step);
        }
        return totalflashCount;
    }

    private int simulate(final int step) {
        int totalFlashCount = 0;
        List<Point> octopusesLocationsToIncrease = octopusesByLocation.keySet().stream().toList();
        while (!octopusesLocationsToIncrease.isEmpty()) {
            final Set<Point> flashedOctopusesLocations = octopusesLocationsToIncrease.stream()
                                                                                     .map(octopusesByLocation::get)
                                                                                     .filter(octopus -> octopus.increaseEnergyLevel(step))
                                                                                     .map(DumboOctopus::getLocation)
                                                                                     .collect(Collectors.toSet());
            totalFlashCount += flashedOctopusesLocations.size();

            octopusesLocationsToIncrease = flashedOctopusesLocations.stream()
                                                                    .map(this::getAdjacentLocations)
                                                                    .flatMap(Set::stream)
                                                                    .filter(octopusesByLocation::containsKey)
                                                                    .toList();
        }
        return totalFlashCount;
    }

    private Set<Point> getAdjacentLocations(final Point point) {
        return Stream.of(
                new Point(point.x - 1, point.y - 1),
                new Point(point.x, point.y - 1),
                new Point(point.x + 1, point.y - 1),

                new Point(point.x - 1, point.y),
                new Point(point.x + 1, point.y),

                new Point(point.x - 1, point.y + 1),
                new Point(point.x, point.y + 1),
                new Point(point.x + 1, point.y + 1)
        ).collect(Collectors.toSet());
    }
}
