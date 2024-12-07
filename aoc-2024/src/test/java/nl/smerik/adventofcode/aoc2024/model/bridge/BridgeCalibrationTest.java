package nl.smerik.adventofcode.aoc2024.model.bridge;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BridgeCalibrationTest {

    private final BridgeCalibration calibration;

    public BridgeCalibrationTest(@Value("classpath:input/day-07/example-01.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        this.calibration = new BridgeCalibration(lines);
    }

    @ParameterizedTest
    @CsvSource({
            " 3749, false",
            "11387, true "
    })
    void testCalculateTotalCalibrationResult(final long expectedResult, final boolean applyConcatenationOperator) {
        assertEquals(expectedResult, calibration.calculateTotalCalibrationResult(applyConcatenationOperator));
    }
}