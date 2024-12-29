package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.game.arcade.Arcade;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day13Service {

    private final List<String> lines;

    public Day13Service(@Value("classpath:input/day-13.txt") Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public long getSolutionPart1() {
        final Arcade arcade = new Arcade(lines, false);
        return arcade.determineMinTokensToWinAllPossiblePrizes();
    }

    public long getSolutionPart2() {
        final Arcade arcade = new Arcade(lines, true);
        return arcade.determineMinTokensToWinAllPossiblePrizes();
    }
}
