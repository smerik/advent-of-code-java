package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.boarding.BoardingPass;
import nl.smerik.adventofcode.aoc2020.service.BoardingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class Day05Service {

    private final BoardingService boardingService;

    @Value("classpath:input/day-05.txt")
    private Resource resource;

    public Day05Service(final BoardingService boardingService) {
        this.boardingService = boardingService;
    }

    @SneakyThrows
    public Long getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> input = stringStream.collect(Collectors.toList());
            final Set<BoardingPass> boardingPasses = boardingService.parseBoardingPasses(input);
            return boardingService.findHighestSeatID(boardingPasses);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
