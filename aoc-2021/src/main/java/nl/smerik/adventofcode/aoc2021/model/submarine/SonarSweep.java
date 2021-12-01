package nl.smerik.adventofcode.aoc2021.model.submarine;

import java.util.List;

public class SonarSweep {

    private final List<Integer> measurements;

    public SonarSweep(final List<Integer> measurements) {
        this.measurements = measurements;
    }

    /**
     * Counts the number of times a depth measurement increases from the previous measurement.
     * (There is no measurement before the first measurement.)
     *
     * @return the number of increased measurements
     */
    public int countIncreasingDepthMeasurements() {
        int result = 0;
        Integer previousMeasurement = null;
        for (final Integer measurement : measurements) {
            if (previousMeasurement != null && measurement > previousMeasurement) {
                result++;
            }
            previousMeasurement = measurement;
        }
        return result;
    }
}
