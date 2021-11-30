package nl.smerik.adventofcode.aoc2019.day;

import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import nl.smerik.adventofcode.aoc2019.service.PuzzleInputService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Day02Service {

    private final PuzzleInputService puzzleInputService;

    @Value("classpath:input/day-02.txt")
    private Resource resource;

    public Day02Service(final PuzzleInputService puzzleInputService) {
        this.puzzleInputService = puzzleInputService;
    }

    public long getSolutionPart1() {
        LOG.info("getSolutionPart1");
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        program[1] = 12;
        program[2] = 2;
        final IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        return computer.getMemory()[0];
    }

    public String getSolutionPart2() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final Long requiredOutput = 19690720L;
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                program[1] = noun;
                program[2] = verb;

                final IntcodeComputer computer = new IntcodeComputer(program);
                computer.run();
                if (requiredOutput.equals(computer.getMemory()[0])) {
                    return String.format("%02d%02d", noun, verb);
                }
            }
        }
        return "???";
    }
}
