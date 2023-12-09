package nl.smerik.adventofcode.aoc2023.model.oasis;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OasisReportTest {

    private static final List<String> EXAMPLE_HISTORY = List.of(
            // @formatter:off
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45"
            // @formatter:on
    );

    @Test
    void testSumPredictedHistory() {
        final OasisReport report = new OasisReport(EXAMPLE_HISTORY);
        assertEquals(114, report.sumPredictedHistory());
    }
}