package nl.smerik.adventofcode.aoc2024.model.farm;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Region {

    @EqualsAndHashCode.Include
    private final int id;
    private final char plantType;
    private int perimeter;
    private int sideCount;
    private int plotCount; // aka the area

    public Region(final int id, final char plantType, final int perimeter, final int sideCount) {
        this.id = id;
        this.plantType = plantType;
        this.perimeter = perimeter;
        this.sideCount = sideCount;
        this.plotCount = 1;
    }

    public void addPlot(final int perimeter, int sideCount) {
        this.perimeter += perimeter;
        this.sideCount += sideCount;
        this.plotCount++;
    }

    public int calculateFencingPrice() {
        return plotCount * perimeter;
    }

    public int calculateFencingPriceOnDiscount() {
        return plotCount * sideCount;
    }
}
