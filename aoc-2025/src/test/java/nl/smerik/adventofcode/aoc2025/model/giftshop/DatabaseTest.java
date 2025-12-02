package nl.smerik.adventofcode.aoc2025.model.giftshop;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DatabaseTest {

    @Value("classpath:input/day-02/example-01.txt")
    private Resource example01Resource;

    @Test
    void testSumInvalidIds() {
        // Given
        final String line = PuzzleInputParser.parseToString(example01Resource).get(0);
        final Database database = new Database(line);
        // When & Then
        assertEquals(1227775554L, database.sumInvalidIds());
    }
}