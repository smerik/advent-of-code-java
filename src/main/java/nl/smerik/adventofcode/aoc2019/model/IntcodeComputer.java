package nl.smerik.adventofcode.aoc2019.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * INTCODE interpreter.
 */
public class IntcodeComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntcodeComputer.class);

    @Getter
    private final int[] memory;

    @Getter
    private int output = -1;

    /**
     * Opcodes mark the beginning of an instruction.
     */
    private enum Opcode {

        /**
         * Reads from two positions and stores the result in a third position.
         */
        ADD(1),

        /**
         * Reads from two positions and stores the result in a third position.
         */
        MULTIPLY(2),

        /**
         * Takes a single integer as input & saves it to the position of the input parameter.
         */
        INPUT(3),

        /**
         * Outputs the value of its only parameter.
         */
        OUTPUT(4),

        /**
         * If the first parameter is non-zero, it sets the instruction pointer to the value from the second parameter.
         * Otherwise, it does nothing
         */
        JUMP_IF_TRUE(5),

        /**
         * If the first parameter is zero, it sets the instruction pointer to the value from the second parameter.
         * Otherwise, it does nothing.
         */
        JUMP_IF_FALSE(6),

        /**
         * If the first parameter is less than the second parameter,
         * it stores 1 in the position given by the third parameter.
         * Otherwise, it stores 0.
         */
        LESS_THAN(7),

        /**
         * If the first parameter is equal to the second parameter,
         * it stores 1 in the position given by the third parameter.
         * Otherwise, it stores 0.
         */
        EQUALS(8),

        /**
         * Halts the program.
         */
        HALT(99);

        private static final Map<Integer, Opcode> BY_OPCODE;

        static {
            BY_OPCODE = Arrays.stream(Opcode.values()).collect(Collectors.toMap(opcode -> opcode.code, opcode -> opcode));
        }

        private final int code;

        Opcode(final int code) {
            this.code = code;
        }

        public static Opcode valueOfOpcode(int opcode) {
            return BY_OPCODE.get(opcode % 100);
        }
    }

    /**
     * Instruction parameters.
     */
    private enum ParameterMode {

        /**
         * The parameter should be interpreted as a position.
         */
        POSITION(0),

        /**
         * The parameter should be interpreted as a value.
         */
        IMMEDIATE(1);


        private static final Map<Integer, ParameterMode> BY_PARAMETER_MODE;

        static {
            BY_PARAMETER_MODE = Arrays.stream(ParameterMode.values()).collect(Collectors.toMap(mode -> mode.mode, mode -> mode));
        }

        private final int mode;

        ParameterMode(final int mode) {
            this.mode = mode;
        }

        public static ParameterMode valueOfParameterMode(final int opcode, final int digit) {
            return BY_PARAMETER_MODE.get(opcode / (int) Math.pow(10, digit + 2 - 1) % 10);
        }
    }

    public IntcodeComputer(int[] program) {
        this.memory = Arrays.copyOf(program, program.length);
    }

    public int[] run() {
        int instructionPointer = 0;
        while (instructionPointer <= memory.length && memory[instructionPointer] != Opcode.HALT.code) {
            instructionPointer = runInstruction(instructionPointer, memory);
        }
        return memory;
    }

    public int[] runWithInput(final int input) {
        int instructionPointer = 0;
        while (instructionPointer <= memory.length && memory[instructionPointer] != Opcode.HALT.code) {
            instructionPointer = runInstruction(instructionPointer, memory, input);
        }
        return memory;
    }

    private int runInstruction(final int instructionPointer, final int[] memory) {
        return runInstruction(instructionPointer, memory, 0);
    }

    private int runInstruction(final int instructionPointer, final int[] memory, final int input) {
        final Opcode instruction = Opcode.valueOfOpcode(memory[instructionPointer]);
        LOGGER.debug("Instruction:{}", instruction);

        switch (instruction) {
            case ADD:
                return add(instructionPointer, memory);
            case MULTIPLY:
                return multiply(instructionPointer, memory);
            case INPUT:
                return input(instructionPointer, memory, input);
            case OUTPUT:
                return output(instructionPointer, memory);
            case JUMP_IF_TRUE:
                return jumpIfTrue(instructionPointer, memory);
            case JUMP_IF_FALSE:
                return jumpIfFalse(instructionPointer, memory);
            case LESS_THAN:
                return lessThan(instructionPointer, memory);
            case EQUALS:
                return equals(instructionPointer, memory);
            case HALT:
                return 1;
            default:
                return 0;
        }
    }

    private int add(final int instructionPointer, final int[] memory) {
        final int inputAddress1 = getParameterValue(instructionPointer, 1, memory);
        final int inputAddress2 = getParameterValue(instructionPointer, 2, memory);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = inputAddress1 + inputAddress2;
        return instructionPointer + 4;
    }

    private int multiply(final int instructionPointer, final int[] memory) {
        final int inputAddress1 = getParameterValue(instructionPointer, 1, memory);
        final int inputAddress2 = getParameterValue(instructionPointer, 2, memory);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = inputAddress1 * inputAddress2;
        return instructionPointer + 4;
    }

    private int input(final int instructionPointer, final int[] memory, final int input) {
        memory[memory[instructionPointer + 1]] = input;
        return instructionPointer + 2;
    }

    private int output(final int instructionPointer, final int[] memory) {
        output = getParameterValue(instructionPointer, 1, memory);
        LOGGER.debug("Output:{}", output);
        return instructionPointer + 2;
    }

    private int jumpIfTrue(final int instructionPointer, final int[] memory) {
        if (getParameterValue(instructionPointer, 1, memory) == 0) {
            return instructionPointer + 3;
        }
        return getParameterValue(instructionPointer, 2, memory);
    }

    private int jumpIfFalse(final int instructionPointer, final int[] memory) {
        if (getParameterValue(instructionPointer, 1, memory) == 0) {
            return getParameterValue(instructionPointer, 2, memory);
        }
        return instructionPointer + 3;
    }

    private int lessThan(final int instructionPointer, final int[] memory) {
        final int parameterOne = getParameterValue(instructionPointer, 1, memory);
        final int parameterTwo = getParameterValue(instructionPointer, 2, memory);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = parameterOne < parameterTwo ? 1 : 0;
        return instructionPointer + 4;
    }

    private int equals(final int instructionPointer, final int[] memory) {
        final int parameterOne = getParameterValue(instructionPointer, 1, memory);
        final int parameterTwo = getParameterValue(instructionPointer, 2, memory);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = parameterOne == parameterTwo ? 1 : 0;
        return instructionPointer + 4;
    }

    private int getParameterValue(final int instructionPointer, final int nthParameter, final int[] memory) {
        final ParameterMode mode = ParameterMode.valueOfParameterMode(memory[instructionPointer], nthParameter);
        LOGGER.debug("Mode:{}", mode);
        switch (mode) {
            case IMMEDIATE:
                return memory[instructionPointer + nthParameter];
            case POSITION:
            default:
                return memory[memory[instructionPointer + nthParameter]];
        }
    }
}
