package nl.smerik.adventofcode.aoc2021.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day07ServiceTest {

    @Autowired
    private Day07Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(336701, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(95167302, dayService.getSolutionPart2());
    }
}