package nl.smerik.adventofcode.aoc2024.model.lab;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ManufacturingLabTest {

    private final ManufacturingLab lab;

    public ManufacturingLabTest(@Value("classpath:input/day-06/example-01.txt") Resource example01Resource) {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        lab = new ManufacturingLab(lines);
    }

    @Test
    void testCountDistinctPositionsVisited() {
        assertEquals(41, lab.countDistinctPositionsVisited());
    }

    @Test
    void testCountLoopPositions() {
        assertEquals(6, lab.countLoopPositions());
    }
}