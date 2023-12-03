package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.gondola.EngineSchematic;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day03Service {

    @Value("classpath:input/day-03.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final EngineSchematic schematic = new EngineSchematic(lines);
        return schematic.sumPartNumbers();
    }

    public Integer getSolutionPart2() {
        return null;
    }
}
