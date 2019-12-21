package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.service.FuelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Day01Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day01Service.class);

    private final FuelService fuelService;

    @Value("classpath:input/day-01.txt")
    private Resource resource;

    public Day01Service(final FuelService fuelService) {
        this.fuelService = fuelService;
    }

    public Long getSolutionPart1() {
        try {
            final Path path = Paths.get(resource.getURI());
            return fuelService.calculateTotalRequiredFuel(path);
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    public Long getSolutionPart2() {
        try {
            final Path path = Paths.get(resource.getURI());
            return fuelService.calculateTotalRequiredFuel(path);
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
