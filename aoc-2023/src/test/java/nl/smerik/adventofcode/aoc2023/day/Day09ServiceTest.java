package nl.smerik.adventofcode.aoc2023.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day09ServiceTest {

    @Autowired
    private Day09Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(1861775706, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(1082, dayService.getSolutionPart2());
    }
}