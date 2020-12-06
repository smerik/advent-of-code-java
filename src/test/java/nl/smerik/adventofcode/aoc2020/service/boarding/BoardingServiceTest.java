package nl.smerik.adventofcode.aoc2020.service.boarding;

import nl.smerik.adventofcode.aoc2020.model.boarding.BoardingPass;
import nl.smerik.adventofcode.aoc2020.service.boarding.BoardingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardingServiceTest {

    @Autowired
    private BoardingService boardingService;

    @Test
    void parseBoardingPasses() {
        final List<String> input = provideBoardingPasses();
        final Set<BoardingPass> result = boardingService.parseBoardingPasses(input);
        assertEquals(4, result.size());
    }

    @Test
    void findHighestSeatID() {
        final List<String> input = provideBoardingPasses();
        final Set<BoardingPass> boardingPasses = boardingService.parseBoardingPasses(input);
        assertEquals(820, boardingService.findHighestSeatID(boardingPasses));
    }

    private List<String> provideBoardingPasses() {
        return Arrays.asList("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL");
    }
}