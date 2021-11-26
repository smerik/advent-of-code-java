package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.waitingarea.WaitingArea;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day11Service {

    @Value("classpath:input/day-11.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final WaitingArea waitingArea = initWaitingArea();
        waitingArea.simulatePart01();
        return waitingArea.getOccupiedSeats().size();
    }

    public Integer getSolutionPart2() {
        final WaitingArea waitingArea = initWaitingArea();
        waitingArea.simulatePart02();
        return waitingArea.getOccupiedSeats().size();
    }

    private WaitingArea initWaitingArea() {
        final List<String> seatLayout = PuzzleInputParser.parseToString(resource);
        return new WaitingArea(seatLayout);
    }
}
