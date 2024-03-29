package nl.smerik.adventofcode.aoc2023.model.cosmic;

import java.awt.*;
import java.util.Objects;

public record GalaxyPair(Point galaxy1, Point galaxy2) {

    @Override
    public boolean equals(final Object o) {
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
