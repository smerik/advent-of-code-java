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
public class Day05Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day05Service.class);

    private final IntcodeComputerService intcodeComputerService;

    @Value("classpath:input/day-05.txt")
    private Resource resource;

    public Day05Service(final IntcodeComputerService intcodeComputerService) {
        this.intcodeComputerService = intcodeComputerService;
    }

    public int[] getSolutionPart1(final int instruction) {
        try {
            Path path = Paths.get(resource.getURI());
            String[] strings = Files.readString(path).replace("\r\n", "").split(",");
            int[] integers = Stream.of(strings).mapToInt(Integer::parseInt).toArray();
            return intcodeComputerService.solveWithInput(integers, instruction);
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    public int getSolutionPart2() {
        return -1;
    }
}
