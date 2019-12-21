package nl.smerik.adventofcode.aoc2019.service;

import nl.smerik.adventofcode.aoc2019.model.RocketModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FuelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuelService.class);

    public Long calculateTotalRequiredFuel(final Path path) {
        try {
            return Files.lines(path)
                    .map(Long::valueOf)
                    .map(RocketModule::new)
                    .map(RocketModule::getRequiredFuel)
                    .mapToLong(Long::longValue)
                    .sum();
        } catch (IOException e) {
            LOGGER.info("Houston: {}", e.getMessage(), e);
        }
        return 0L;
    }
}
