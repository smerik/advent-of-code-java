package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.FuelManagementSystem;
import nl.smerik.adventofcode.aoc2019.model.Wire;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day03Service {

    @Value("classpath:input/day-03.txt")
    private Resource resource;

    public int getSolutionPart1() {
        return initFuelManagementSystem().getManhattanDistanceFromCentralPortToClosestIntersection();
    }

    public int getSolutionPart2() {
        return initFuelManagementSystem().getFewestCombinedStepsToReachAnIntersection();
    }

    private FuelManagementSystem initFuelManagementSystem() {
        final FuelManagementSystem fuelManagementSystem = new FuelManagementSystem();

        final List<String> wirePaths = PuzzleInputParser.parseToString(resource);
        wirePaths.forEach(wirePath -> fuelManagementSystem.addWire(new Wire(wirePath)));
        return fuelManagementSystem;
    }
}
