package nl.smerik.adventofcode.aoc2025.model.giftshop;

import java.util.List;
import java.util.stream.LongStream;

public class ProductIdRange {

    private final long min;
    private final long max;

    public ProductIdRange(final String range) {
        final String[] split = range.split("-");
        this.min = Long.parseLong(split[0]);
        this.max = Long.parseLong(split[1]);
    }

    public List<Long> findInvalidIds() {
        return LongStream.rangeClosed(this.min, this.max).filter(this::isInvalid).boxed().toList();
    }

    private boolean isInvalid(final long id) {
        final int digits = (int) Math.log10(id) + 1;
        if (digits % 2 != 0) {
            return false;
        }
        final long pow = (long) Math.pow(10, (double) digits / 2);
        final long left = id / pow;
        final long right = id % pow;
        return left == right;
    }
}
