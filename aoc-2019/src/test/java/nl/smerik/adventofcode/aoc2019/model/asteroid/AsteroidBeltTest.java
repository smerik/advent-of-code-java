package nl.smerik.adventofcode.aoc2019.model.asteroid;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@Slf4j
class AsteroidBeltTest {

    private static AsteroidBelt asteroidBeltExample00;
    private static AsteroidBelt asteroidBeltExample01;
    private static AsteroidBelt asteroidBeltExample02;
    private static AsteroidBelt asteroidBeltExample03;
    private static AsteroidBelt asteroidBeltExample04;
    private static AsteroidBelt asteroidBeltDay10;

    @BeforeAll
    static void initAll() throws IOException {
        final Path pathExample00 = Paths.get("src", "test", "resources", "input", "day-10-example-00.txt");
        asteroidBeltExample00 = AsteroidBeltMapParser.parse(pathExample00);

        final Path pathExample01 = Paths.get("src", "test", "resources", "input", "day-10-example-01.txt");
        asteroidBeltExample01 = AsteroidBeltMapParser.parse(pathExample01);

        final Path pathExample02 = Paths.get("src", "test", "resources", "input", "day-10-example-02.txt");
        asteroidBeltExample02 = AsteroidBeltMapParser.parse(pathExample02);

        final Path pathExample03 = Paths.get("src", "test", "resources", "input", "day-10-example-03.txt");
        asteroidBeltExample03 = AsteroidBeltMapParser.parse(pathExample03);

        final Path pathExample04 = Paths.get("src", "test", "resources", "input", "day-10-example-04.txt");
        asteroidBeltExample04 = AsteroidBeltMapParser.parse(pathExample04);

        final Path pathDay10 = Paths.get("src", "test", "resources", "input", "day-10.txt");
        asteroidBeltDay10 = AsteroidBeltMapParser.parse(pathDay10);
    }

    private static Stream<Arguments> provideSourceForGetNumberOfDetectedAsteroids() {
        return Stream.of(
                // First example with number of detected asteroids by each asteroid
                Arguments.of(asteroidBeltExample00, new Point(1, 0), 7),
                Arguments.of(asteroidBeltExample00, new Point(4, 0), 7),

                Arguments.of(asteroidBeltExample00, new Point(0, 2), 6),
                Arguments.of(asteroidBeltExample00, new Point(1, 2), 7),
                Arguments.of(asteroidBeltExample00, new Point(2, 2), 7),
                Arguments.of(asteroidBeltExample00, new Point(3, 2), 7),
                Arguments.of(asteroidBeltExample00, new Point(4, 2), 5),

                Arguments.of(asteroidBeltExample00, new Point(4, 3), 7),

                Arguments.of(asteroidBeltExample00, new Point(3, 4), 8),
                Arguments.of(asteroidBeltExample00, new Point(4, 4), 7),

                // Examples with best place location
                Arguments.of(asteroidBeltExample01, new Point(5, 8), 33),
                Arguments.of(asteroidBeltExample02, new Point(1, 2), 35),
                Arguments.of(asteroidBeltExample03, new Point(6, 3), 41),
                Arguments.of(asteroidBeltExample04, new Point(11, 13), 210)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForGetNumberOfDetectedAsteroids")
    void testGetNumberOfDetectedAsteroids(final AsteroidBelt asteroidBelt,
                                          final Point point,
                                          final int expectedNumberOfAsteroids) {
        assertEquals(expectedNumberOfAsteroids, asteroidBelt.getNumberOfDetectedAsteroids(point));
    }

    @Test
    void testFindSolutionDay10Part01() {
        LOG.info("Finding solution day 10 - part 1...");
        final Point result = asteroidBeltDay10.findBestLocationForNewMonitoringStation();
        LOG.info("Found solution!");
        LOG.info("Best location : {}", result);
        LOG.info("Number        : {}", asteroidBeltDay10.getNumberOfDetectedAsteroids(result));
        //Best location : java.awt.Point[x=26,y=36]
        //Number        : 347
    }

    private static Stream<Arguments> provideSourceForIsBlockedBy() {
        // Data from the example containing capital & lowercase letters
        return Stream.of(
                // A
                Arguments.of(new Point(0, 0), new Point(3, 1),
                        Arrays.asList(new Point(6, 2), new Point(9, 3))),
                // B
                Arguments.of(new Point(0, 0), new Point(3, 2),
                        Arrays.asList(new Point(6, 4), new Point(9, 6))),
                // C
                Arguments.of(new Point(0, 0), new Point(3, 3),
                        Arrays.asList(new Point(4, 4), new Point(5, 5), new Point(6, 6),
                                new Point(7, 7), new Point(8, 8), new Point(9, 9))),
                // D
                Arguments.of(new Point(0, 0), new Point(2, 3),
                        Arrays.asList(new Point(4, 6), new Point(6, 9))),
                // E
                Arguments.of(new Point(0, 0), new Point(1, 3),
                        Arrays.asList(new Point(2, 6), new Point(3, 9))),
                // F
                Arguments.of(new Point(0, 0), new Point(2, 4),
                        Arrays.asList(new Point(3, 6), new Point(4, 8))),
                // G
                Arguments.of(new Point(0, 0), new Point(4, 3),
                        Collections.singletonList(new Point(8, 6)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForIsBlockedBy")
    void isBlockedBy(final Point asteroid1,
                     final Point asteroid2,
                     final List<Point> blockedLocations) {

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                final Point location = new Point(x, y);
                if (location.equals(asteroid1) || location.equals(asteroid2)) {
                    continue;
                }
                assertEquals(blockedLocations.contains(location), AsteroidBelt.isBlockedBy(location, asteroid1, asteroid2));
            }
        }
    }

    private static Stream<Arguments> provideSourceForFindBestLocationForNewMonitoringStation() {
        return Stream.of(
                // First example with number of detected asteroids by each asteroid
                Arguments.of(asteroidBeltExample00, new Point(3, 4)),
                Arguments.of(asteroidBeltExample01, new Point(5, 8)),
                Arguments.of(asteroidBeltExample02, new Point(1, 2)),
                Arguments.of(asteroidBeltExample03, new Point(6, 3)),
                Arguments.of(asteroidBeltExample04, new Point(11, 13))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForFindBestLocationForNewMonitoringStation")
    void findBestLocationForNewMonitoringStation(final AsteroidBelt asteroidBelt,
                                                 final Point bestLocation) {
        assertEquals(bestLocation, asteroidBelt.findBestLocationForNewMonitoringStation());
    }
}