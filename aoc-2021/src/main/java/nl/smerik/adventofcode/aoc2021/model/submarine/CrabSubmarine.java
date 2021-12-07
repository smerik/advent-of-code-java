package nl.smerik.adventofcode.aoc2021.model.submarine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrabSubmarine {

    private final List<Integer> horizontalCrabPositions;
    private final int minPosition;
    private final int maxPosition;

    public CrabSubmarine(final List<Integer> horizontalCrabPositions) {
        this.horizontalCrabPositions = horizontalCrabPositions;
        this.minPosition = horizontalCrabPositions.stream().mapToInt(v -> v).min().orElseThrow();
        this.maxPosition = horizontalCrabPositions.stream().mapToInt(v -> v).max().orElseThrow();
    }

    /**
     * Calculates the amount of fuel that will be spent when moving from a position to another position.
     *
     * @param fromPosition           the position to move from
     * @param toPosition             the position to move to
     * @param burnFuelAtConstantRate <code>true</code> if fuel burns at a constant rate; <code>false</code> otherwise
     * @return the amount of spent fuel
     */
    static int calculateFuelSpent(final int fromPosition, final int toPosition, final boolean burnFuelAtConstantRate) {
        final int diff = Math.abs(fromPosition - toPosition);
        if (burnFuelAtConstantRate) {
            return diff;
        }
        return calculateFuelSpentAtNonConstantRate(diff);
    }

    private static int calculateFuelSpentAtNonConstantRate(final int distance) {
        if (distance < 2) {
            return distance;
        }
        return distance + calculateFuelSpentAtNonConstantRate(distance - 1);
    }

    /**
     * Calculates the amount of fuel that will be spent when moving all crabs to the same aligned position
     * and returns the lowest amount of fuel that could be spent.
     *
     * @param burnFuelAtConstantRate <code>true</code> if fuel burns at a constant rate; <code>false</code> otherwise
     * @return the lowest amount of spent value
     */
    public int determineLowestFuel(final boolean burnFuelAtConstantRate) {
        return calculateFuelSpentByAlignedPosition(burnFuelAtConstantRate).values().stream().mapToInt(v -> v).min().orElseThrow();
    }

    /**
     * Calculates the amount of fuel that would be spent when moving all crabs to all possible aligned positions.
     * The result will be the amount of spent fuel per aligned position.
     *
     * @param burnFuelAtConstantRate <code>true</code> if fuel burns at a constant rate; <code>false</code> otherwise
     * @return the amount of spent fuel per aligned position
     */
    public Map<Integer, Integer> calculateFuelSpentByAlignedPosition(final boolean burnFuelAtConstantRate) {
        final Map<Integer, Integer> fuelSpentByAlignedPosition = new HashMap<>();
        for (int position = minPosition; position < maxPosition; position++) {
            final int totalFuel = calculateFuelSpentForAlignedPosition(position, burnFuelAtConstantRate);
            fuelSpentByAlignedPosition.put(position, totalFuel);
        }
        return fuelSpentByAlignedPosition;
    }

    /**
     * Calculates the amount of fuel that would be spent when moving all crabs to given position.
     *
     * @param position               the position to align to
     * @param burnFuelAtConstantRate <code>true</code> if fuel burns at a constant rate; <code>false</code> otherwise
     * @return the amount of value that would be spent
     */
    public int calculateFuelSpentForAlignedPosition(final int position, final boolean burnFuelAtConstantRate) {
        return horizontalCrabPositions.stream()
                .mapToInt(v -> v)
                .map(fromPosition -> calculateFuelSpent(fromPosition, position, burnFuelAtConstantRate))
                .sum();
    }
}
