package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.game.arcade.Arcade;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day13Service {

    private final Arcade arcade;

    public Day13Service(@Value("classpath:input/day-13.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        arcade = new Arcade(lines);
    }

    public int getSolutionPart1() {
        return arcade.determineMinTokensToWinAllPossiblePrizes();
    }
}
