package nl.smerik.adventofcode.aoc2019.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * INTCODE interpreter.
 */
public class IntcodeComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntcodeComputer.class);

    private final Queue<Long> input;

    private final Memory memory;

    private final List<Long> output;

    private int instructionPointer;

    @Getter
    private boolean pausedExecution;

    private int relativeBase = 0;

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
         * Adjusts the relative base by the value of its only parameter.
         */
        ADJUST_RELATIVE_BASE(9),

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
        IMMEDIATE(1),

        /**
         * Behaves very similarly to parameters in position mode: the parameter is interpreted as a position.
         * Like position mode, parameters in relative mode can be read from or written to.
         * <p>
         * The important difference is that relative mode parameters don't count from address 0.
         * Instead, they count from a value called the relative base.
         * The relative base starts at 0.
         * <p>
         * The address a relative mode parameter refers to is itself plus the current relative base.
         * When the relative base is 0,
         * relative mode parameters and position mode parameters with the same value refer to the same address.
         */
        RELATIVE(2);

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

    private class Memory {

        private long[] program;

        public Memory(final long[] program) {
            this.program = Arrays.copyOf(program, program.length);
        }

        public long[] get() {
            return program;
        }

        public long get(final int i) {
            allocate(i);
            return program[i];
        }

        public void set(final int i, final Long value) {
            allocate(i);
            program[i] = value;
        }

        private void allocate(final int i) {
            if (i + 1 > program.length) {
                LOGGER.trace("Index {} exceeds total allocated memory size of {}; Allocating extra memory...",
                        i, program.length);
                this.program = Arrays.copyOf(program, i + 1);
                LOGGER.trace("New allocated memory size: {}", program.length);
            }
        }

        @Override
        public String toString() {
            return "Memory{" +
                    "program=" + Arrays.toString(program) +
                    '}';
        }
    }

    public long[] getMemory() {
        return memory.get();
    }

    public IntcodeComputer(final long[] program) {
        this(program, null);
    }

    public IntcodeComputer(final long[] program, final Integer phase) {
        this.memory = new Memory(program);
        this.instructionPointer = 0;
        this.input = new LinkedList<>();
        if (phase != null) {
            this.input.add(Long.valueOf(phase));
        }
        this.output = new ArrayList<>();
    }

    public List<Long> run() {
        return run(Collections.emptyList());
    }

    public List<Long> run(final Long input) {
        return run(Collections.singletonList(input));
    }

    public List<Long> run(final List<Long> input) {
        this.pausedExecution = false;
        this.input.addAll(input);
        LOGGER.trace("Running input {} on {}", input, memory);
        while (instructionPointer <= memory.get().length && memory.get(instructionPointer) != Opcode.HALT.code && !this.pausedExecution) {
            instructionPointer = runInstruction();
            LOGGER.trace("New instruction pointer location: {}", instructionPointer);
        }
        final List<Long> result = new ArrayList<>(this.output);
        this.output.clear();
        return result;
    }

    private int runInstruction() {
        final Opcode instruction = Opcode.valueOfOpcode((int) memory.get(instructionPointer));
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
            case ADJUST_RELATIVE_BASE:
                return adjustRelativeBase();
            case HALT:
                return 1;
            default:
                return 0;
        }
    }

    private int add() {
        final long parameterOne = getParameterValue(1);
        final long parameterTwo = getParameterValue(2);
        final int storeAddress = getParameterPointer(3);
        memory.set(storeAddress, parameterOne + parameterTwo);
        return instructionPointer + 4;
    }

    private int multiply() {
        final long parameterOne = getParameterValue(1);
        final long parameterTwo = getParameterValue(2);
        final int storeAddress = getParameterPointer(3);
        memory.set(storeAddress, parameterOne * parameterTwo);
        return instructionPointer + 4;
    }

    private int input() {
        LOGGER.trace("Input:{}", input);
        if (input.peek() == null) {
            LOGGER.trace("Pausing computer. Output will be:{}", output);
            this.pausedExecution = true;
            return instructionPointer;
        }
        final int storeAddress = getParameterPointer(1);
        memory.set(storeAddress, input.remove());
        return instructionPointer + 2;
    }

    private int output() {
        output.add(getParameterValue(1));
        LOGGER.trace("Output:{}", output);
        return instructionPointer + 2;
    }

    private int jumpIfTrue() {
        if (getParameterValue(1) == 0) {
            return instructionPointer + 3;
        }
        return (int) getParameterValue(2);
    }

    private int jumpIfFalse() {
        if (getParameterValue(1) == 0) {
            return (int) getParameterValue(2);
        }
        return instructionPointer + 3;
    }

    private int lessThan() {
        final long parameterOne = getParameterValue(1);
        final long parameterTwo = getParameterValue(2);
        final int storeAddress = getParameterPointer(3);
        memory.set(storeAddress, parameterOne < parameterTwo ? 1L : 0);
        return instructionPointer + 4;
    }

    private int equals() {
        final long parameterOne = getParameterValue(1);
        final long parameterTwo = getParameterValue(2);
        final int storeAddress = getParameterPointer(3);
        memory.set(storeAddress, parameterOne == parameterTwo ? 1L : 0);
        return instructionPointer + 4;
    }

    private int adjustRelativeBase() {
        relativeBase += getParameterValue(1);
        LOGGER.trace("Modified relativeBase:{}", relativeBase);
        return instructionPointer + 2;
    }

    private long getParameterValue(final int nthParameter) {
        return memory.get(getParameterPointer(nthParameter));
    }

    private int getParameterPointer(final int nthParameter) {
        final ParameterMode mode = ParameterMode.valueOfParameterMode((int) memory.get(instructionPointer), nthParameter);
        LOGGER.trace("Mode:{}", mode);
        switch (mode) {
            // Parameters that an instruction writes to will never be in immediate mode.
            case IMMEDIATE:
                return instructionPointer + nthParameter;
            case RELATIVE:
                return relativeBase + (int) memory.get(instructionPointer + nthParameter);
            case POSITION:
                return (int) memory.get(instructionPointer + nthParameter);
            default:
                throw new IllegalArgumentException("Unknown mode:" + mode);
        }
    }
}
