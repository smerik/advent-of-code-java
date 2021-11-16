package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day01ServiceTest {

    @Autowired
    private Day01Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(955584, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(287503934, dayService.getSolutionPart2());
    }
}