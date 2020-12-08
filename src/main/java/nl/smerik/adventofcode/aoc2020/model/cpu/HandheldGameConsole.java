package nl.smerik.adventofcode.aoc2020.model.cpu;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class HandheldGameConsole implements Processor {

    private static final Pattern INSTRUCTION_PATTERN
            = Pattern.compile("(?<operation>[a-z]{3})\\s(?<argument>(\\+|-)\\d+)");

    private final List<String> instructions;
    private final Set<Integer> processedInstructionIndexes;

    private int instructionIndex;
    private int accumulator;

    public HandheldGameConsole(final List<String> instructions) {
        this.instructions = instructions;
        this.processedInstructionIndexes = new HashSet<>();
        this.instructionIndex = 0;
        this.accumulator = 0;
    }

    @Override
    public void run() {
        while (instructionIndex < instructions.size()) {
            final String instruction = instructions.get(instructionIndex);
            if (processedInstructionIndexes.contains(instructionIndex)) {
                throw new IllegalStateException("Infinite loop detected: instruction '" + instruction
                                                        + "' at line " + instructionIndex + 1
                                                        + " has been processed before");
            }
            processedInstructionIndexes.add(instructionIndex);

            final Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Unknown instruction '" + instruction + "' at line "
                                                           + instructionIndex + 1);
            }

            final InstructionSet operation = InstructionSet.valueOfOperation(matcher.group("operation"));
            final int argument = Integer.parseInt(matcher.group("argument"));
            switch (operation) {
                case ACCUMULATOR -> {
                    accumulator += argument;
                    instructionIndex++;
                }
                case JUMP -> instructionIndex += argument;
                case NO_OPERATION -> instructionIndex++;
                default -> throw new IllegalArgumentException("Operation '" + operation + "' not implemented.");
            }
        }
    }
}
