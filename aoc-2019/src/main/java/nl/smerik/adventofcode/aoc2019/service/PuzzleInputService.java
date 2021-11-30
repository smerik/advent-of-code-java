package nl.smerik.adventofcode.aoc2019.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
@Service
public class PuzzleInputService {

    public long[] readIntcodeProgram(final Resource resource) {
        try {
            final Path path = Paths.get(resource.getURI());
            final String[] strings = Files.readString(path)
                    .replace("\r", "")
                    .replace("\n", "")
                    .split(",");
            return Stream.of(strings)
                    .mapToLong(Long::parseLong)
                    .toArray();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
        }
        return new long[0];
    }
}
