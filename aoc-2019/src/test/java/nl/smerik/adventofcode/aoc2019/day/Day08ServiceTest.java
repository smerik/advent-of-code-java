package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day08ServiceTest {

    @Autowired
    private Day08Service dayService;

    @Test
    void getSolutionPart1() throws IOException {
        assertEquals(1548, dayService.getSolutionPart1());

    }

    @Test
    void getSolutionPart2() throws IOException {
        // @formatter:off
        final String expectedResult = "Image:\n" +
                                      " ##  #### #  # #  #  ##  \n" +
                                      "#  # #    # #  #  # #  # \n" +
                                      "#    ###  ##   #  # #  # \n" +
                                      "#    #    # #  #  # #### \n" +
                                      "#  # #    # #  #  # #  # \n" +
                                      " ##  #### #  #  ##  #  # \n";
        // @formatter:on
        // Expected result should read: CEKUA
        assertEquals(expectedResult, dayService.getSolutionPart2());
    }
}
