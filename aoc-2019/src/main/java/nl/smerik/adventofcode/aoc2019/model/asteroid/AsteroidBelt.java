package nl.smerik.adventofcode.aoc2019.model.asteroid;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AsteroidBelt {

    private final List<Point> asteroids;

    public AsteroidBelt(final List<Point> asteroids) {
        this.asteroids = asteroids;
    }

    /**
     * Checks if given location is not in the line of sight of asteroid1 because it is blocked by asteroid2.
     *
     * <p>
     * Source of implementation:
     * <a href="https://stackoverflow.com/questions/328107/how-can-you-determine-a-point-is-between-two-other-points-on-a-line-segment">
     * How can you determine a point is between two other points on a line segment?
     * </a>
     *
     * @param location  the location to check for
     * @param asteroid1 the asteroid location that detects other asteroids
     * @param asteroid2 the detected asteroid location
     * @return true if location is blocked; false otherwise
     */
    public static boolean isBlockedBy(final Point location,
                                      final Point asteroid1,
                                      final Point asteroid2) {

        final int crossProduct = (asteroid2.y - location.y) * (asteroid1.x - location.x) - (asteroid2.x - location.x) * (asteroid1.y - location.y);

        // Compare versus epsilon for floating point values, or != 0 if using integers
        if (Math.abs(crossProduct) > Math.ulp(crossProduct)) {
            return false;
        }

        final int dotProduct = (asteroid2.x - location.x) * (asteroid1.x - location.x) + (asteroid2.y - location.y) * (asteroid1.y - location.y);
        if (dotProduct < 0) {
            return false;
        }

        final int squaredLengthBA = (asteroid1.x - location.x) * (asteroid1.x - location.x) + (asteroid1.y - location.y) * (asteroid1.y - location.y);
        if (dotProduct > squaredLengthBA) {
            return false;
        }
        return true;
    }

    public List<Point> getAsteroids() {
        return asteroids;
    }

    public Point findBestLocationForNewMonitoringStation() {
        LOG.info("findBestLocationForNewMonitoringStation()...");
        // TODO: optimize implementation
        return asteroids
                .stream()
                .max(Comparator.comparingInt(this::getNumberOfDetectedAsteroids))
//                .orElseThrow() // TODO
                .get();
    }

    public int getNumberOfDetectedAsteroids(final Point asteroid) {
        final List<Point> visibleAsteroids = asteroids.stream()
                .filter(point -> !point.equals(asteroid))
                .filter(point -> !determineBlockedLocations(asteroid).contains(point))
                .collect(Collectors.toList());
//        LOGGER.debug("Visible asteroids:{}", visibleAsteroids);
        return visibleAsteroids.size();
    }

    private List<Point> determineBlockedLocations(final Point asteroid) {
        final List<Point> result = new ArrayList<>();
        asteroids.forEach(point -> result.addAll(determineBlockedAsteroidsByAsteroid(asteroid, point)));
//        LOGGER.debug("Blocked locations:{}", result);
        return result;
    }

    private List<Point> determineBlockedAsteroidsByAsteroid(final Point eligibleAsteroid, final Point asteroid) {
        if (eligibleAsteroid.equals(asteroid)) {
            return Collections.emptyList();
        }

        final List<Point> blockedAsteroids = new ArrayList<>();
        for (final Point asteroidToCheckIfInLineOfSight : asteroids) {
            if (asteroidToCheckIfInLineOfSight.equals(eligibleAsteroid)
                    || asteroidToCheckIfInLineOfSight.equals(asteroid)) {
                continue;
            }
            if (isBlockedBy(asteroidToCheckIfInLineOfSight, eligibleAsteroid, asteroid)) {
                blockedAsteroids.add(asteroidToCheckIfInLineOfSight);
            }
        }
        return blockedAsteroids;
    }
}
