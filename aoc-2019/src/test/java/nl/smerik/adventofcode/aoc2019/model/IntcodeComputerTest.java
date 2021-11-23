package nl.smerik.adventofcode.aoc2019.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class IntcodeComputerTest {

    @Test
    void solveDay02Part01Test01() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50});
        computer.run();
        Assertions.assertArrayEquals(new long[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}, computer.getMemory());
    }

    @Test
    void solveDay02Part01Test02() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{1, 0, 0, 0, 99});
        computer.run();
        Assertions.assertArrayEquals(new long[]{2, 0, 0, 0, 99}, computer.getMemory());
    }

    @Test
    void solveDay02Part01Test03() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{2, 3, 0, 3, 99});
        computer.run();
        Assertions.assertArrayEquals(new long[]{2, 3, 0, 6, 99}, computer.getMemory());
    }

    @Test
    void solveDay02Part01Test04() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{2, 4, 4, 5, 99, 0});
        computer.run();
        Assertions.assertArrayEquals(new long[]{2, 4, 4, 5, 99, 9801}, computer.getMemory());
    }

    @Test
    void solveDay02Part01Test05() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{1, 1, 1, 4, 99, 5, 6, 0, 99});
        computer.run();
        Assertions.assertArrayEquals(new long[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, computer.getMemory());
    }

    @Test
    void solveDay05Part01Test01() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{3, 0, 4, 0, 99});
        final List<Long> result = computer.run(456L);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(456,result.get(0));
        Assertions.assertArrayEquals(new long[]{456, 0, 4, 0, 99}, computer.getMemory());
    }

    @Test
    void solveDay05Part01Test02() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{1002, 4, 3, 4, 33});
        computer.run();
        Assertions.assertArrayEquals(new long[]{1002, 4, 3, 4, 99}, computer.getMemory());
    }

    @Test
    void solveDay05Part01Test03() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{1101, 100, -1, 4, 0});
        computer.run();
        Assertions.assertArrayEquals(new long[]{1101, 100, -1, 4, 99}, computer.getMemory());
    }

    /**
     * Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay05Part02Test01() {
        final IntcodeComputer computer1 = new IntcodeComputer(new long[]{3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8});
        final List<Long> result1 = computer1.run(8L);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertEquals(1, result1.get(0));
        Assertions.assertArrayEquals(new long[]{3, 9, 8, 9, 10, 9, 4, 9, 99, 1, 8}, computer1.getMemory());

        final IntcodeComputer computer0 = new IntcodeComputer(new long[]{3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8});
        final List<Long> result0 = computer0.run(7L);
        Assertions.assertEquals(1, result0.size());
        Assertions.assertEquals(0, result0.get(0));
        Assertions.assertArrayEquals(new long[]{3, 9, 8, 9, 10, 9, 4, 9, 99, 0, 8}, computer0.getMemory());
    }

    /**
     * Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay05Part02Test02() {
        final IntcodeComputer computer1 = new IntcodeComputer(new long[]{3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8});
        final List<Long> result1 = computer1.run(7L);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertEquals(1, result1.get(0));
        Assertions.assertArrayEquals(new long[]{3, 9, 7, 9, 10, 9, 4, 9, 99, 1, 8}, computer1.getMemory());

        final IntcodeComputer computer0 = new IntcodeComputer(new long[]{3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8});
        final List<Long> result0 = computer0.run(8L);
        Assertions.assertEquals(1, result0.size());
        Assertions.assertEquals(0, result0.get(0));
        Assertions.assertArrayEquals(new long[]{3, 9, 7, 9, 10, 9, 4, 9, 99, 0, 8}, computer0.getMemory());
    }

    /**
     * Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay05Part02Test03() {
        final IntcodeComputer computer1 = new IntcodeComputer(new long[]{3, 3, 1108, -1, 8, 3, 4, 3, 99});
        final List<Long> result1 = computer1.run(8L);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertEquals(1, result1.get(0));
        Assertions.assertArrayEquals(new long[]{3, 3, 1108, 1, 8, 3, 4, 3, 99}, computer1.getMemory());

        final IntcodeComputer computer0 = new IntcodeComputer(new long[]{3, 3, 1108, -1, 8, 3, 4, 3, 99});
        final List<Long> result0 = computer0.run(7L);
        Assertions.assertEquals(1, result0.size());
        Assertions.assertEquals(0, result0.get(0));
        Assertions.assertArrayEquals(new long[]{3, 3, 1108, 0, 8, 3, 4, 3, 99}, computer0.getMemory());
    }

    /**
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    void solveDay05Part02Test04() {
        final IntcodeComputer computer1 = new IntcodeComputer(new long[]{3, 3, 1107, -1, 8, 3, 4, 3, 99});
        final List<Long> result1 = computer1.run(7L);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertEquals(1, result1.get(0));
        Assertions.assertArrayEquals(new long[]{3, 3, 1107, 1, 8, 3, 4, 3, 99}, computer1.getMemory());

        final IntcodeComputer computer0 = new IntcodeComputer(new long[]{3, 3, 1107, -1, 8, 3, 4, 3, 99});
        final List<Long> result0 = computer0.run(8L);
        Assertions.assertEquals(1, result0.size());
        Assertions.assertEquals(0, result0.get(0));
        Assertions.assertArrayEquals(new long[]{3, 3, 1107, 0, 8, 3, 4, 3, 99}, computer0.getMemory());
    }

    /**
     * Using position mode, consider whether the input is 0; output 0 (if it is) or 1 (if it is not).
     */
    @Test
    void solveDay05Part02Test05() {
        final IntcodeComputer computer1 = new IntcodeComputer(new long[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9});
        final List<Long> result1 = computer1.run(1L);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertEquals(1, result1.get(0));
        Assertions.assertArrayEquals(new long[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, 1, 1, 1, 9}, computer1.getMemory());

        final IntcodeComputer computer0 = new IntcodeComputer(new long[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9});
        final List<Long> result0 = computer0.run(0L);
        Assertions.assertEquals(1, result0.size());
        Assertions.assertEquals(0, result0.get(0));
        Assertions.assertArrayEquals(new long[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, 0, 0, 1, 9}, computer0.getMemory());
    }

    /**
     * Using immediate mode, consider whether the input is 0; output 0 (if it is) or 1 (if it is not).
     */
    @Test
    void solveDay05Part02Test06() {
        final IntcodeComputer computer1 = new IntcodeComputer(new long[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1});
        final List<Long> result1 = computer1.run(1L);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertEquals(1, result1.get(0));
        Assertions.assertArrayEquals(new long[]{3, 3, 1105, 1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}, computer1.getMemory());

        final IntcodeComputer computer0 = new IntcodeComputer(new long[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1});
        final List<Long> result0 = computer0.run(0L);
        Assertions.assertEquals(1, result0.size());
        Assertions.assertEquals(0, result0.get(0));
        Assertions.assertArrayEquals(new long[]{3, 3, 1105, 0, 9, 1101, 0, 0, 12, 4, 12, 99, 0}, computer0.getMemory());
    }

    /**
     * The example program uses an input instruction to ask for a single number.
     * The program will then output 999 if the input value is below 8,
     * output 1000 if the input value is equal to 8,
     * or output 1001 if the input value is greater than 8.
     */
    @Test
    void solveDay05Part02Test07() {
        final IntcodeComputer computer999 = new IntcodeComputer(new long[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        });
        final List<Long> result999 = computer999.run(7L);
        Assertions.assertEquals(1, result999.size());
        Assertions.assertEquals(999, result999.get(0));
        Assertions.assertArrayEquals(new long[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 7, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        }, computer999.getMemory());

        final IntcodeComputer computer1000 = new IntcodeComputer(new long[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        });
        final List<Long> result1000 = computer1000.run(8L);
        Assertions.assertEquals(1, result1000.size());
        Assertions.assertEquals(1000, result1000.get(0));
        Assertions.assertArrayEquals(new long[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 1000, 8, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        }, computer1000.getMemory());

        final IntcodeComputer computer1001 = new IntcodeComputer(new long[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        });
        final List<Long> result1001 = computer1001.run(9L);
        Assertions.assertEquals(1, result1001.size());
        Assertions.assertEquals(1001, result1001.get(0));
        Assertions.assertArrayEquals(new long[]{
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 1001, 9, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        }, computer1001.getMemory());
    }

    @Test
    void solveDay09Part01Test01() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99});
        final List<Long> result = computer.run();
        Assertions.assertEquals(List.of(109L, 1L, 204L, -1L, 1001L, 100L, 1L, 100L, 1008L, 100L, 16L, 101L, 1006L, 101L, 0L, 99L), result);
    }

    @Test
    void solveDay09Part01Test02() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{1102, 34915192, 34915192, 7, 4, 7, 99, 0});
        final List<Long> result = computer.run();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(16, String.valueOf(result.get(0)).length());
        Assertions.assertEquals(1219070632396864L, result.get(0));
    }

    @Test
    void solveDay09Part01Test03() {
        final IntcodeComputer computer = new IntcodeComputer(new long[]{104, 1125899906842624L, 99});
        final List<Long> result = computer.run();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1125899906842624L, result.get(0));
    }
}