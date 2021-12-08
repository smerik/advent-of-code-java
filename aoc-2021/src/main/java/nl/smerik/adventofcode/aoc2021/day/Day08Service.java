package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.service.display.FourDigitDisplayService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class Day08Service {

    private final FourDigitDisplayService displayService;

    @Value("classpath:input/day-08.txt")
    private Resource resource;

    public Day08Service(final FourDigitDisplayService displayService) {
        this.displayService = displayService;
    }

    public Integer getSolutionPart1() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return displayService.countDigits(input, Set.of(1, 4, 7, 8));
    }
}