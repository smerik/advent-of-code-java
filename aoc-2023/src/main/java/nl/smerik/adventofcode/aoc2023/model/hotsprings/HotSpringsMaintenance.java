package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import java.util.List;

public class HotSpringsMaintenance {

    private final List<ConditionRecord> conditionRecords;

    public HotSpringsMaintenance(final List<String> lines, int copyCount) {
        conditionRecords = parseLines(lines, copyCount);
    }

    private List<ConditionRecord> parseLines(final List<String> lines, int copyCount) {
        return lines.stream().map(line -> new ConditionRecord(line, copyCount)).toList();
    }

    public long sumPossibleArrangements() {
        return conditionRecords.stream().map(ConditionRecord::countPossibleArrangements).mapToLong(Long::longValue).sum();
    }
}
