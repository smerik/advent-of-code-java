package nl.smerik.adventofcode.aoc2025.model.giftshop;

import java.util.Arrays;
import java.util.List;

public class Database {

    private final List<ProductIdRange> productIdRanges;

    public Database(final String line) {
        this.productIdRanges = parseLine(line);
    }

    private List<ProductIdRange> parseLine(final String line) {
        return Arrays.stream(line.split(",")).map(ProductIdRange::new).toList();
    }

    public long sumInvalidIds(final boolean shouldHaveMultipleRepeats) {
        return productIdRanges.stream()
                .map(productIdRange -> productIdRange.findInvalidIds(shouldHaveMultipleRepeats))
                .flatMap(List::stream)
                .mapToLong(Long::longValue)
                .sum();
    }
}
