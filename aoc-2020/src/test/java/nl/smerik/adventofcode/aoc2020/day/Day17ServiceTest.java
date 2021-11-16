package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day17ServiceTest {

    @Autowired
    private Day17Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(324L, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(1836L, dayService.getSolutionPart2());
    }
}