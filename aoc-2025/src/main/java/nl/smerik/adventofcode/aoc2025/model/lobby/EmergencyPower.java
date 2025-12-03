package nl.smerik.adventofcode.aoc2025.model.lobby;

import java.util.List;

public class EmergencyPower {

    private final List<BatteryBank> banks;

    public EmergencyPower(final List<String> lines) {
        this.banks = parseLines(lines);
    }

    private List<BatteryBank> parseLines(final List<String> lines) {
        return lines.stream().map(BatteryBank::new).toList();
    }

    public long calcTotalOutputJoltage(final int batteryCountToTurnOn) {
        return this.banks.stream().mapToLong(bank -> bank.findLargestJoltage(batteryCountToTurnOn)).sum();
    }
}
