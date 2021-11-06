package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day24ServiceTest {

    @Autowired
    private Day24Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(232L, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(3519, dayService.getSolutionPart2());
    }
}