package nl.smerik.adventofcode.aoc2023.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day03ServiceTest {

    @Autowired
    private Day03Service day03Service;

    @Test
    void getSolutionPart1() {
        assertEquals(560670, day03Service.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(0, day03Service.getSolutionPart2());
    }
}