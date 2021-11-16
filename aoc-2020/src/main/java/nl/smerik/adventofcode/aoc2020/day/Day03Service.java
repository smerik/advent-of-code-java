package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.AreaMap;
import nl.smerik.adventofcode.aoc2020.model.Toboggan;
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
public class Day03Service {

    @Value("classpath:input/day-03.txt")
    private Resource resource;

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> input = stringStream.toList();
            final AreaMap areaMap = new AreaMap(input);
            final Toboggan toboggan = new Toboggan(areaMap);
            return toboggan.countTreesUsingSlope(3, 1);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> input = stringStream.toList();
            final AreaMap areaMap = new AreaMap(input);
            final Toboggan toboggan = new Toboggan(areaMap);
            int result1 = toboggan.countTreesUsingSlope(1, 1);
            int result2 = toboggan.countTreesUsingSlope(3, 1);
            int result3 = toboggan.countTreesUsingSlope(5, 1);
            int result4 = toboggan.countTreesUsingSlope(7, 1);
            int result5 = toboggan.countTreesUsingSlope(1, 2);
            return (long) result1 * result2 * result3 * result4 * result5;
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
