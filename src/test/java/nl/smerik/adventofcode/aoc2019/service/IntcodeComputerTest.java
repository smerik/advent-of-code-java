package nl.smerik.adventofcode.aoc2019.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    void solveDay5Test01() {
        int[] program = new int[]{3, 0, 4, 0, 99};
        int[] resolution = day02.solveWithInput(program, 456);
        Assertions.assertArrayEquals(new int[]{456, 0, 4, 0, 99}, resolution);
    }

    @Test
    void solveDay5Test02() {
        int[] program = new int[]{1002, 4, 3, 4, 33};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{1002, 4, 3, 4, 99}, resolution);
    }

    @Test
    void solveDay5Test03() {
        int[] program = new int[]{1101, 100, -1, 4, 0};
        int[] resolution = day02.solvePart1(program);
        Assertions.assertArrayEquals(new int[]{1101, 100, -1, 4, 99}, resolution);
    }
}