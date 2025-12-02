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

    public List<Long> findInvalidIds(final boolean shouldHaveMultipleRepeats) {
        return LongStream.rangeClosed(this.min, this.max).filter(id -> isInvalid(id, shouldHaveMultipleRepeats)).boxed().toList();
    }

    public static boolean isInvalid(final long id, final boolean shouldHaveMultipleRepeats) {
        return shouldHaveMultipleRepeats ? isInvalidWhenContainingMultipleRepeats(id) : isInvalidWhenContainingSingleRepeat(id);
    }

    private static boolean isInvalidWhenContainingMultipleRepeats(final long id) {
        final String idString = String.valueOf(id);
        final int maxLength = idString.length() / 2;
        for (int currentLength = 1; currentLength <= maxLength; currentLength++) {
            if (idString.length() % currentLength != 0) {
                continue;
            }
            String currentSequence = idString.substring(0, currentLength);
            String nextSequence = "";
            for (int i = currentLength; i + currentLength <= idString.length(); i = i + currentLength) {
                nextSequence = idString.substring(i, i + currentLength);
                if (!nextSequence.equals(currentSequence)) {
                    break;
                }
                currentSequence = nextSequence;
            }
            if (nextSequence.equals(currentSequence)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInvalidWhenContainingSingleRepeat(final long id) {
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
