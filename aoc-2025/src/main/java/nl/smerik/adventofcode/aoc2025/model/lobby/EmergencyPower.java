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

    public int calcTotalOutputJoltage() {
        return this.banks.stream().mapToInt(BatteryBank::findLargestJoltage).sum();
    }
}
