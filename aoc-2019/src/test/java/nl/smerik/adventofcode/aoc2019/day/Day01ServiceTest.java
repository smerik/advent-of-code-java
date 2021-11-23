package nl.smerik.adventofcode.aoc2019.day;

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
        assertEquals(3538016, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(5304147, dayService.getSolutionPart2());
    }
}
