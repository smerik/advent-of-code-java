package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.service.PuzzleInputService;
import nl.smerik.adventofcode.aoc2019.service.RepairDroidService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day15Service {

    private final PuzzleInputService puzzleInputService;

    private final RepairDroidService repairDroidService;

    @Value("classpath:input/day-15.txt")
    private Resource resource;

    public Day15Service(final PuzzleInputService puzzleInputService,
                        final RepairDroidService repairDroidService) {
        this.puzzleInputService = puzzleInputService;
        this.repairDroidService = repairDroidService;
    }

    public int getSolutionPart1() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        return repairDroidService.determineFewestNumberOfCommandsToMoveToOxygenSystem(program);
    }
}
