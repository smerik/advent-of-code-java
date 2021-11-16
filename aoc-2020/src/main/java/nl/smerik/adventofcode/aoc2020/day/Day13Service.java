package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.shuttlebus.ShuttleBusService;
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
public class Day13Service {

    @Value("classpath:input/day-13.txt")
    private Resource resource;

    @SneakyThrows
    public Long getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> notes = stringStream.toList();
            final long timestamp = Long.parseLong(notes.get(0));
            final ShuttleBusService busService = new ShuttleBusService(notes.get(1));
            return busService.findEarliestBusToTake(timestamp).calculateSolutionPart01(timestamp);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> notes = stringStream.toList();
            final ShuttleBusService busService = new ShuttleBusService(notes.get(1));
            return busService.findSubsequentBusDeparturesTimestamp(0);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
