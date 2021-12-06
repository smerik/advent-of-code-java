package nl.smerik.adventofcode.aoc2021.model.fish;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LanternfishSchool {

    private final Set<Lanternfish> lanternfishes;

    public LanternfishSchool(final List<Integer> ages) {
        lanternfishes = parseAges(ages);
    }

    private Set<Lanternfish> parseAges(final List<Integer> ages) {
        return ages.stream().map(Lanternfish::new).collect(Collectors.toSet());
    }

    /**
     * Simulates a school of lanternfish for given amount of days.
     * <p>
     * Returns the total amount of fish in the school after the simulation.
     *
     * @param days the amount of days to simulate
     * @return the total amount of fish
     */
    public int simulate(final int days) {
        for (int i = 0; i < days; i++) {
            final Set<Lanternfish> newBornFish = lanternfishes.stream()
                    .map(Lanternfish::simulate)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toSet());
            lanternfishes.addAll(newBornFish);
        }
        return lanternfishes.size();
    }
}
