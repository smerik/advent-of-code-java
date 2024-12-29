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

    @Value("input/day-12/example-04.txt")
    private Resource example04Resource;

    @Value("input/day-12/example-05.txt")
    private Resource example05Resource;

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

    @Test
    void testCalculateTotalFencingPriceOnDiscount() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final Garden gardenExample01 = new Garden(linesExample01);
        assertEquals(80, gardenExample01.calculateTotalFencingPriceOnDiscount());

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        final Garden gardenExample02 = new Garden(linesExample02);
        assertEquals(436, gardenExample02.calculateTotalFencingPriceOnDiscount());

        final List<String> linesExample04 = PuzzleInputParser.parseToString(example04Resource);
        final Garden gardenExample04 = new Garden(linesExample04);
        assertEquals(236, gardenExample04.calculateTotalFencingPriceOnDiscount());

        final List<String> linesExample05 = PuzzleInputParser.parseToString(example05Resource);
        final Garden gardenExample05 = new Garden(linesExample05);
        assertEquals(368, gardenExample05.calculateTotalFencingPriceOnDiscount());

        final List<String> linesExample03 = PuzzleInputParser.parseToString(example03Resource);
        final Garden gardenExample03 = new Garden(linesExample03);
        assertEquals(1206, gardenExample03.calculateTotalFencingPriceOnDiscount());
    }
}