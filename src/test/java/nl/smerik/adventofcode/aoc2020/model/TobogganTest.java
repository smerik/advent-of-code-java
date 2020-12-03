package nl.smerik.adventofcode.aoc2020.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

class TobogganTest {

    @Test
    void countTreesUsingSlope() {
        final List<String> input = List.of(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
        );
        final AreaMap areaMap = new AreaMap(input);
        final Toboggan toboggan = new Toboggan(areaMap);

        int result = toboggan.countTreesUsingSlope(3, 1);
        assertEquals(7, result);
    }

    @Test
    void countTreesUsingSlopes() {
        final List<String> input = List.of(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
        );
        final AreaMap areaMap = new AreaMap(input);
        final Toboggan toboggan = new Toboggan(areaMap);

        int result1 = toboggan.countTreesUsingSlope(1, 1);
        int result2 = toboggan.countTreesUsingSlope(3, 1);
        int result3 = toboggan.countTreesUsingSlope(5, 1);
        int result4 = toboggan.countTreesUsingSlope(7, 1);
        int result5 = toboggan.countTreesUsingSlope(1, 2);
        assertEquals(2, result1);
        assertEquals(7, result2);
        assertEquals(3, result3);
        assertEquals(4, result4);
        assertEquals(2, result5);

        long sum = (long) result1 * result2 * result3 * result4 * result5;
        assertEquals(336, sum);
    }
}