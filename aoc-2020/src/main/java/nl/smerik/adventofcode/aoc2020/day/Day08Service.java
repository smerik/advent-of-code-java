package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import nl.smerik.adventofcode.aoc2020.model.cpu.HandheldGameConsole;
import nl.smerik.adventofcode.aoc2020.service.cpu.ProcessorService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day08Service {

    private final ProcessorService processorService;

    @Value("classpath:input/day-08.txt")
    private Resource resource;

    public Day08Service(final ProcessorService processorService) {
        this.processorService = processorService;
    }

    public Integer getSolutionPart1() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final HandheldGameConsole console = new HandheldGameConsole(input);
        return processorService.getAccumulatorValueOnInfiniteLoop(console);
    }

    @SneakyThrows
    public Integer getSolutionPart2() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return processorService.getAccumulatorWhenInstructionsFixed(input);
    }
}
