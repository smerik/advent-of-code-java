package nl.smerik.adventofcode.aoc2021.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day03ServiceTest {

    @Autowired
    private Day03Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(2583164, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(2784375, dayService.getSolutionPart2());
    }
}