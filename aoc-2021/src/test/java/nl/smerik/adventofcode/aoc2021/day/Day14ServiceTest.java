package nl.smerik.adventofcode.aoc2021.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day14ServiceTest {

    @Autowired
    private Day14Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(2233L, dayService.getSolutionPart1());
    }
}