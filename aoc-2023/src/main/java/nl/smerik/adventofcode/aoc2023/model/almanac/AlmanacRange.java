package nl.smerik.adventofcode.aoc2023.model.almanac;

public class AlmanacRange {

    private final long destinationRangeStart;
    private final long sourceRangeStart;
    private final long rangeLength;

    public AlmanacRange(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }

    public boolean containsSource(final long number) {
        return number >= sourceRangeStart && number < sourceRangeStart + rangeLength;
    }

    public long mapSourceToDestination(final long number) {
        if (!containsSource(number)) {
            throw new IllegalArgumentException("Number " + number + " not within range.");
        }
        return destinationRangeStart + number - sourceRangeStart;
    }
}
