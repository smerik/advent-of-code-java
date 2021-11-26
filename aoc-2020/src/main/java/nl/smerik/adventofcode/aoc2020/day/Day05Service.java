package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.boarding.BoardingPass;
import nl.smerik.adventofcode.aoc2020.service.boarding.BoardingService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class Day05Service {

    private final BoardingService boardingService;

    @Value("classpath:input/day-05.txt")
    private Resource resource;

    public Day05Service(final BoardingService boardingService) {
        this.boardingService = boardingService;
    }

    public Long getSolutionPart1() {
        final Set<BoardingPass> boardingPasses = initBoardingPasses();
        return boardingService.findHighestSeatID(boardingPasses);
    }

    public Long getSolutionPart2() {
        final Set<BoardingPass> boardingPasses = initBoardingPasses();
        return boardingService.findEmptySeat(boardingPasses);
    }

    private Set<BoardingPass> initBoardingPasses() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return boardingService.parseBoardingPasses(input);
    }
}
