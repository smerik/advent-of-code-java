package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day22ServiceTest {

    @Autowired
    private Day22Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(30138, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(31587, dayService.getSolutionPart2());
    }
}