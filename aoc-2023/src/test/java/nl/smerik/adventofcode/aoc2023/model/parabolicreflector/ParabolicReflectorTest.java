package nl.smerik.adventofcode.aoc2023.model.parabolicreflector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParabolicReflectorTest {

    private static final List<String> INPUT_EXAMPLE_01 = List.of(
            // @formatter:off
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#...."
            // @formatter:on
    );

    @Test
    void testSumTotalLoad() {
        // Given
        final ParabolicReflector reflector = new ParabolicReflector(INPUT_EXAMPLE_01);
        // When
        reflector.tiltLever();
        // Then
        assertEquals(136, reflector.sumTotalLoad());
    }

    @ParameterizedTest
    @CsvSource({
            "50,0",
            "18,1",
            "32,2",
            "21,3",
            " 0,4",
            " 0,5",
            "12,6",
            " 3,7",
            " 0,8",
            " 0,9"
    })
    void testCalcLoad(final int expectedResult, final int row) {
        final ParabolicReflector reflector = new ParabolicReflector(INPUT_EXAMPLE_01);
        reflector.tiltLever();
        assertEquals(expectedResult, reflector.calcLoad(row));
    }

    @Test
    void testTiltLever() {
        final String expectedResult =
                // @formatter:off
                "OOOO.#.O.." + System.lineSeparator() +
                "OO..#....#" + System.lineSeparator() +
                "OO..O##..O" + System.lineSeparator() +
                "O..#.OO..." + System.lineSeparator() +
                "........#." + System.lineSeparator() +
                "..#....#.#" + System.lineSeparator() +
                "..O..#.O.O" + System.lineSeparator() +
                "..O......." + System.lineSeparator() +
                "#....###.." + System.lineSeparator() +
                "#....#....";
        // @formatter:on
        // Given
        final ParabolicReflector reflector = new ParabolicReflector(INPUT_EXAMPLE_01);
        // When
        reflector.tiltLever();
        // Then
        assertEquals(expectedResult, reflector.render());
    }

    @Test
    void testRender() {
        final String expectedResult =
                // @formatter:off
                "O....#...." + System.lineSeparator() +
                "O.OO#....#" + System.lineSeparator() +
                ".....##..." + System.lineSeparator() +
                "OO.#O....O" + System.lineSeparator() +
                ".O.....O#." + System.lineSeparator() +
                "O.#..O.#.#" + System.lineSeparator() +
                "..O..#O..O" + System.lineSeparator() +
                ".......O.." + System.lineSeparator() +
                "#....###.." + System.lineSeparator() +
                "#OO..#....";
                // @formatter:on
        final ParabolicReflector reflector = new ParabolicReflector(INPUT_EXAMPLE_01);
        assertEquals(expectedResult, reflector.render());
    }
}