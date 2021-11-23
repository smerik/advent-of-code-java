package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import nl.smerik.adventofcode.aoc2019.model.vacuumrobot.AftScaffoldControlInformationInterface;
import nl.smerik.adventofcode.aoc2019.model.vacuumrobot.VacuumRobot;
import nl.smerik.adventofcode.aoc2019.service.PuzzleInputService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day17Service {

    private final PuzzleInputService puzzleInputService;

    @Value("classpath:input/day-17.txt")
    private Resource resource;

    public Day17Service(final PuzzleInputService puzzleInputService) {
        this.puzzleInputService = puzzleInputService;
    }

    public int getSolutionPart1() {
        final long[] program = puzzleInputService.readIntcodeProgram(resource);
        IntcodeComputer computer = new IntcodeComputer(program);
        VacuumRobot robot = new VacuumRobot(computer);
        AftScaffoldControlInformationInterface scaffoldInterface = new AftScaffoldControlInformationInterface(robot);
        scaffoldInterface.drawArea();
        return scaffoldInterface.sumAlignmentParameterOfScaffoldIntersections();
    }
}
