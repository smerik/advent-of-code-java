package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.FuelManagementSystem;
import nl.smerik.adventofcode.aoc2019.model.Wire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Day03Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day03Service.class);

    @Value("classpath:input/day-03.txt")
    private Resource resource;

    public Day03Service() {
    }

    public int getSolutionPart1() {
        try {
            final FuelManagementSystem fuelManagementSystem = getFuelManagementSystem();
            return fuelManagementSystem.getManhattanDistanceFromCentralPortToClosestIntersection();
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return -1;
        }
    }

    public int getSolutionPart2() {
        try {
            final FuelManagementSystem fuelManagementSystem = getFuelManagementSystem();
            return fuelManagementSystem.getFewestCombinedStepsToReachAnIntersection();
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return -1;
        }
    }

    private FuelManagementSystem getFuelManagementSystem() throws IOException {
        final FuelManagementSystem fuelManagementSystem = new FuelManagementSystem();

        final Path path = Paths.get(resource.getURI());
        Files.lines(path).forEach(line -> fuelManagementSystem.addWire(new Wire(line)));
        return fuelManagementSystem;
    }
}
