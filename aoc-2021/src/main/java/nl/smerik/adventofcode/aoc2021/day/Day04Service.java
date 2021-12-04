package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.game.bingo.BingoSubsystem;
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
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final BingoSubsystem bingo = new BingoSubsystem(input);
        return bingo.playUntilBingo().calculateScore();
    }

    public Integer getSolutionPart2() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final BingoSubsystem bingo = new BingoSubsystem(input);
        return bingo.findLastCompletedCard().calculateScore();
    }
}
