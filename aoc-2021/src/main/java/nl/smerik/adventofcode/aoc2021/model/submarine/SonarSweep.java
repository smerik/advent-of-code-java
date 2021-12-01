package nl.smerik.adventofcode.aoc2021.model.submarine;

import java.util.ArrayList;
import java.util.List;

public class SonarSweep {

    private static final int SLIDING_WINDOW_SIZE = 3;

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
        return countIncreasingDepthMeasurements(measurements);
    }

    private int countIncreasingDepthMeasurements(final List<Integer> depthMeasurements) {
        int result = 0;
        Integer previousMeasurement = null;
        for (final int measurement : depthMeasurements) {
            if (previousMeasurement != null && measurement > previousMeasurement) {
                result++;
            }
            previousMeasurement = measurement;
        }
        return result;
    }

    /**
     * Counts the number of times a depth measurement increases using a three-measurement sliding window.
     *
     * @return the number of increased measurements
     */
    public int countIncreasingDepthSlidingWindowMeasurements() {
        final List<Integer> slidingWindows = createSumOfSlidingWindows();
        return countIncreasingDepthMeasurements(slidingWindows);
    }

    List<Integer> createSumOfSlidingWindows() {
        return createSlidingWindows().stream()
                                     .map(slidingWindow -> slidingWindow.stream().mapToInt(Integer::intValue).sum())
                                     .toList();
    }

    List<List<Integer>> createSlidingWindows() {
        final List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= measurements.size() - SLIDING_WINDOW_SIZE; i++) {
            final List<Integer> slidingWindowMeasurements = new ArrayList<>();
            for (int j = i; j < i + SLIDING_WINDOW_SIZE; j++) {
                slidingWindowMeasurements.add(measurements.get(j));
            }
            result.add(slidingWindowMeasurements);
        }
        return result;
    }
}
