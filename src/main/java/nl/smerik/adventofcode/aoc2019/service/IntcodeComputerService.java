package nl.smerik.adventofcode.aoc2019.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * INTCODE interpreter.
 */
@Service
public class IntcodeComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntcodeComputerService.class);


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
            BY_PARAMETER_MODE = Arrays.stream(ParameterMode.values()).collect(Collectors.toMap(mode -> mode.parameterMode, mode -> mode));
        }

        private final int parameterMode;

        ParameterMode(final int parameterMode) {
            this.parameterMode = parameterMode;
        }

        public static ParameterMode valueOfParameterMode(final int opcode, final int digit) {
            return BY_PARAMETER_MODE.get(opcode / (int) Math.pow(10, digit + 2 - 1) % 10);
        }
    }

    public int[] solvePart1(int[] codes) {
        int instructionPointer = 0;
        while (instructionPointer <= codes.length && codes[instructionPointer] != Opcode.HALT.code) {
            final int numberOfInstructionValues = runInstruction(instructionPointer, codes);
            instructionPointer += numberOfInstructionValues;
        }
        return codes;
    }

    public String solvePart2(int[] memory, int requiredOutput) {
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                int[] memoryCopy = Arrays.copyOf(memory, memory.length);
                memoryCopy[1] = noun;
                memoryCopy[2] = verb;
                solvePart1(memoryCopy);
                if (memoryCopy[0] == requiredOutput) {
                    return answer(noun, verb);
                }
            }
        }
        return "???";
    }

    public int[] solveWithInput(int[] codes, int input) {
        int instructionPointer = 0;
        while (instructionPointer <= codes.length && codes[instructionPointer] != Opcode.HALT.code) {
            final int numberOfInstructionValues = runInstruction(instructionPointer, codes, input);
            instructionPointer += numberOfInstructionValues;
        }
        return codes;
    }

    private int runInstruction(final int instructionPointer, final int[] memory) {
        return runInstruction(instructionPointer, memory, 0);
    }

    private int runInstruction(final int instructionPointer, final int[] memory, final int input) {
        final Opcode instruction = Opcode.valueOfOpcode(memory[instructionPointer]);
        LOGGER.info("Instruction:{}", instruction);

        switch (instruction) {
            case ADD:
                return add(instructionPointer, memory);
            case MULTIPLY:
                return multiply(instructionPointer, memory);
            case INPUT:
                return input(instructionPointer, memory, input);
            case OUTPUT:
                return output(instructionPointer, memory);
            case HALT:
                return 1;
            default:
                return 0;
        }
    }

    private int add(final int instructionPointer, final int[] memory) {
        final int inputAddress1 = getParameterValue(instructionPointer, 1, memory);
        final int inputAddress2 = getParameterValue(instructionPointer, 2, memory);
        int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = inputAddress1 + inputAddress2;
        return 4;
    }

    private int multiply(final int instructionPointer, final int[] memory) {
        final int inputAddress1 = getParameterValue(instructionPointer, 1, memory);
        final int inputAddress2 = getParameterValue(instructionPointer, 2, memory);
        int storeAddress = memory[instructionPointer + 3];
        memory[storeAddress] = inputAddress1 * inputAddress2;
        return 4;
    }

    private int input(final int instructionPointer, final int[] memory, final int input) {
        memory[memory[instructionPointer + 1]] = input;
        return 2;
    }

    private int output(final int instructionPointer, final int[] memory) {
        LOGGER.info("Output:{}", memory[memory[instructionPointer + 1]]);
        return 2;
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

    private String answer(final int noun, final int verb) {
        return String.format("%02d%02d", noun, verb);
    }
}
