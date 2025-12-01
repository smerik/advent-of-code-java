package nl.smerik.adventofcode.aoc2025.model.safe;

public record Rotation(Direction direction, int distance) {

    int getSteps() {
        return direction().getStep() * distance;
    }
}
