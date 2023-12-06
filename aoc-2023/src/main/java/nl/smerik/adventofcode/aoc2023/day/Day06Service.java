package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.game.boatrace.RaceAdministrator;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day06Service {

    @Value("classpath:input/day-06.txt")
    private Resource resource;

    public long getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        RaceAdministrator administrator = new RaceAdministrator(lines, false);
        return administrator.calcMarginOfError();
    }

    public long getSolutionPart2() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        RaceAdministrator administrator = new RaceAdministrator(lines, true);
        return administrator.calcMarginOfError();
    }
}
