package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.game.scratchcard.ScratchCardAdministrator;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day04Service {

    @Value("classpath:input/day-04.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final ScratchCardAdministrator administrator = new ScratchCardAdministrator(lines);
        return administrator.sumPoints();
    }

    public Integer getSolutionPart2() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        return null;
    }
}
