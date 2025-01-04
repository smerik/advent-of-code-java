package nl.smerik.adventofcode.aoc2024.model.computer.chronospatial;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChronospatialComputer {

    private static final Pattern REGISTER_PATTERN = Pattern.compile("^Register (?<name>[A-C]): (?<value>\\d+)$");

    private final Map<Register, Integer> registerByName;
    private final int[] program;
    private final List<Integer> output;

    public ChronospatialComputer(final List<String> lines) {
        final Iterator<String> iterator = lines.iterator();
        this.registerByName = parseRegisters(iterator);
        this.program = parseProgram(iterator);
        this.output = new ArrayList<>();
    }

    private Map<Register, Integer> parseRegisters(final Iterator<String> iterator) {
        final Map<Register, Integer> result = new EnumMap<>(Register.class);
        String line;
        while (iterator.hasNext() && !(line = iterator.next()).isBlank()) {
            final Matcher matcher = REGISTER_PATTERN.matcher(line);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Invalid register format: " + line);
            }
            final Register name = Register.valueOf(matcher.group("name"));
            final int value = Integer.parseInt(matcher.group("value"));
            result.put(name, value);
        }
        return result;
    }

    private int[] parseProgram(final Iterator<String> iterator) {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("No program found!");
        }
        return Arrays.stream(iterator.next().split(": ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public String runProgram() {
        int instructionPointer = 0;
        while (instructionPointer < program.length) {
            instructionPointer = run(instructionPointer);
        }
        return output.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    /**
     * Runs the program part at the instruction pointer.
     *
     * @param instructionPointer the instruction pointer to run
     * @return the new location of the instruction pointer
     */
    private int run(final int instructionPointer) {
        int result = instructionPointer + 2;
        final int literalOperand = program[instructionPointer + 1];
        final int comboOperand = determineComboOperand(literalOperand);
        switch (Instruction.valueOfOpcode(program[instructionPointer])) {
            case ADV -> divide(Register.A, comboOperand);
            case BDV -> divide(Register.B, comboOperand);
            case CDV -> divide(Register.C, comboOperand);
            case BXL -> calcBitwiseXOR(registerByName.get(Register.B), literalOperand);
            case BXC -> calcBitwiseXOR(registerByName.get(Register.B), registerByName.get(Register.C));
            case BST -> calcBST(comboOperand);
            case JNZ -> result = registerByName.get(Register.A) == 0 ? result : literalOperand;
            case OUT -> output(comboOperand);
        }
        return result;
    }

    private void divide(final Register writeToRegister, final int comboOperand) {
        final int numerator = registerByName.get(Register.A);
        final int denominator = (int) Math.pow(2, comboOperand);
        registerByName.put(writeToRegister, numerator / denominator);
    }

    private void calcBitwiseXOR(final int a, final int b) {
        registerByName.put(Register.B, a ^ b);
    }

    private void calcBST(final int comboOperand) {
        registerByName.put(Register.B, comboOperand % 8);
    }

    private void output(final int comboOperand) {
        output.add(comboOperand % 8);
    }

    private int determineComboOperand(final int operand) {
        return switch (operand) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> registerByName.get(Register.A);
            case 5 -> registerByName.get(Register.B);
            case 6 -> registerByName.get(Register.C);
            case 7 -> throw new IllegalStateException("Program is invalid");
            default -> throw new IllegalArgumentException("Invalid combo operand: " + operand);
        };
    }
}
