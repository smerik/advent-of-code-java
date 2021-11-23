package nl.smerik.adventofcode.aoc2019.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2019.model.jupiter.JupiterMoonTracker;
import nl.smerik.adventofcode.aoc2019.model.jupiter.Moon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class Day12Service {

    @Value("classpath:input/day-12.txt")
    private Resource resource;

    @SneakyThrows
    public Long getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Moon> moons = stringStream.map(Moon::new).toList();
            final JupiterMoonTracker tracker = new JupiterMoonTracker(moons);
            tracker.simulateMotion(1000);
            return tracker.getTotalEnergy();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Moon> moons = stringStream.map(Moon::new).toList();
            final JupiterMoonTracker tracker = new JupiterMoonTracker(moons);
            return tracker.simulateStepsToSameState();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
