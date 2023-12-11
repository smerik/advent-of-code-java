package nl.smerik.adventofcode.aoc2023.model.cosmic;

import java.awt.*;
import java.util.Objects;

public class GalaxyPair {

    private final Point galaxy1;
    private final Point galaxy2;

    public GalaxyPair(final Point galaxy1, final Point galaxy2) {
        this.galaxy1 = galaxy1;
        this.galaxy2 = galaxy2;
    }

    public int findShortestPathLength() {
        int diffX = Math.abs(galaxy2.x - galaxy1.x);
        int diffY = Math.abs(galaxy2.y - galaxy1.y);
        return diffX + diffY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalaxyPair that = (GalaxyPair) o;
        return (Objects.equals(galaxy1, that.galaxy1) && Objects.equals(galaxy2, that.galaxy2))
                || (Objects.equals(galaxy1, that.galaxy2) && Objects.equals(galaxy2, that.galaxy1));
    }

    @Override
    public int hashCode() {
        // Using XOR here: the order shouldn't matter
        return Objects.hash(galaxy1, galaxy2) ^ Objects.hash(galaxy2, galaxy1);
    }

}
