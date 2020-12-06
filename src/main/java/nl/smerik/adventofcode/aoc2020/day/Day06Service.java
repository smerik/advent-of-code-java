package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.customs.GroupAnswers;
import nl.smerik.adventofcode.aoc2020.service.customs.CustomsService;
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
public class Day06Service {

    private final CustomsService customsService;

    @Value("classpath:input/day-06.txt")
    private Resource resource;

    public Day06Service(final CustomsService customsService) {
        this.customsService = customsService;
    }

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> input = stringStream.collect(Collectors.toList());
            final List<GroupAnswers> answers = customsService.parseAnswers(input);
            return customsService.sumCountUnionOfAnswers(answers);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
