package nl.smerik.adventofcode.aoc2019.model.vacuumrobot;

import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;

import java.util.List;

@Slf4j
public class VacuumRobot {

    private final IntcodeComputer intcodeComputer;

    public VacuumRobot(final IntcodeComputer intcodeComputer) {
        this.intcodeComputer = intcodeComputer;
    }

    public List<Long> showCameraOutput() {
        return intcodeComputer.run();
    }
}
