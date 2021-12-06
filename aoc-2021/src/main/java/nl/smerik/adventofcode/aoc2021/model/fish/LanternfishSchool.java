package nl.smerik.adventofcode.aoc2021.model.fish;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class LanternfishSchool {

    private final long[] lanternfishCountByTimer;

    public LanternfishSchool(final List<Integer> ages) {
        lanternfishCountByTimer = parseAges(ages);
    }

    private long[] parseAges(final List<Integer> ages) {
        final long[] result = new long[9];
        ages.forEach(age -> result[age]++);
        return result;
    }

    /**
     * Simulates a school of lanternfish for given amount of days.
     * <p>
     * Returns the total amount of fish in the school after the simulation.
     *
     * @param days the amount of days to simulate
     * @return the total amount of fish
     */
    public long simulate(final int days) {
        for (int i = 0; i < days; i++) {
            final long fishCount = lanternfishCountByTimer[0];
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(lanternfishCountByTimer, 1, lanternfishCountByTimer, 0, 8);
            lanternfishCountByTimer[6] += fishCount; // After another day, its internal timer would reset to 6,
            lanternfishCountByTimer[8] = fishCount;  // and it would create a new lanternfish with an internal timer of 8.
        }
        return countTotalFishes();
    }

    public long countTotalFishes() {
        return Arrays.stream(lanternfishCountByTimer).sum();
    }
}
