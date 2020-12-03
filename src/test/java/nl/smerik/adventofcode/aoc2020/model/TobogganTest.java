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
}