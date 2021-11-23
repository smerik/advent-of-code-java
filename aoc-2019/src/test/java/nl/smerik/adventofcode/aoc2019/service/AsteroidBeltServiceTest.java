package nl.smerik.adventofcode.aoc2019.service;

import nl.smerik.adventofcode.aoc2019.model.asteroid.AsteroidBelt;
import nl.smerik.adventofcode.aoc2019.model.asteroid.AsteroidBeltMapParser;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsteroidBeltServiceTest {

    private static AsteroidBelt asteroidBeltExample00;
    private static AsteroidBelt asteroidBeltExample01;
    private static AsteroidBelt asteroidBeltExample02;
    private static AsteroidBelt asteroidBeltExample03;
    private static AsteroidBelt asteroidBeltExample04;

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
    }
}