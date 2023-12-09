package nl.smerik.adventofcode.aoc2023.model.oasis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OasisReport {

    private final Set<OasisReading> report;

    public OasisReport(final List<String> lines) {
        this.report = parseLines(lines);
    }

    private Set<OasisReading> parseLines(List<String> lines) {
        return lines.stream().map(OasisReading::new).collect(Collectors.toSet());
    }

    public int sumPredictedHistory() {
        return report.stream().map(OasisReading::predictNextValue).mapToInt(Integer::intValue).sum();
    }

    public int sumPredictedBackwardHistory() {
        return report.stream().map(OasisReading::predictFirstValue).mapToInt(Integer::intValue).sum();
    }
}
