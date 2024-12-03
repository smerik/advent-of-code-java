package nl.smerik.adventofcode.aoc2024.model.computer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Calculator {

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(?<instruction>mul\\(\\d{1,3},\\d{1,3}\\))");

    private final List<String> instructions;

    public Calculator(final List<String> lines, boolean handleConditionalStatements) {
        instructions = parseLines(lines, handleConditionalStatements);
    }

    private List<String> parseLines(final List<String> lines, boolean handleConditionalStatements) {
        final List<String> linesToExecute = handleConditionalStatements ? removeDisabledInstructions(lines) : lines;
        final List<String> result = new ArrayList<>();
        for (final String line : linesToExecute) {
            result.addAll(parseLine(line));
        }
        return result;
    }

    private List<String> removeDisabledInstructions(final List<String> lines) {
        final StringBuilder lineBuilder = new StringBuilder();
        for (String line : lines) {
            lineBuilder.append(line);
        }
        String result = lineBuilder.toString().replaceAll("don't\\(\\).*?do\\(\\)", "");
        final int dontIndex = result.indexOf("don't()");
        if (dontIndex != -1) {
            result = result.substring(0, dontIndex);
        }
        return Collections.singletonList(result);
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
