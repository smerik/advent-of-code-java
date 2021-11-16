package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.util.ComboBreakerUtil;
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
public class Day25Service {

    @Value("classpath:input/day-25.txt")
    private Resource resource;

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> keys = stringStream.toList();
            final int publicKeyCard = Integer.parseInt(keys.get(0));
            final int publicKeyDoor = Integer.parseInt(keys.get(1));
            return ComboBreakerUtil.determineEncryptionKey(publicKeyCard, publicKeyDoor);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
