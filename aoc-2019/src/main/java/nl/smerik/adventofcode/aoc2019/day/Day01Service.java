package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.service.FuelService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day01Service {

    private final FuelService fuelService;

    @Value("classpath:input/day-01.txt")
    private Resource resource;

    public Day01Service(final FuelService fuelService) {
        this.fuelService = fuelService;
    }

    public Integer getSolutionPart1() {
        final List<Integer> masses = PuzzleInputParser.parseToInt(resource);
        return fuelService.calculateTotalRequiredFuel(masses, false);
    }

    public Integer getSolutionPart2() {
        final List<Integer> masses = PuzzleInputParser.parseToInt(resource);
        return fuelService.calculateTotalRequiredFuel(masses, true);
    }
}
