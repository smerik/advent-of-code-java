package nl.smerik.adventofcode.aoc2021.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day10ServiceTest {

    @Autowired
    private Day10Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(464991, dayService.getSolutionPart1());
    }
}