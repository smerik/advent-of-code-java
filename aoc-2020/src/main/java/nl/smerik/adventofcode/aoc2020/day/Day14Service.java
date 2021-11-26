package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.ferry.DockingProgram;
import nl.smerik.adventofcode.aoc2020.model.ferry.DockingProgramV2;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day14Service {

    @Value("classpath:input/day-14.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        final List<String> initializationProgram = PuzzleInputParser.parseToString(resource);
        final DockingProgram program = new DockingProgram();
        program.run(initializationProgram);
        return program.sumMemory();
    }

    public Long getSolutionPart2() {
        final List<String> initializationProgram = PuzzleInputParser.parseToString(resource);
        final DockingProgramV2 program = new DockingProgramV2();
        program.run(initializationProgram);
        return program.sumMemory();
    }
}
