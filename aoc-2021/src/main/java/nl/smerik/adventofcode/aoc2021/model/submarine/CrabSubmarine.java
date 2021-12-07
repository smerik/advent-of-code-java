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
     * @param fromPosition the position to move from
     * @param toPosition   the position to move to
     * @return the amount of spent fuel
     */
    static int calculateFuelSpent(final int fromPosition, final int toPosition) {
        return Math.abs(fromPosition - toPosition);
    }

    /**
     * Calculates the amount of fuel that will be spent when moving all crabs to the same aligned position
     * and returns the lowest amount of fuel that could be spent.
     *
     * @return the lowest amount of spent value
     */
    public int determineLowestFuel() {
        return calculateFuelSpentByAlignedPosition().values().stream().mapToInt(v -> v).min().orElseThrow();
    }

    /**
     * Calculates the amount of fuel that would be spent when moving all crabs to all possible aligned positions.
     * The result will be the amount of spent fuel per aligned position.
     *
     * @return the amount of spent fuel per aligned position
     */
    public Map<Integer, Integer> calculateFuelSpentByAlignedPosition() {
        final Map<Integer, Integer> fuelSpentByAlignedPosition = new HashMap<>();
        for (int position = minPosition; position < maxPosition; position++) {
            final int totalFuel = calculateFuelSpentForAlignedPosition(position);
            fuelSpentByAlignedPosition.put(position, totalFuel);
        }
        return fuelSpentByAlignedPosition;
    }

    /**
     * Calculates the amount of fuel that would be spent when moving all crabs to given position.
     *
     * @param position the position to align to
     * @return the amount of value that would be spent
     */
    public int calculateFuelSpentForAlignedPosition(final int position) {
        return horizontalCrabPositions.stream()
                                      .mapToInt(v -> v)
                                      .map(fromPosition -> calculateFuelSpent(fromPosition, position))
                                      .sum();
    }
}
