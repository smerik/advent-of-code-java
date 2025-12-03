package nl.smerik.adventofcode.aoc2025.model.lobby;

public class BatteryBank {

    private final int[] batteryRatings;

    public BatteryBank(final String line) {
        this.batteryRatings = line.chars().map(Character::getNumericValue).toArray();
    }

    public long findLargestJoltage(final int batteryCountToTurnOn) {
        final int batteryCount = batteryRatings.length;
        final int[] result = new int[batteryCount];
        int batteryCountToRemove = batteryCount - batteryCountToTurnOn;
        int i = 0;
        for (final int rating : batteryRatings) {
            // Override smaller digits with greater digits when applicable
            while (batteryCountToRemove > 0 && i > 0 && result[i - 1] < rating) {
                i--;
                batteryCountToRemove--;
            }
            result[i] = rating;
            i++;
        }
        return concatArray(result, batteryCountToTurnOn);
    }

    private long concatArray(final int[] batteryRatings, final int batteryCountToTurnOn) {
        long result = 0L;
        for (int i = 0; i < batteryCountToTurnOn; i++) {
            result = result * 10 + batteryRatings[i];
        }
        return result;
    }
}
