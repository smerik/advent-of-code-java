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
public class Day02Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day02Service.class);

    @Value("classpath:input/day-02.txt")
    private Resource resource;

    public long getSolutionPart1() {
        LOGGER.info("getSolutionPart1");
        final long[] program = getProgram();
        program[1] = 12;
        program[2] = 2;
        final IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        return computer.getMemory()[0];
    }

    public String getSolutionPart2(final int requiredOutput) {
        final long[] program = getProgram();
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                program[1] = noun;
                program[2] = verb;

                final IntcodeComputer computer = new IntcodeComputer(program);
                computer.run();
                if (computer.getMemory()[0] == requiredOutput) {
                    return String.format("%02d%02d", noun, verb);
                }
            }
        }
        return "???";
    }

    private long[] getProgram() {
        try {
            final Path path = Paths.get(resource.getURI());
            final String[] strings = Files.readString(path).replace("\r\n", "").split(",");
            return Stream.of(strings).mapToLong(Long::parseLong).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new long[0];
    }
}
