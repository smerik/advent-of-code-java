package nl.smerik.adventofcode.aoc2024.model.puzzle;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootTest
class WordSearchTest {

    @Value("classpath:input/day-04/example-01.txt")
    private Resource example01Resource;

    @Test
    void testCountWord() {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final WordSearch wordSearch = new WordSearch(lines);
        Assertions.assertEquals(18, wordSearch.countWord("XMAS"));
    }
}