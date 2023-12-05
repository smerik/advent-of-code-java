package nl.smerik.adventofcode.geom;

public record Range<T extends Comparable<T>>(T start, T endExclusive) {

    public boolean contains(T value) {
        return value.compareTo(start) >= 0 && value.compareTo(endExclusive) < 0;
    }
}
