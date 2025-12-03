package nl.smerik.adventofcode.aoc2025.model.lobby;

public class BatteryBank {

    private final int[] batteries;

    public BatteryBank(final String line) {
        this.batteries = line.chars().map(Character::getNumericValue).toArray();
    }

    public int findLargestJoltage() {
        int batteryOne = 0;
        int batteryTwo = 0;
        for (int i = 0; i < batteries.length; i++) {
            if (batteries[i] > batteryTwo) {
                batteryTwo = batteries[i];
            }
            if (batteries[i] > batteryOne && i + 1 < batteries.length) {
                batteryOne = batteries[i];
                batteryTwo = 0;
            }
        }
        return Integer.parseInt("" + batteryOne + batteryTwo);
    }
}
