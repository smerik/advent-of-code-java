package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.service.AmplificationCircuitService;
import nl.smerik.adventofcode.aoc2019.service.PuzzleInputService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Day07Service {

    private final PuzzleInputService puzzleInputService;

    private final AmplificationCircuitService amplificationCircuitService;

    @Value("classpath:input/day-07.txt")
    private Resource resource;

    public Day07Service(final PuzzleInputService puzzleInputService,
                        final AmplificationCircuitService amplificationCircuitService) {
        this.puzzleInputService = puzzleInputService;
        this.amplificationCircuitService = amplificationCircuitService;
    }

    public long getSolutionPart1() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final List<Integer> phases = IntStream.range(0, 5).boxed().collect(Collectors.toList());
        return amplificationCircuitService.determineLargestOutputSignal(program, phases);
    }

    public long getSolutionPart2() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final List<Integer> phases = IntStream.range(5, 10).boxed().collect(Collectors.toList());
        return amplificationCircuitService.determineLargestOutputSignal(program, phases);
    }
}
