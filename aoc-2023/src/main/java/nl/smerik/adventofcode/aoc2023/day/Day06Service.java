package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.game.boatrace.RaceAdministrator;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day06Service {

    private final RaceAdministrator administrator;

    public Day06Service(@Value("classpath:input/day-06.txt") final Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        administrator = new RaceAdministrator(lines);
    }

    public Integer getSolutionPart1() {
        return administrator.calcMarginOfError();
    }

    public Integer getSolutionPart2() {
        return null;
    }
}
