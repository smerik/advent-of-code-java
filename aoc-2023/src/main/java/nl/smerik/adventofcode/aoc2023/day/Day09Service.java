package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.oasis.OasisReport;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day09Service {

    private final OasisReport report;

    public Day09Service(@Value("classpath:input/day-09.txt") final Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        report = new OasisReport(lines);
    }

    public Integer getSolutionPart1() {
        return report.sumPredictedHistory();
    }

    public Integer getSolutionPart2() {
        return report.sumPredictedBackwardHistory();
    }
}
