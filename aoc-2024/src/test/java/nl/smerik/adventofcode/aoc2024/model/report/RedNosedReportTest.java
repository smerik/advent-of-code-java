package nl.smerik.adventofcode.aoc2024.model.report;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedNosedReportTest {

    private final RedNosedReport report;

    public RedNosedReportTest(@Value("classpath:input/day-02/example-01.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        report = new RedNosedReport(lines);
    }

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "2, false",
            "4, true "
            // @formatter:on
    })
    void countSafeReports(final int expectedResult, final boolean applyProblemDampener) {
        assertEquals(expectedResult, report.countSafeReports(applyProblemDampener));
    }
}