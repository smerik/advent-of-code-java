package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArcadeTest {

    @Value("classpath:input/day-13/example-01.txt")
    private Resource example01Resource;

    @Test
    void testDetermineMinTokensToWinAllPossiblePrizes() {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final Arcade arcade = new Arcade(lines);
        assertEquals(480, arcade.determineMinTokensToWinAllPossiblePrizes());
    }
}