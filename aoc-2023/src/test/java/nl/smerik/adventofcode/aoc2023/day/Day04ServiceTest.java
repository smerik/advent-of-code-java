package nl.smerik.adventofcode.aoc2023.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day04ServiceTest {

    @Autowired
    private Day04Service day04Service;

    @Test
    void getSolutionPart1() {
        assertEquals(18653, day04Service.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(5921508, day04Service.getSolutionPart2());
    }
}