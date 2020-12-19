package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.train.TicketDocument;
import nl.smerik.adventofcode.aoc2020.service.train.TicketParser;
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
public class Day16Service {

    private final TicketParser ticketParser;

    @Value("classpath:input/day-16.txt")
    private Resource resource;

    public Day16Service(final TicketParser ticketParser) {
        this.ticketParser = ticketParser;
    }

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> documentLines = stringStream.collect(Collectors.toList());
            final TicketDocument ticketDocument = ticketParser.parse(documentLines);
            return ticketDocument.calculateTicketScanningErrorRate();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> documentLines = stringStream.collect(Collectors.toList());
            final TicketDocument ticketDocument = ticketParser.parse(documentLines);
            ticketDocument.determineTicketRuleIndexes();
            return ticketDocument.multiplyFieldsContaining("departure");
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
