package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.combat.CombatGame;
import nl.smerik.adventofcode.aoc2020.model.combat.CombatPlayer;
import nl.smerik.adventofcode.aoc2020.model.combat.RecursiveCombatGame;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day22Service {

    @Value("classpath:input/day-22.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final CombatGame game = new CombatGame(input);
        final CombatPlayer winner = game.playUntilGameEnds();
        return winner.calculateScore();
    }

    public Integer getSolutionPart2() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final RecursiveCombatGame game = new RecursiveCombatGame(input);
        final CombatPlayer winner = game.playUntilGameEnds();
        return winner.calculateScore();
    }
}
