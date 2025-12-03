package nl.smerik.adventofcode.aoc2025.model.lobby;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmergencyPowerTest {

    @Value("classpath:input/day-03/example-01.txt")
    private Resource example01Resource;

    @Test
    void calcTotalOutputJoltage() {
        // Given
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final EmergencyPower power = new EmergencyPower(lines);
        // When & Then
        assertEquals(357, power.calcTotalOutputJoltage());
    }
}