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

    public Long findEmptySeat(final Set<BoardingPass> boardingPasses) {
        final List<Long> sortedSeatIDs = boardingPasses.stream()
                                                       .mapToLong(BoardingPass::getSeatID)
                                                       .sorted()
                                                       .boxed()
                                                       .collect(Collectors.toList());
        for (int i = 1; i < sortedSeatIDs.size() - 1; i++) {
            if (sortedSeatIDs.get(i - 1) == sortedSeatIDs.get(i) - 2) {
                return sortedSeatIDs.get(i) - 1;
            }
        }
        return -1L;
    }
}
