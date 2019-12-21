package nl.smerik.adventofcode.aoc2019.model.asteroid;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AsteroidBeltMapParserTest {

    @Test
    void parseMap() throws IOException {
        final Path path = Paths.get("src", "test", "resources", "input", "day-10-example-00.txt");

        final AsteroidBelt asteroidBelt = AsteroidBeltMapParser.parse(path);
        final List<Point> asteroids = asteroidBelt.getAsteroids();

        assertEquals(10, asteroids.size());
        assertTrue(asteroids.contains(new Point(1, 0)));
        assertTrue(asteroids.contains(new Point(4, 0)));
        assertTrue(asteroids.contains(new Point(0, 2)));
        assertTrue(asteroids.contains(new Point(1, 2)));
        assertTrue(asteroids.contains(new Point(2, 2)));
        assertTrue(asteroids.contains(new Point(3, 2)));
        assertTrue(asteroids.contains(new Point(4, 2)));
        assertTrue(asteroids.contains(new Point(4, 3)));
        assertTrue(asteroids.contains(new Point(3, 4)));
        assertTrue(asteroids.contains(new Point(4, 4)));
    }
}