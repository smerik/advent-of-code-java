package nl.smerik.adventofcode.aoc2021.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day02ServiceTest {

    @Autowired
    private Day02Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(1936494, dayService.getSolutionPart1());
    }
}