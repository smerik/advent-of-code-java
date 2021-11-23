package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day11ServiceTest {

    @Autowired
    private Day11Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(1964, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        // @formatter:off
        final String expectedResult =
                ".####.#..#.####.#..#..##..####.###..#..#..." + System.lineSeparator() +
                ".#....#.#..#....#.#..#..#.#....#..#.#.#...." + System.lineSeparator() +
                ".###..##...###..##...#....###..#..#.##....." + System.lineSeparator() +
                ".#....#.#..#....#.#..#....#....###..#.#...." + System.lineSeparator() +
                ".#....#.#..#....#.#..#..#.#....#.#..#.#...." + System.lineSeparator() +
                ".#....#..#.####.#..#..##..#....#..#.#..#..." + System.lineSeparator();
        // @formatter:on
        // Expected result should read: FKEKCFRK
        assertEquals(expectedResult, dayService.getSolutionPart2());
    }
}
