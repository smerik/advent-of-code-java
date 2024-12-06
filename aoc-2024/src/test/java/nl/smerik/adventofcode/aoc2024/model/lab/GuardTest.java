package nl.smerik.adventofcode.aoc2024.model.lab;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GuardTest {

    private final Guard guard;

    public GuardTest(@Value("classpath:input/day-06/example-01.txt") Resource example01Resource) {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        guard = new Guard(lines);

    }

    @Test
    void testCountDistinctPositionsVisited() {
        assertEquals(41, guard.countDistinctPositionsVisited());
    }
}