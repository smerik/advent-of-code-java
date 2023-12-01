package nl.smerik.adventofcode.aoc2023.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day01ServiceTest {

    @Autowired
    private Day01Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(53974, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(0, dayService.getSolutionPart2());
    }
}
