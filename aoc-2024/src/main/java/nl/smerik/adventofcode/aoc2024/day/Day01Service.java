package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.notes.AssortmentOfNotes;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day01Service {

    final AssortmentOfNotes notes;

    public Day01Service(@Value("classpath:input/day-01.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        notes = new AssortmentOfNotes(lines);
    }

    public Integer getSolutionPart1() {
        return notes.calculateTotalDistance();
    }

    public Long getSolutionPart2() {
        return notes.calculateTotalSimilarityScore();
    }
}
