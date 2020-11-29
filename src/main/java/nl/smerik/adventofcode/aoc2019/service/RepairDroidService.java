package nl.smerik.adventofcode.aoc2019.service;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import nl.smerik.adventofcode.aoc2019.model.repairdroid.RepairDroid;
import nl.smerik.adventofcode.aoc2019.model.repairdroid.Section;
import org.springframework.stereotype.Service;

@Service
public class RepairDroidService {

    public Long determineFewestNumberOfCommandsToMoveToOxygenSystem(final long[] program) {
        final IntcodeComputer intcodeComputer = new IntcodeComputer(program);
        final RepairDroid repairDroid = new RepairDroid(intcodeComputer);
        final Section section = new Section(repairDroid);

        // TODO: explore every coordinate
        // TODO: determine fewest number of movement commands from start pos to oxygen
        return -1L;
    }
}
