package nl.smerik.adventofcode.aoc2023.model.oasis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OasisReading {

    private final List<Integer> readings;

    public OasisReading(final String line) {
        this.readings = parseLine(line);
    }

    private List<Integer> parseLine(final String line) {
        return Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
    }

    public int predictNextValue() {
        return predictNextValue(readings);
    }

    private int predictNextValue(final List<Integer> readings) {
        final int readingsLast = readings.get(readings.size() - 1);
        if (isFinalSequence(readings)) {
            return readingsLast;
        }
        final List<Integer> sequence = createNextSequence(readings);
        return readingsLast + predictNextValue(sequence);
    }

    public int predictFirstValue() {
        return predictFirstValue(readings);
    }

    private int predictFirstValue(final List<Integer> readings) {
        final int readingsFirst = readings.get(0);
        if (isFinalSequence(readings)) {
            return readingsFirst;
        }
        final List<Integer> sequence = createNextSequence(readings);
        return readingsFirst - predictFirstValue(sequence);
    }

    private static boolean isFinalSequence(final List<Integer> readings) {
        return readings.stream().allMatch(integer -> integer == 0) || readings.size() == 1;
    }

    private List<Integer> createNextSequence(final List<Integer> readings) {
        final List<Integer> result = new ArrayList<>();
        Integer previousReading = null;
        for (final int reading : readings) {
            if (previousReading != null) {
                result.add(reading - previousReading);
            }
            previousReading = reading;
        }
        return result;
    }
}
