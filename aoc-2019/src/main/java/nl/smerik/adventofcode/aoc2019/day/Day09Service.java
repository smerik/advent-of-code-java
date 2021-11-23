package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import nl.smerik.adventofcode.aoc2019.service.PuzzleInputService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day09Service {

    private final PuzzleInputService puzzleInputService;

    @Value("classpath:input/day-09.txt")
    private Resource resource;

    public Day09Service(final PuzzleInputService puzzleInputService) {
        this.puzzleInputService = puzzleInputService;
    }

    public Long getSolutionPart1() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final IntcodeComputer computer = new IntcodeComputer(program);
        return computer.run(1L).get(0);
    }

    public Long getSolutionPart2() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final IntcodeComputer computer = new IntcodeComputer(program);
        return computer.run(2L).get(0);
    }
}
