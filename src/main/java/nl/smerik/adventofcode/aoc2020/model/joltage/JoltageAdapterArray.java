package nl.smerik.adventofcode.aoc2020.model.joltage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JoltageAdapterArray {

    private static final Integer OUTLET_JOLTS = 0;

    private final List<Integer> adapterChain;

    public JoltageAdapterArray(final List<Integer> adapters) {
        this.adapterChain = buildAdapterChain(adapters);
    }

    private List<Integer> buildAdapterChain(final List<Integer> adapters) {
        final List<Integer> chain = new ArrayList<>(adapters);
        chain.add(OUTLET_JOLTS);
        chain.add(adapters.stream().max(Integer::compareTo).orElseThrow() + 3);
        return chain.stream().sorted().collect(Collectors.toList());
    }

    public int countDifferencesForJolt(final int jolt) {
        int count = 0;
        for (int i = 1; i < adapterChain.size(); i++) {
            if (adapterChain.get(i) - adapterChain.get(i - 1) == jolt) {
                count++;
            }
        }
        return count;
    }
}
