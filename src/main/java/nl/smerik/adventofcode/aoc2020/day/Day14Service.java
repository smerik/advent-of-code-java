package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.ferry.DockingProgram;
import nl.smerik.adventofcode.aoc2020.model.ferry.DockingProgramV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class Day14Service {

    @Value("classpath:input/day-14.txt")
    private Resource resource;

    @SneakyThrows
    public Long getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> initializationProgram = stringStream.collect(Collectors.toList());
            final DockingProgram program = new DockingProgram();
            program.run(initializationProgram);
            return program.sumMemory();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> initializationProgram = stringStream.collect(Collectors.toList());
            final DockingProgramV2 program = new DockingProgramV2();
            program.run(initializationProgram);
            return program.sumMemory();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
