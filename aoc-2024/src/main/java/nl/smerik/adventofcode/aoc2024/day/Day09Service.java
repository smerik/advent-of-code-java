package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.computer.Disk;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day09Service {

    private final String line;

    public Day09Service(@Value("classpath:input/day-09.txt") Resource resource) {
        line = PuzzleInputParser.parseToString(resource).get(0);
    }

    public long getSolutionPart1() {
        final Disk disk = new Disk(line);
        disk.defragmentIndividualBlocks();
        return disk.calculateChecksum();
    }

    public long getSolutionPart2() {
        final Disk disk = new Disk(line);
        disk.defragmentWholeFiles();
        return disk.calculateChecksum();
    }
}
