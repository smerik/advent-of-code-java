package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.ferry.Ferry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class Day12Service {

    @Value("classpath:input/day-12.txt")
    private Resource resource;

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> navigationInstructions = stringStream.collect(Collectors.toList());
            final Point startPosition = new Point();
            final Ferry ferry = new Ferry(startPosition);
            ferry.navigate(navigationInstructions);
            return ferry.calculateManhattanDistance(startPosition);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Integer getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> navigationInstructions = stringStream.collect(Collectors.toList());
            final Point startPosition = new Point();
            final Point waypoint = new Point(10, 1);
            final Ferry ferry = new Ferry(startPosition, waypoint);
            ferry.navigateWithWaypoint(navigationInstructions);
            return ferry.calculateManhattanDistance(startPosition);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
