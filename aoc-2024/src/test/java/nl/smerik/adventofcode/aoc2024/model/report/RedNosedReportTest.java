package nl.smerik.adventofcode.aoc2024.model.report;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootTest
class RedNosedReportTest {

    @Value("classpath:input/day-02/example-01.txt")
    private Resource resource;

    @Test
    void countSafeReports() {
        // Given
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        // When
        final RedNosedReport report = new RedNosedReport(lines);
        // Then
        Assertions.assertEquals(2, report.countSafeReports());
    }
}