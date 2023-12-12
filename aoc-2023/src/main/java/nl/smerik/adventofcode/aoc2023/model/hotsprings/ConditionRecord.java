package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import java.util.*;
import java.util.stream.IntStream;

public class ConditionRecord {

    private final List<ConditionType> conditions;
    private final List<Integer> groupSizes;

    private final Map<ArrangementIndex, Long> cache;

    public ConditionRecord(final String line, final int copyCount) {
        final String[] split = line.split(" ");
        this.conditions = parseRecord(split[0], copyCount);
        this.groupSizes = parseCriteria(split[1], copyCount);
        this.cache = new HashMap<>();
    }

    private List<ConditionType> parseRecord(final String conditionRecord, final int copyCount) {
        final List<ConditionType> conditionTokens = conditionRecord.chars().mapToObj(c -> (char) c).map(ConditionType::valueOfConditionToken).toList();
        if (copyCount == 1) {
            return conditionTokens;
        }

        final List<ConditionType> result = new ArrayList<>(conditionTokens);
        for (int i = 1; i < copyCount; i++) {
            result.add(ConditionType.UNKNOWN);
            result.addAll(conditionTokens);
        }
        return result;
    }

    private List<Integer> parseCriteria(final String criteria, final int copyCount) {
        return IntStream.range(0, copyCount)
                .mapToObj(integer -> Arrays.stream(criteria.split(",")).map(Integer::valueOf))
                .flatMap(integerStream -> integerStream)
                .toList();
    }

    public long countPossibleArrangements() {
        return findPossibleArrangements(0, 0, 0);
    }

    private long findPossibleArrangements(final int conditionIndex, final int groupIndex, final int groupLengthBuildUpSoFar) {
        final ArrangementIndex arrangementIndex = new ArrangementIndex(conditionIndex, groupIndex, groupLengthBuildUpSoFar);
        if (cache.containsKey(arrangementIndex)) {
            return cache.get(arrangementIndex);
        }

        // Check if end of record condition has been reached.
        if (conditionIndex >= conditions.size()) {
            int groupsCount = groupSizes.size();
            // Check if the current index is the last criteria element
            if (groupIndex + 1 == groupsCount) {
                // Check if the last group  matches the criteria
                return groupSizes.get(groupIndex) == groupLengthBuildUpSoFar ? 1 : 0;
            }
            // No arrangement found when the group index is smaller than the group size.
            // In other words: when an arrangement has been found for criteria (4,1,1) then the index will be 4.
            return groupIndex < groupsCount ? 0 : 1;
        }

        long result = 0;
        final ConditionType type = conditions.get(conditionIndex);
        switch (type) {
            case OPERATIONAL:
                result += processOperational(conditionIndex, groupIndex, groupLengthBuildUpSoFar);
                break;
            case DAMAGED:
                result += processDamaged(conditionIndex, groupIndex, groupLengthBuildUpSoFar);
                break;
            case UNKNOWN:
                // We now have 2 options to choose from.
                // Option 1: tile is OPERATIONAL.
                result += processOperational(conditionIndex, groupIndex, groupLengthBuildUpSoFar);
                // Option 2: tile is DAMAGED.
                result += processDamaged(conditionIndex, groupIndex, groupLengthBuildUpSoFar);
                break;
            default:
                throw new IllegalArgumentException("No support for type: " + type);
        }
        cache.put(arrangementIndex, result);
        return result;
    }

    private long processDamaged(final int conditionIndex, final int groupIndex, final int groupLengthBuildUpSoFar) {
        if (groupIndex >= groupSizes.size()) {
            // No additional criteria to match the damaged spring to: no possible arrangement.
            return 0;
        }

        final int newGroupLength = groupLengthBuildUpSoFar + 1;
        final int groupToFindSize = groupSizes.get(groupIndex);
        if (newGroupLength > groupToFindSize) {
            // Exceeded the group size condition: no possible arrangement.
            return 0;
        }
        return findPossibleArrangements(conditionIndex + 1, groupIndex, newGroupLength);
    }

    private long processOperational(final int conditionIndex, final int groupIndex, final int groupLengthBuildUpSoFar) {
        if (groupLengthBuildUpSoFar == 0 || groupIndex >= groupSizes.size()) {
            // No group found yet OR all groups have been matched: continue.
            return findPossibleArrangements(conditionIndex + 1, groupIndex, groupLengthBuildUpSoFar);
        }

        final int groupToFindSize = groupSizes.get(groupIndex);
        if (groupLengthBuildUpSoFar < groupToFindSize) {
            // Group to find was not possible: there is no need to look any further.
            return 0;
        }

        // Group found: go find next group (so increase group index by 1 and reset length build up to 0).
        return findPossibleArrangements(conditionIndex + 1, groupIndex + 1, 0);
    }
}
