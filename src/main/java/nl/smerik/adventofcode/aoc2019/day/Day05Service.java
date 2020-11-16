package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
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

    @Value("classpath:input/day-05.txt")
    private Resource resource;

    public int getSolutionPart1(final int instruction) {
        try {
            final Path path = Paths.get(resource.getURI());
            final String[] strings = Files.readString(path).replace("\r\n", "").split(",");
            final int[] integers = Stream.of(strings).mapToInt(Integer::parseInt).toArray();
            final IntcodeComputer computer = new IntcodeComputer(integers);
            computer.run(instruction);
            return computer.getOutput();
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return -1;
        }
    }

    public int getSolutionPart2() {
        return -1;
    }
}
