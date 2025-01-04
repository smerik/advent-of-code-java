package nl.smerik.adventofcode.aoc2024.model.computer.chronospatial;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChronospatialComputerTest {

    @Value("classpath:input/day-17/example-01.txt")
    private Resource example01Resource;

    @Test
    void testRunProgram() {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final ChronospatialComputer computer = new ChronospatialComputer(lines);
        assertEquals("4,6,3,5,6,3,5,2,1,0", computer.runProgram());
    }
}