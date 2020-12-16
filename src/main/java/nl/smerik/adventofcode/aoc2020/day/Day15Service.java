package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.game.MemoryGame;
import org.springframework.stereotype.Service;

@Service
public class Day15Service {

    public int getSolutionPart1() {
        final int[] input = {20, 9, 11, 0, 1, 2};
        final MemoryGame game = new MemoryGame(input);
        return game.determineNthSpokenNumber(2020);
    }
}
