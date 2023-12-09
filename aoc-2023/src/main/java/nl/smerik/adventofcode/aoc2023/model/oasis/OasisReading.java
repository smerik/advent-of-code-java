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
        return createNextSequence(readings);
    }

    private int createNextSequence(final List<Integer> readings) {
        if (readings.stream().allMatch(integer -> integer == 0) || readings.size() == 1) {
            return readings.get(readings.size() - 1);
        }

        final List<Integer> result = new ArrayList<>();
        Integer previousReading = null;
        for (final int reading : readings) {
            if (previousReading != null) {
                result.add(reading - previousReading);
            }
            previousReading = reading;
        }
        return readings.get(readings.size() - 1) + createNextSequence(result);
    }
}
