package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day06ServiceTest {

    @Autowired
    private Day06Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(245089, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(511, dayService.getSolutionPart2());
    }
}
