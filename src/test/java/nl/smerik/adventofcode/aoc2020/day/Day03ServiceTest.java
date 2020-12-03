package nl.smerik.adventofcode.aoc2020.day;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day03ServiceTest {

    @Autowired
    private Day03Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(159, dayService.getSolutionPart1());
    }
}