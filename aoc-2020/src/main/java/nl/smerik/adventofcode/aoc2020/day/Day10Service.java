package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.joltage.JoltageAdapterArray;
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
public class Day10Service {

    @Value("classpath:input/day-10.txt")
    private Resource resource;

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Integer> input = stringStream.mapToInt(Integer::valueOf).boxed().toList();
            final JoltageAdapterArray joltageAdapterArray = new JoltageAdapterArray(input);
            return joltageAdapterArray.countDifferencesForJolt(1) * joltageAdapterArray.countDifferencesForJolt(3);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Integer> input = stringStream.mapToInt(Integer::valueOf).boxed().toList();
            final JoltageAdapterArray joltageAdapterArray = new JoltageAdapterArray(input);
            return joltageAdapterArray.countTotalNumberOfArrangements();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
