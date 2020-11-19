package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import nl.smerik.adventofcode.aoc2019.model.spaceship.EmergencyHullPaintingRobot;
import nl.smerik.adventofcode.aoc2019.model.spaceship.Hull;
import nl.smerik.adventofcode.aoc2019.service.PuzzleInputService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day11Service {

    private final PuzzleInputService puzzleInputService;

    @Value("classpath:input/day-11.txt")
    private Resource resource;

    public Day11Service(final PuzzleInputService puzzleInputService) {
        this.puzzleInputService = puzzleInputService;
    }

    public Long getSolutionPart1() {
        final Hull hull = new Hull();
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        final IntcodeComputer intcodeComputer = new IntcodeComputer(program);
        final EmergencyHullPaintingRobot robot = new EmergencyHullPaintingRobot(hull, intcodeComputer);
        robot.draw();
        return hull.countPaintedPanels();
    }
}
