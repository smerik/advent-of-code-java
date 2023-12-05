package nl.smerik.adventofcode.aoc2023.model.almanac;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class AlmanacMap {

    private final AlmanacCategory sourceCategory;
    private final AlmanacCategory destinationCategory;
    private final Set<AlmanacRange> ranges;

    public AlmanacMap(final AlmanacCategory sourceCategory, final AlmanacCategory destinationCategory) {
        this.sourceCategory = sourceCategory;
        this.destinationCategory = destinationCategory;
        this.ranges = new HashSet<>();
    }

    public void addRange(final AlmanacRange range) {
        ranges.add(range);
    }

    public long mapSourceToDestination(long number) {
        return ranges.stream().filter(range -> range.containsSource(number)).findAny().map(range -> range.mapSourceToDestination(number)).orElse(number);
    }

    public long mapDestinationToSource(long number) {
        return ranges.stream().filter(range -> range.containsDestination(number)).findAny().map(range -> range.mapDestinationToSource(number)).orElse(number);
    }
}
