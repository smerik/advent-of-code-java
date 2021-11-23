package nl.smerik.adventofcode.aoc2019.day;

import lombok.SneakyThrows;
import nl.smerik.adventofcode.aoc2019.service.FuelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class Day01Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day01Service.class);

    private final FuelService fuelService;

    @Value("classpath:input/day-01.txt")
    private Resource resource;

    public Day01Service(final FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Integer> masses = stringStream.mapToInt(Integer::valueOf).boxed().toList();
            return fuelService.calculateTotalRequiredFuel(masses, false);
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Integer getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Integer> masses = stringStream.mapToInt(Integer::valueOf).boxed().toList();
            return fuelService.calculateTotalRequiredFuel(masses, true);
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
