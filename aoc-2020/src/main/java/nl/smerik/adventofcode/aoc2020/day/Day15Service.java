package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.game.MemoryGame;
import org.springframework.stereotype.Service;

@Service
public class Day15Service {

    private static final int[] INPUT = {20, 9, 11, 0, 1, 2};

    public int getSolutionPart1() {
        final MemoryGame game = new MemoryGame(INPUT);
        return game.determineNthSpokenNumber(2020);
    }

    public int getSolutionPart2() {
        final MemoryGame game = new MemoryGame(INPUT);
        return game.determineNthSpokenNumber(30000000);
    }
}
