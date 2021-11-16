package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.service.security.PasswordService;
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
public class Day02Service {

    private final PasswordService passwordService;

    @Value("classpath:input/day-02.txt")
    private Resource resource;

    public Day02Service(final PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @SneakyThrows
    public Long getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> passwordPolicies = stringStream.toList();
            return passwordService.validatePasswordPoliciesForSledRentalPlace(passwordPolicies);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> passwordPolicies = stringStream.toList();
            return passwordService.validatePasswordPoliciesForToboggan(passwordPolicies);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
