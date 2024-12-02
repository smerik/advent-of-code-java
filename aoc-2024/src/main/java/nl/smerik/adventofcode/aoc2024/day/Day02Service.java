package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.report.RedNosedReport;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day02Service {

    final RedNosedReport report;

    public Day02Service(@Value("classpath:input/day-02.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        report = new RedNosedReport(lines);
    }

    public Long getSolutionPart1() {
        return report.countSafeReports();
    }
}
