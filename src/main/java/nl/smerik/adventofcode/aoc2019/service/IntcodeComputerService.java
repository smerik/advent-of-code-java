package nl.smerik.adventofcode.aoc2019.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * INTCODE interpreter.
 */
@Service
public class IntcodeComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntcodeComputerService.class);

    private static int OPCODE_ADD = 1;
    private static int OPCODE_MULTIPLY = 2;
    private static int OPCODE_THREE = 3; // takes single integer as input & saves it to the position of the input parameter
    private static int OPCODE_FOUR = 4; // outputs the value of its only parameter
    private static int OPCODE_HALT = 99;

    private static int PARAMETER_MODE_POSITION = 0;
    private static int PARAMETER_MODE_IMMEDIATE = 1;

    public int[] solvePart1(int[] codes) {
        int instructionPointer = 0;
        while (instructionPointer <= codes.length && codes[instructionPointer] != OPCODE_HALT) {
            runProgram(instructionPointer, codes);
            instructionPointer = instructionPointer + 4;
        }
        return codes;
    }

    private void runProgram(final int address, final int[] memory) {
        int inputAddress1 = memory[address + 1];
        int inputAddress2 = memory[address + 2];
        int storeAddress = memory[address + 3];

        if (memory[address] == OPCODE_ADD) {
            memory[storeAddress] = memory[inputAddress1] + memory[inputAddress2];
        } else if (memory[address] == OPCODE_MULTIPLY) {
            memory[storeAddress] = memory[inputAddress1] * memory[inputAddress2];
        }
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

//    private int executeInstruction(final int opcode, final int... instructionParameters) {
//        if (opcode == OPCODE_ADD) {
//            memory[storeAddress] = memory[inputAddress] + memory[inputPos2];
//        } else if (opcode == OPCODE_MULTIPLY) {
//            memory[storeAddress] = memory[inputAddress] * memory[inputPos2];
//        } else if (opcode == OPCODE_HALT) {
//
//        }
//        return 1 + instructionParameters.length;
//    }

    private String answer(final int noun, final int verb) {
        return String.format("%02d%02d", noun, verb);
    }
}
