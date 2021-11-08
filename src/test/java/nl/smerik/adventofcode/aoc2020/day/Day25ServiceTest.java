package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day25ServiceTest {

    @Autowired
    private Day25Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(2947148, dayService.getSolutionPart1());
    }
}