package nl.smerik.adventofcode.aoc2023.model.oasis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OasisReadingTest {

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "18, 0 3 6 9 12 15",
            "28, 1 3 6 10 15 21",
            "68, 10 13 16 21 30 45"
            // @formatter:on
    })
    void testPredictNextValue(final int expectedResult, final String line) {
        final OasisReading reading = new OasisReading(line);
        assertEquals(expectedResult, reading.predictNextValue());
    }

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "-3, 0 3 6 9 12 15",
            " 0, 1 3 6 10 15 21",
            " 5, 10 13 16 21 30 45"
            // @formatter:on
    })
    void testPredictFirstValue(final int expectedResult, final String line) {
        final OasisReading reading = new OasisReading(line);
        assertEquals(expectedResult, reading.predictFirstValue());
    }
}