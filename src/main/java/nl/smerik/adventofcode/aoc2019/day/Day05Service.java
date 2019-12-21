package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.service.IntcodeComputerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day05Service {

    private final IntcodeComputerService intcodeComputerService;

    @Value("classpath:input/day-05.txt")
    private Resource resource;

    public Day05Service(final IntcodeComputerService intcodeComputerService) {
        this.intcodeComputerService = intcodeComputerService;
    }

    public int getSolutionPart1(final int instruction) {
        return -1;
    }

    public int getSolutionPart2() {
        return -1;
    }
}
