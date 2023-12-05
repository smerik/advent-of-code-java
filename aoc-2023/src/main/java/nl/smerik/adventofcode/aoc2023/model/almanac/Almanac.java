package nl.smerik.adventofcode.aoc2023.model.almanac;

import lombok.Getter;
import nl.smerik.adventofcode.geom.Range;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class Almanac {

    private final List<Long> seedsToBePlanted;
    private final Map<AlmanacCategory, AlmanacMap> almanacMapBySourceCategory;
    private final Map<AlmanacCategory, AlmanacMap> almanacMapByDestinationCategory;

    public Almanac(final List<Long> seedsToBePlanted, final Map<AlmanacCategory, AlmanacMap> almanacMapBySourceCategory) {
        this.seedsToBePlanted = seedsToBePlanted;
        this.almanacMapBySourceCategory = almanacMapBySourceCategory;
        this.almanacMapByDestinationCategory = almanacMapBySourceCategory.values().stream().collect(Collectors.toMap(AlmanacMap::getDestinationCategory, Function.identity()));
    }

    public long findLowestLocationNumber() {
        return seedsToBePlanted.stream().map(this::mapSeed).min(Long::compareTo).orElseThrow();
    }

    public long mapSeed(final long seedNumber) {
        return mapNumberByCategory(seedNumber, AlmanacCategory.SEED);
    }

    public long mapNumberByCategory(final long sourceNumber, final AlmanacCategory category) {
        if (!almanacMapBySourceCategory.containsKey(category)) {
            return sourceNumber;
        }
        final AlmanacMap almanacMap = almanacMapBySourceCategory.get(category);
        final long destinationNumber = almanacMap.mapSourceToDestination(sourceNumber);
        return mapNumberByCategory(destinationNumber, almanacMap.getDestinationCategory());
    }

    public long findLowestLocationNumberForARangeOfSeeds() {
        // TODO: Optimize since it takes ~3 minutes to find the solution
        final List<Range<Long>> seedsToBePlantedRanges = determineSeedsToBePlantedRanges();
        long locationNumber = 0;
        while (true) {
            final long seedNumber = mapLocation(locationNumber);
            if (seedsToBePlantedRanges.stream().anyMatch(range -> range.contains(seedNumber))) {
                break;
            }
            locationNumber++;
        }
        return locationNumber;
    }

    public List<Range<Long>> determineSeedsToBePlantedRanges() {
        if (seedsToBePlanted.size() % 2 != 0) {
            throw new IllegalArgumentException("Collection of ranges should be even!");
        }
        final List<Range<Long>> result = new ArrayList<>();
        final Iterator<Long> iterator = seedsToBePlanted.iterator();
        while (iterator.hasNext()) {
            long number = iterator.next();
            long range = iterator.next();
            result.add(new Range<>(number, number + range));
        }
        return result;
    }

    public long mapLocation(final long locationNumber) {
        return mapNumberReversedByCategory(locationNumber, AlmanacCategory.LOCATION);
    }

    public long mapNumberReversedByCategory(final long destinationNumber, final AlmanacCategory category) {
        if (!almanacMapByDestinationCategory.containsKey(category)) {
            return destinationNumber;
        }
        final AlmanacMap almanacMap = almanacMapByDestinationCategory.get(category);
        final long sourceNumber = almanacMap.mapDestinationToSource(destinationNumber);
        return mapNumberReversedByCategory(sourceNumber, almanacMap.getSourceCategory());
    }
}
