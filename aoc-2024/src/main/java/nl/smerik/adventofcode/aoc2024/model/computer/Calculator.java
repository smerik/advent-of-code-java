package nl.smerik.adventofcode.aoc2024.model.computer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Calculator {

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(?<instruction>mul\\(\\d{1,3},\\d{1,3}\\))");

    private final List<String> instructions;

    public Calculator(final List<String> lines) {
        instructions = parseLines(lines);
    }

    private List<String> parseLines(final List<String> lines) {
        final List<String> result = new ArrayList<>();
        for (String line : lines) {
            result.addAll(parseLine(line));
        }
        return result;
    }

    private List<String> parseLine(final String line) {
        final List<String> result = new ArrayList<>();
        final Matcher matcher = INSTRUCTION_PATTERN.matcher(line);
        while (matcher.find()) {
            result.add(matcher.group("instruction"));
        }
        return result;
    }

    public Long sumAllMultiplications() {
        return instructions.stream().map(this::executeInstruction).reduce(0L, Long::sum);
    }

    Long executeInstruction(final String instruction) {
        String[] digits = instruction.substring(4, instruction.length() - 1).split(",");
        return Long.parseLong(digits[0]) * Long.parseLong(digits[1]);
    }
}
