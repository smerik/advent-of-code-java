package nl.smerik.adventofcode.aoc2024.model.bridge;

import java.util.Arrays;
import java.util.List;

public class BridgeCalibration {

    private final List<Equation> equations;

    public BridgeCalibration(final List<String> lines) {
        this.equations = parseLines(lines);
    }

    private List<Equation> parseLines(final List<String> lines) {
        return lines.stream().map(this::parseLine).toList();
    }

    private Equation parseLine(final String line) {
        final String[] testValueAndNumbers = line.split(": ");
        final Long testValue = Long.parseLong(testValueAndNumbers[0]);
        final List<Long> numbers = Arrays.stream(testValueAndNumbers[1].split(" "))
                .mapToLong(Long::valueOf).boxed().toList();
        return new Equation(testValue, numbers);
    }

    public Long calculateTotalCalibrationResult() {
        return this.equations.stream()
                .filter(Equation::isEquationTrue)
                .mapToLong(Equation::getTestValue)
                .sum();
    }
}
