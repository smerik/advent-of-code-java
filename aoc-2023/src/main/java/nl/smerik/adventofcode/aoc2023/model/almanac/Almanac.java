package nl.smerik.adventofcode.aoc2023.model.almanac;

import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class Almanac {

    private final Set<Long> seedsToBePlanted;
    private final Map<AlmanacCategory, AlmanacMap> almanacMapByCategory;

    public Almanac(final Set<Long> seedsToBePlanted, final Map<AlmanacCategory, AlmanacMap> almanacMapByCategory) {
        this.seedsToBePlanted = seedsToBePlanted;
        this.almanacMapByCategory = almanacMapByCategory;
    }

    public long findLowestLocationNumber() {
        return seedsToBePlanted.stream().map(this::mapSeed).min(Long::compareTo).orElseThrow();
    }

    public long mapSeed(final long seedNumber) {
        return mapNumberByCategory(seedNumber, AlmanacCategory.SEED);
    }

    public long mapNumberByCategory(final long sourceNumber, final AlmanacCategory category) {
        if (!almanacMapByCategory.containsKey(category)) {
            return sourceNumber;
        }
        final AlmanacMap almanacMap = almanacMapByCategory.get(category);
        final long destinationNumber = almanacMap.mapSourceToDestination(sourceNumber);
        return mapNumberByCategory(destinationNumber, almanacMap.getDestinationCategory());
    }
}
