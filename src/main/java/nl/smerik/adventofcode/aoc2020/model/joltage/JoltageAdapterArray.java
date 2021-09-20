package nl.smerik.adventofcode.aoc2020.model.joltage;

import java.util.ArrayList;
import java.util.List;

public class JoltageAdapterArray {

    private static final Integer OUTLET_JOLTS = 0;

    private final List<Integer> adapterChain;

    public JoltageAdapterArray(final List<Integer> adapters) {
        this.adapterChain = buildAdapterChain(adapters);
    }

    private List<Integer> buildAdapterChain(final List<Integer> adapters) {
        return adapters.stream().sorted().toList();
    }

    public int countDifferencesForJolt(final int jolt) {
        final List<Integer> chain = new ArrayList<>(adapterChain);
        chain.add(0, OUTLET_JOLTS);
        chain.add(adapterChain.get(adapterChain.size() - 1) + 3);

        int count = 0;
        for (int i = 1; i < chain.size(); i++) {
            if (chain.get(i) - chain.get(i - 1) == jolt) {
                count++;
            }
        }
        return count;
    }

    /**
     * Implementation based on
     * <a href="https://www.reddit.com/r/adventofcode/comments/ka8z8x/2020_day_10_solutions/gfc9x45/?utm_source=share&utm_medium=web2x&context=3">this post</a>
     * in the 2020 Day 10 Solutions Mega thread.
     */
    public long countTotalNumberOfArrangements() {
        final long[] sums = new long[adapterChain.get(adapterChain.size() - 1) + 1];
        sums[0] = 1;
        for (final int j : adapterChain) {
            final long x = j >= 3 ? sums[j - 3] : 0;
            final long y = j >= 2 ? sums[j - 2] : 0;
            final long z = j >= 1 ? sums[j - 1] : 0;
            sums[j] = x + y + z;
        }
        return sums[sums.length - 1];
    }
}
