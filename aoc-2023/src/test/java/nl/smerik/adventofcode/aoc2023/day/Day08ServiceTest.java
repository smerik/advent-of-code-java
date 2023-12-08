package nl.smerik.adventofcode.aoc2023.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day08ServiceTest {

    @Autowired
    private Day08Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(14429, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(BigInteger.valueOf(10921547990923L), dayService.getSolutionPart2());
    }
}