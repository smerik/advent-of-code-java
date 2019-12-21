package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.service.IntcodeComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class Day02Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day02Service.class);

    private final IntcodeComputerService intcodeComputerService;

    @Value("classpath:input/day-02.txt")
    private Resource resource;

    public Day02Service(final IntcodeComputerService intcodeComputerService) {
        this.intcodeComputerService = intcodeComputerService;
    }

    public int[] getSolutionPart1() {
        try {
            Path path = Paths.get(resource.getURI());
            String[] strings = Files.readString(path).replaceAll("\n", "").split(",");
            int[] integers = Stream.of(strings).mapToInt(Integer::parseInt).toArray();
            return intcodeComputerService.solvePart1(integers);
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    public String getSolutionPart2(final int requiredOutput) {
        final int[] memory = getMemory();
        return intcodeComputerService.solvePart2(memory, requiredOutput);
    }

    private int[] getMemory() {
        try {
            final Path path = Paths.get(resource.getURI());
            final String[] strings = Files.readString(path).replaceAll("\n", "").split(",");
            return Stream.of(strings).mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
