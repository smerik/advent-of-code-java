package nl.smerik.adventofcode.aoc2019.day;

import lombok.SneakyThrows;
import nl.smerik.adventofcode.aoc2019.model.jupiter.JupiterMoonTracker;
import nl.smerik.adventofcode.aoc2019.model.jupiter.Moon;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day12Service {

    @Value("classpath:input/day-12.txt")
    private Resource resource;

    @SneakyThrows
    public Long getSolutionPart1() {
        final JupiterMoonTracker tracker = initTracker();
        tracker.simulateMotion(1000);
        return tracker.getTotalEnergy();
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        return initTracker().simulateStepsToSameState();
    }

    private JupiterMoonTracker initTracker() {
        final List<String> moonPositions = PuzzleInputParser.parseToString(resource);
        final List<Moon> moons = moonPositions.stream().map(Moon::new).toList();
        return new JupiterMoonTracker(moons);
    }
}
