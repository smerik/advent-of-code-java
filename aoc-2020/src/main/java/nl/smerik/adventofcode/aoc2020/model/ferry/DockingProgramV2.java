package nl.smerik.adventofcode.aoc2020.model.ferry;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DockingProgramV2 extends AbstractDockingProgram {

    private final Set<Integer> maskOneIndexes;
    private final List<Integer> maskFloatingIndexes;

    public DockingProgramV2() {
        maskOneIndexes = new HashSet<>();
        maskFloatingIndexes = new ArrayList<>();
    }

    @Override
    public void updateBitmask(final String bitmask) {
        final String reversedBitMask = StringUtils.reverse(bitmask);
        maskOneIndexes.clear();
        maskOneIndexes.addAll(findIndexes(reversedBitMask, '1'));

        maskFloatingIndexes.clear();
        maskFloatingIndexes.addAll(findIndexes(reversedBitMask, 'X'));
    }

    private Set<Integer> findIndexes(final String reversedBitMask, final char maskCharacter) {
        final Set<Integer> result = new HashSet<>();
        int index = 0;
        while (index != -1) {
            index = reversedBitMask.indexOf(maskCharacter, index);
            if (index != -1) {
                result.add(index);
                index++;
            }
        }
        return result;
    }

    @Override
    public void writeToMemory(final long address, final long value) {
        for (final long a : determineAddresses(address)) {
            memory.put(a, value);
        }
    }

    private Set<Long> determineAddresses(final long address) {
        final long appliedOneMaskAddress = applyOneMask(address);

        final Set<Long> result = new HashSet<>();
        permuteFloatingAddress(result, appliedOneMaskAddress, 0);
        return result;
    }

    private long applyOneMask(final long address) {
        long result = address;
        for (final Integer bitPosition : maskOneIndexes) {
            result |= 1L << bitPosition;
        }
        return result;
    }

    private void permuteFloatingAddress(final Set<Long> values, final long address, final int index) {
        final byte[] bitValues = {0, 1};
        for (byte bitValue : bitValues) {
            if (index < maskFloatingIndexes.size()) {
                if (bitValue == 0) {
                    permuteFloatingAddress(values, address & ~(1L << maskFloatingIndexes.get(index)), index + 1);
                } else {
                    permuteFloatingAddress(values, address | (1L << maskFloatingIndexes.get(index)), index + 1);
                }
                continue;
            }
            values.add(address);
        }
    }
}
