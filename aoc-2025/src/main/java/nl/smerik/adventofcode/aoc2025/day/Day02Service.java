package nl.smerik.adventofcode.aoc2025.day;

import nl.smerik.adventofcode.aoc2025.model.giftshop.Database;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day02Service {

    private final List<String> lines;

    public Day02Service(@Value("classpath:input/day-02.txt") Resource resource) {
        this.lines = PuzzleInputParser.parseToString(resource);
    }

    public Long getSolutionPart1() {
        final Database database = new Database(lines.get(0));
        return database.sumInvalidIds();
    }
}
