package nl.smerik.adventofcode.aoc2025.model.safe;

import lombok.Getter;

public enum Direction {

    L(-1),
    R(1);

    @Getter
    private final int step;

    Direction(final int step) {
        this.step = step;
    }
}
