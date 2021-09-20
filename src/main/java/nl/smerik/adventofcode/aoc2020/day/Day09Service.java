package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.service.crypto.ExchangeMaskingAdditionSystemService;
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
public class Day09Service {

    private final ExchangeMaskingAdditionSystemService xmasService;

    @Value("classpath:input/day-09.txt")
    private Resource resource;

    public Day09Service(final ExchangeMaskingAdditionSystemService xmasService) {
        this.xmasService = xmasService;
    }

    @SneakyThrows
    public Long getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Long> input = stringStream.mapToLong(Long::valueOf).boxed().toList();
            return xmasService.validate(input, 25);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Long> input = stringStream.mapToLong(Long::valueOf).boxed().toList();
            final Long invalidSum = xmasService.validate(input, 25);
            final List<Long> contiguousRange = xmasService.findContiguousRange(input, invalidSum);
            return xmasService.findEncryptionWeakness(contiguousRange);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
