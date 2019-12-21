package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.asteroid.AsteroidBelt;
import nl.smerik.adventofcode.aoc2019.model.asteroid.AsteroidBeltMapParser;
import nl.smerik.adventofcode.aoc2019.service.AsteroidBeltService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Day10Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day10Service.class);

    @Value("classpath:input/day-10.txt")
    private Resource resource;

    public Day10Service() {
    }

    public int getSolutionPart1() throws IOException {
        final AsteroidBelt asteroidBelt = AsteroidBeltMapParser.parse(Paths.get(resource.getURI()));
        final Point bestLocation = asteroidBelt.findBestLocationForNewMonitoringStation();
        return asteroidBelt.getNumberOfDetectedAsteroids(bestLocation);
    }

    public int getSolutionPart2() throws IOException {
        return -1;
    }
}
