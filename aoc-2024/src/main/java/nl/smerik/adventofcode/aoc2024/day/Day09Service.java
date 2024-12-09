package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.computer.Disk;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day09Service {

    private final Disk disk;

    public Day09Service(@Value("classpath:input/day-09.txt") Resource resource) {
        final String line = PuzzleInputParser.parseToString(resource).get(0);
        disk = new Disk(line);
    }

    public long getSolutionPart1() {
        disk.defragment();
        return disk.calculateChecksum();
    }
}
