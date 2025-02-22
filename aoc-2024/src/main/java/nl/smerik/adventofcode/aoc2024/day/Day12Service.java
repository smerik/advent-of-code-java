package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.farm.Garden;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day12Service {

    private final Garden garden;

    public Day12Service(@Value("classpath:input/day-12.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        garden = new Garden(lines);
    }

    public int getSolutionPart1() {
        return garden.calculateTotalFencingPrice();
    }

    public int getSolutionPart2() {
        return garden.calculateTotalFencingPriceOnDiscount();
    }
}
