package nl.smerik.adventofcode.aoc2019.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * INTCODE interpreter.
 */
public class IntcodeComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntcodeComputer.class);

    private final Queue<Integer> input;

    @Getter
    private final int[] memory;

    private int instructionPointer;

    @Getter
    private boolean pausedExecution;

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

    public IntcodeComputer(final int[] program) {
        this(program, null);
    }

    public IntcodeComputer(final int[] program, final Integer phase) {
        this.memory = Arrays.copyOf(program, program.length);
        this.instructionPointer = 0;
        this.input = new LinkedList<>();
        if (phase != null) {
            this.input.add(phase);
        }
    }

    public int run(final Integer... input) {
        this.pausedExecution = false;
        this.input.addAll(Arrays.asList(input));
        LOGGER.trace("Running input {} on program:{}", input, memory);
        while (instructionPointer <= memory.length && memory[instructionPointer] != Opcode.HALT.code && !this.pausedExecution) {
            instructionPointer = runInstruction();
        }
        return output;
    }

    private int runInstruction() {
        final Opcode instruction = Opcode.valueOfOpcode(memory[instructionPointer]);
        LOGGER.trace("Instruction:{}", instruction);

        switch (instruction) {
            case ADD:
                return add();
            case MULTIPLY:
                return multiply();
            case INPUT:
                return input();
            case OUTPUT:
                return output();
            case JUMP_IF_TRUE:
                return jumpIfTrue();
            case JUMP_IF_FALSE:
                return jumpIfFalse();
            case LESS_THAN:
                return lessThan();
            case EQUALS:
                return equals();
            case HALT:
                return 1;
            default:
                return 0;
        }
    }

    private int add() {
        final int inputAddress1 = getParameterValue(1);
        final int inputAddress2 = getParameterValue(2);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = inputAddress1 + inputAddress2;
        return instructionPointer + 4;
    }

    private int multiply() {
        final int inputAddress1 = getParameterValue(1);
        final int inputAddress2 = getParameterValue(2);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = inputAddress1 * inputAddress2;
        return instructionPointer + 4;
    }

    private int input() {
        LOGGER.trace("Input:{}", input.peek());
        if (input.peek() == null) {
            LOGGER.debug("Pausing computer. Output will be:{}", output);
            this.pausedExecution = true;
            return instructionPointer;
        }
        memory[memory[instructionPointer + 1]] = input.remove();
        return instructionPointer + 2;
    }

    private int output() {
        output = getParameterValue(1);
        LOGGER.trace("Output:{}", output);
        return instructionPointer + 2;
    }

    private int jumpIfTrue() {
        if (getParameterValue(1) == 0) {
            return instructionPointer + 3;
        }
        return getParameterValue(2);
    }

    private int jumpIfFalse() {
        if (getParameterValue(1) == 0) {
            return getParameterValue(2);
        }
        return instructionPointer + 3;
    }

    private int lessThan() {
        final int parameterOne = getParameterValue(1);
        final int parameterTwo = getParameterValue(2);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = parameterOne < parameterTwo ? 1 : 0;
        return instructionPointer + 4;
    }

    private int equals() {
        final int parameterOne = getParameterValue(1);
        final int parameterTwo = getParameterValue(2);
        final int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = parameterOne == parameterTwo ? 1 : 0;
        return instructionPointer + 4;
    }

    private int getParameterValue(final int nthParameter) {
        final ParameterMode mode = ParameterMode.valueOfParameterMode(memory[instructionPointer], nthParameter);
        LOGGER.trace("Mode:{}", mode);
        switch (mode) {
            case IMMEDIATE:
                return memory[instructionPointer + nthParameter];
            case POSITION:
            default:
                return memory[memory[instructionPointer + nthParameter]];
        }
    }
}
