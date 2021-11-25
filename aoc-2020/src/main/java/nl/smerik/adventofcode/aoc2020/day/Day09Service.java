package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.service.crypto.ExchangeMaskingAdditionSystemService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day09Service {

    private final ExchangeMaskingAdditionSystemService xmasService;

    @Value("classpath:input/day-09.txt")
    private Resource resource;

    public Day09Service(final ExchangeMaskingAdditionSystemService xmasService) {
        this.xmasService = xmasService;
    }

    public Long getSolutionPart1() {
        final List<Long> input = PuzzleInputParser.parseToLong(resource);
        return xmasService.validate(input, 25);
    }

    public Long getSolutionPart2() {
        final List<Long> input = PuzzleInputParser.parseToLong(resource);
        final Long invalidSum = xmasService.validate(input, 25);
        final List<Long> contiguousRange = xmasService.findContiguousRange(input, invalidSum);
        return xmasService.findEncryptionWeakness(contiguousRange);
    }
}
