package nl.smerik.adventofcode.aoc2020.service;

import nl.smerik.adventofcode.aoc2020.model.boarding.BoardingPass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BoardingService {

    public Set<BoardingPass> parseBoardingPasses(final List<String> input) {
        return input.stream().map(BoardingPass::new).collect(Collectors.toSet());
    }

    public Long findHighestSeatID(final Set<BoardingPass> boardingPasses) {
        return boardingPasses.stream().mapToLong(BoardingPass::getSeatID).max().orElseThrow();
    }
}
