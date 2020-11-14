package nl.smerik.adventofcode.aoc2019.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class IntcodeComputerTest {

    private static IntcodeComputerService day02;

    @BeforeAll
    public static void init() {
        day02 = new IntcodeComputerService();
    }

    @Test
    void solve1() {
        int[] program = new int[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}, resolution);
    }

    @Test
    void solve2() {
        int[] program = new int[]{1, 0, 0, 0, 99};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{2, 0, 0, 0, 99}, resolution);
    }

    @Test
    void solve3() {
        int[] program = new int[]{2, 3, 0, 3, 99};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{2, 3, 0, 6, 99}, resolution);
    }

    @Test
    void solve4() {
        int[] program = new int[]{2, 4, 4, 5, 99, 0};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{2, 4, 4, 5, 99, 9801}, resolution);
    }

    @Test
    void solve5() {
        int[] program = new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, resolution);
    }

    @Test
    void solveDay5Part01Test01() {
        int[] program = new int[]{3, 0, 4, 0, 99};
        int[] resolution = day02.solveWithInput(program, 456);
        Assertions.assertArrayEquals(new int[]{456, 0, 4, 0, 99}, resolution);
    }

    @Test
    void solveDay5Part01Test02() {
        int[] program = new int[]{1002, 4, 3, 4, 33};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{1002, 4, 3, 4, 99}, resolution);
    }

    @Test
    void solveDay5Part01Test03() {
        int[] program = new int[]{1101, 100, -1, 4, 0};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{1101, 100, -1, 4, 99}, resolution);
    }

    /**
     * Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay5Part02Test01() {
        int[] program = new int[]{3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8};

        int[] output1 = day02.solveWithInput(Arrays.copyOf(program, program.length), 8);
        Assertions.assertArrayEquals(new int[]{3, 9, 8, 9, 10, 9, 4, 9, 99, 1, 8}, output1);

        int[] output0 = day02.solveWithInput(Arrays.copyOf(program, program.length), 7);
        Assertions.assertArrayEquals(new int[]{3, 9, 8, 9, 10, 9, 4, 9, 99, 0, 8}, output0);
    }

    /**
     * Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay5Part02Test02() {
        int[] program = new int[]{3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8};

        int[] output1 = day02.solveWithInput(Arrays.copyOf(program, program.length), 7);
        Assertions.assertArrayEquals(new int[]{3, 9, 7, 9, 10, 9, 4, 9, 99, 1, 8}, output1);

        int[] output0 = day02.solveWithInput(Arrays.copyOf(program, program.length), 8);
        Assertions.assertArrayEquals(new int[]{3, 9, 7, 9, 10, 9, 4, 9, 99, 0, 8}, output0);
    }

    /**
     * Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay5Part02Test03() {
        int[] program = new int[]{3, 3, 1108, -1, 8, 3, 4, 3, 99};

        int[] output1 = day02.solveWithInput(Arrays.copyOf(program, program.length), 8);
        Assertions.assertArrayEquals(new int[]{3, 3, 1108, 1, 8, 3, 4, 3, 99}, output1);

        int[] output0 = day02.solveWithInput(Arrays.copyOf(program, program.length), 7);
        Assertions.assertArrayEquals(new int[]{3, 3, 1108, 0, 8, 3, 4, 3, 99}, output0);
    }

    /**
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay5Part02Test04() {
        int[] program = new int[]{3, 3, 1107, -1, 8, 3, 4, 3, 99};

        int[] output1 = day02.solveWithInput(Arrays.copyOf(program, program.length), 7);
        Assertions.assertArrayEquals(new int[]{3, 3, 1107, 1, 8, 3, 4, 3, 99}, output1);

        int[] output0 = day02.solveWithInput(Arrays.copyOf(program, program.length), 8);
        Assertions.assertArrayEquals(new int[]{3, 3, 1107, 0, 8, 3, 4, 3, 99}, output0);
    }

    /**
     * Using position mode, consider whether the input is 0; output 0 (if it is) or 1 (if it is not).
     */
    @Test
    void solveDay5Part02Test05() {
        int[] program = new int[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9};

        int[] output1 = day02.solveWithInput(Arrays.copyOf(program, program.length), 1);
        Assertions.assertArrayEquals(new int[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, 1, 1, 1, 9}, output1);

        int[] output0 = day02.solveWithInput(Arrays.copyOf(program, program.length), 0);
        Assertions.assertArrayEquals(new int[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, 0, 0, 1, 9}, output0);
    }

    /**
     * Using immediate mode, consider whether the input is 0; output 0 (if it is) or 1 (if it is not).
     */
    @Test
    void solveDay5Part02Test06() {
        int[] program = new int[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1};

        int[] output1 = day02.solveWithInput(Arrays.copyOf(program, program.length), 1);
        Assertions.assertArrayEquals(new int[]{3, 3, 1105, 1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}, output1);

        int[] output0 = day02.solveWithInput(Arrays.copyOf(program, program.length), 0);
        Assertions.assertArrayEquals(new int[]{3, 3, 1105, 0, 9, 1101, 0, 0, 12, 4, 12, 99, 0}, output0);
    }

    /**
     * The example program uses an input instruction to ask for a single number.
     * The program will then output 999 if the input value is below 8,
     * output 1000 if the input value is equal to 8,
     * or output 1001 if the input value is greater than 8.
     */
    @Test
    void solveDay5Part02Test07() {
        int[] program = new int[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        };

        int[] output999 = day02.solveWithInput(Arrays.copyOf(program, program.length), 7);
        Assertions.assertArrayEquals(new int[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 7, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        }, output999);

        int[] output1000 = day02.solveWithInput(Arrays.copyOf(program, program.length), 8);
        Assertions.assertArrayEquals(new int[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 1000, 8, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        }, output1000);

        int[] output1001 = day02.solveWithInput(Arrays.copyOf(program, program.length), 9);
        Assertions.assertArrayEquals(new int[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 1001, 9, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        }, output1001);
    }
}