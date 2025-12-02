package nl.smerik.adventofcode.aoc2025.day;

import nl.smerik.adventofcode.aoc2025.model.giftshop.Database;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day02Service {

    private final Database database;

    public Day02Service(@Value("classpath:input/day-02.txt") Resource resource) {
        this.database = new Database(PuzzleInputParser.parseToString(resource).get(0));
    }

    public Long getSolutionPart1() {
        return database.sumInvalidIds(false);
    }

    public Long getSolutionPart2() {
        return database.sumInvalidIds(true);
    }
}
