package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day14ServiceTest {

    @Autowired
    private Day14Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(5902420735773L, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(3801988250775L, dayService.getSolutionPart2());
    }
}