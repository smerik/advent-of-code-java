package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.satellite.SatelliteMessageDocument;
import nl.smerik.adventofcode.aoc2020.model.satellite.SatelliteMessageRule;
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
public class Day19Service {

    @Value("classpath:input/day-19.txt")
    private Resource resource;

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final SatelliteMessageDocument satelliteMessageDocument = new SatelliteMessageDocument(stringStream.toList());
            return satelliteMessageDocument.findMessagesMatchingRule(0).size();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Integer getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final SatelliteMessageDocument satelliteMessageDocument = new SatelliteMessageDocument(stringStream.toList());
            SatelliteMessageRule rule8 = satelliteMessageDocument.getMessageRulesByRuleId().get(8);
            SatelliteMessageRule rule42 = satelliteMessageDocument.getMessageRulesByRuleId().get(42);
            SatelliteMessageRule rule11 = satelliteMessageDocument.getMessageRulesByRuleId().get(11);
            SatelliteMessageRule rule31 = satelliteMessageDocument.getMessageRulesByRuleId().get(31);

            rule8.addSubRuleList(List.of(rule42, rule8));
            rule11.addSubRuleList(List.of(rule42, rule11, rule31));

            return satelliteMessageDocument.findMessagesMatchingRule(0).size();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
