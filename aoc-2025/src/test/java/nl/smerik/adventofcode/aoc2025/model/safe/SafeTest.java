package nl.smerik.adventofcode.aoc2025.model.safe;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SafeTest {

    @Value("classpath:input/day-01/example-01.txt")
    private Resource example01Resource;

    @Test
    void determinePassword() {
        // Given
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final Safe safe = new Safe(lines);
        // When & Then
        assertEquals(3, safe.determinePassword());
    }
}