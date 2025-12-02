package nl.smerik.adventofcode.aoc2025.model.safe;

import lombok.Getter;

public class Dial {

    private static final int NUMBER_COUNT = 100;

    private int pointsAt;

    @Getter
    private int pointedAtZeroCount;

    public Dial(final int pointsAt) {
        this.pointsAt = pointsAt;
    }

    public int rotate(final Rotation rotation) {
        final int sumDistance = pointsAt + rotation.getSteps();
        if (sumDistance == 0 || pointsAt * sumDistance < 0) {
            pointedAtZeroCount++;
        }
        pointedAtZeroCount += Math.abs(sumDistance / NUMBER_COUNT);

        pointsAt = Math.floorMod(pointsAt + rotation.getSteps(), NUMBER_COUNT);
        return pointsAt;
    }
}
