package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import java.util.Arrays;
import java.util.List;

public class ConditionRecord {

    private final List<ConditionType> conditions;
    private final List<Integer> groupSizes;

    public ConditionRecord(final String line) {
        final String[] split = line.split(" ");
        this.conditions = parseRecord(split[0]);
        this.groupSizes = parseCriteria(split[1]);
    }

    private List<ConditionType> parseRecord(final String conditionRecord) {
        return conditionRecord.chars().mapToObj(c -> (char) c).map(ConditionType::valueOfConditionToken).toList();
    }

    private List<Integer> parseCriteria(final String criteria) {
        return Arrays.stream(criteria.split(",")).map(Integer::valueOf).toList();
    }

    public int countPossibleArrangements() {
        return findPossibleArrangements(0, 0, 0);
    }

    private int findPossibleArrangements(final int conditionIndex, final int groupIndex, final int groupLengthBuildUpSoFar) {
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

        int result = 0;
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
        return result;
    }

    private int processDamaged(final int conditionIndex, final int groupIndex, final int groupLengthBuildUpSoFar) {
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

    private int processOperational(final int conditionIndex, final int groupIndex, final int groupLengthBuildUpSoFar) {
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
