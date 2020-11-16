package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.service.AmplificationCircuitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class Day07Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day07Service.class);

    private final  AmplificationCircuitService amplificationCircuitService;

    @Value("classpath:input/day-07.txt")
    private Resource resource;

    public Day07Service(final AmplificationCircuitService amplificationCircuitService) {
        this.amplificationCircuitService = amplificationCircuitService;
    }

    public int getSolutionPart1() {
        try {
            final Path path = Paths.get(resource.getURI());
            final String[] strings = Files.readString(path).replace("\n", "").split(",");
            final int[] integers = Stream.of(strings).mapToInt(Integer::parseInt).toArray();
            return amplificationCircuitService.determineLargestOutputSignal(integers);
        } catch (IOException e) {
            LOGGER.error("Houston: {}", e.getMessage(), e);
            return -1;
        }
    }
}
