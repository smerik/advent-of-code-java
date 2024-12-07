package nl.smerik.adventofcode.aoc2024.model.bridge;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BridgeCalibrationTest {

    private final BridgeCalibration calibration;

    public BridgeCalibrationTest(@Value("classpath:input/day-07/example-01.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        this.calibration = new BridgeCalibration(lines);
    }

    @Test
    void testCalculateTotalCalibrationResult() {
        assertEquals(3749L, calibration.calculateTotalCalibrationResult());
    }
}