package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import nl.smerik.adventofcode.aoc2019.service.PuzzleInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day05Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day05Service.class);

    private final PuzzleInputService puzzleInputService;

    @Value("classpath:input/day-05.txt")
    private Resource resource;

    public Day05Service(final PuzzleInputService puzzleInputService) {
        this.puzzleInputService = puzzleInputService;
    }

    public long getSolutionPart1() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final IntcodeComputer computer = new IntcodeComputer(program);
        final List<Long> output = computer.run(1L);
        return output.get(output.size() - 1);
    }

    public long getSolutionPart2() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final IntcodeComputer computer = new IntcodeComputer(program);
        final List<Long> output = computer.run(5L);
        return output.get(output.size() - 1);
    }
}
