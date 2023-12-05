package nl.smerik.adventofcode.aoc2023.model.almanac;

import nl.smerik.adventofcode.geom.Range;

public class AlmanacRange {

    private final Range<Long> destinationRange;
    private final Range<Long> sourceRange;

    public AlmanacRange(final long destinationRangeStart, final long sourceRangeStart, final long rangeLength) {
        this.destinationRange = new Range<>(destinationRangeStart, destinationRangeStart + rangeLength);
        this.sourceRange = new Range<>(sourceRangeStart, sourceRangeStart + rangeLength);
    }

    public boolean containsSource(final long number) {
        return sourceRange.contains(number);
    }

    public boolean containsDestination(final long number) {
        return destinationRange.contains(number);
    }

    public long mapSourceToDestination(final long number) {
        if (!containsSource(number)) {
            throw new IllegalArgumentException("Number " + number + " not within " + sourceRange);
        }
        return destinationRange.start() + number - sourceRange.start();
    }

    public long mapDestinationToSource(final long number) {
        if (!containsDestination(number)) {
            throw new IllegalArgumentException("Number " + number + " not within " + destinationRange);
        }
        return sourceRange.start() + number - destinationRange.start();
    }
}
