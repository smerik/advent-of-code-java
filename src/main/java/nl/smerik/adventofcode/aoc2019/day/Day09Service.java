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
public class Day09Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day09Service.class);

    @Value("classpath:input/day-09.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        final long[] program = getProgram();
        final IntcodeComputer computer = new IntcodeComputer(program);
        return computer.run(1L).get(0);
    }

    public Long getSolutionPart2() {
        final long[] program = getProgram();
        final IntcodeComputer computer = new IntcodeComputer(program);
        return computer.run(2L).get(0);
    }

    private long[] getProgram() {
        try {
            final Path path = Paths.get(resource.getURI());
            final String[] strings = Files.readString(path).replace("\n", "").split(",");
            return Stream.of(strings).mapToLong(Long::parseLong).toArray();
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
        }
        return new long[0];
    }
}
