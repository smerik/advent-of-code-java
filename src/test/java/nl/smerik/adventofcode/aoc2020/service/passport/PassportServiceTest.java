package nl.smerik.adventofcode.aoc2020.service.passport;

import nl.smerik.adventofcode.aoc2020.model.passport.Passport;
import nl.smerik.adventofcode.aoc2020.service.passport.PassportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PassportServiceTest {

    @Value("classpath:input/day-04/example-01.txt")
    private Resource resource;

    @Autowired
    private PassportService passportService;

    @Test
    void parseBatch() throws Exception {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> input = stringStream.collect(Collectors.toList());
            final Set<Passport> passports = passportService.parseBatch(input);

            assertEquals(4, passports.size());
            assertEquals(2, passportService.getValidPassports(passports).size());
        }
    }
}