package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.puzzle.WordSearch;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day04Service {

    final WordSearch wordSearch;

    public Day04Service(@Value("classpath:input/day-04.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        wordSearch = new WordSearch(lines);
    }

    public int getSolutionPart1() {
        return wordSearch.countWord("XMAS");
    }
}
