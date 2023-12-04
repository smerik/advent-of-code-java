package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.game.scratchcard.ScratchCardAdministrator;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day04Service {

    private final ScratchCardAdministrator administrator;

    public Day04Service(@Value("classpath:input/day-04.txt") final Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        administrator = new ScratchCardAdministrator(lines);
    }

    public Integer getSolutionPart1() {
        return administrator.sumPoints();
    }

    public Integer getSolutionPart2() {
        return administrator.sumCards();
    }
}
