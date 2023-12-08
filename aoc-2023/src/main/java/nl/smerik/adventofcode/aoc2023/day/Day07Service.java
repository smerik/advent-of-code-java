package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.game.camelcard.CamelCardsGame;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day07Service {

    @Value("classpath:input/day-07.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final CamelCardsGame game = new CamelCardsGame(lines, false);
        return game.determineTotalWinnings();
    }

    public Integer getSolutionPart2() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final CamelCardsGame game = new CamelCardsGame(lines, true);
        return game.determineTotalWinnings();
    }
}
