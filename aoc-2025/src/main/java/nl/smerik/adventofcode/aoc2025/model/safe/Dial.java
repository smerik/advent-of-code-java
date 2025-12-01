package nl.smerik.adventofcode.aoc2025.model.safe;

public class Dial {

    private static final int NUMBER_COUNT = 100;

    private int pointsAt;

    public Dial(final int pointsAt) {
        this.pointsAt = pointsAt;
    }

    public int rotate(final Rotation rotation) {
        pointsAt = Math.floorMod(pointsAt + rotation.getSteps(), NUMBER_COUNT);
        return pointsAt;
    }
}
