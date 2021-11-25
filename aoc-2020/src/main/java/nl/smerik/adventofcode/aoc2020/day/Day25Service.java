package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.util.ComboBreakerUtil;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day25Service {

    @Value("classpath:input/day-25.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<Integer> keys = PuzzleInputParser.parseToInt(resource);
        final int publicKeyCard = keys.get(0);
        final int publicKeyDoor = keys.get(1);
        return ComboBreakerUtil.determineEncryptionKey(publicKeyCard, publicKeyDoor);
    }
}
