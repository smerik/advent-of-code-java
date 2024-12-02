package nl.smerik.adventofcode.aoc2024.model.report;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedNosedReport {

    private final Set<List<Integer>> reports;

    public RedNosedReport(final List<String> lines) {
        reports = parseLines(lines);
    }

    private Set<List<Integer>> parseLines(final List<String> lines) {
        final Set<List<Integer>> result = new HashSet<>();
        for (final String line : lines) {
            result.add(parseLine(line));
        }
        return result;
    }

    private List<Integer> parseLine(final String line) {
        return Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
    }

    public long countSafeReports() {
        return reports.stream().filter(RedNosedReportUtil::isReportSafe).count();
    }
}
