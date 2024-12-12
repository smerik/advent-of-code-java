package nl.smerik.adventofcode.aoc2024.model.farm;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GardenTest {

    @Value("input/day-12/example-01.txt")
    private Resource example01Resource;

    @Value("input/day-12/example-02.txt")
    private Resource example02Resource;

    @Value("input/day-12/example-03.txt")
    private Resource example03Resource;

    @Test
    void testCalculateTotalFencingPrice() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final Garden gardenExample01 = new Garden(linesExample01);
        assertEquals(140, gardenExample01.calculateTotalFencingPrice());

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        final Garden gardenExample02 = new Garden(linesExample02);
        assertEquals(772, gardenExample02.calculateTotalFencingPrice());

        final List<String> linesExample03 = PuzzleInputParser.parseToString(example03Resource);
        final Garden gardenExample03 = new Garden(linesExample03);
        assertEquals(1930, gardenExample03.calculateTotalFencingPrice());
    }
}