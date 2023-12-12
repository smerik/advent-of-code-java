package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import java.util.List;

public class HotSpringsMaintenance {

    private final List<ConditionRecord> conditionRecords;

    public HotSpringsMaintenance(final List<String> lines) {
        conditionRecords = parseLines(lines);
    }

    private List<ConditionRecord> parseLines(final List<String> lines) {
        return lines.stream().map(ConditionRecord::new).toList();
    }

    public int sumPossibleArrangements() {
        return conditionRecords.stream().map(ConditionRecord::countPossibleArrangements).mapToInt(Integer::intValue).sum();
    }
}
