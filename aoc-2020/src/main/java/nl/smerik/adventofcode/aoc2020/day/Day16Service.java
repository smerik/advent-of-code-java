package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.train.TicketDocument;
import nl.smerik.adventofcode.aoc2020.service.train.TicketParser;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day16Service {

    private final TicketParser ticketParser;

    @Value("classpath:input/day-16.txt")
    private Resource resource;

    public Day16Service(final TicketParser ticketParser) {
        this.ticketParser = ticketParser;
    }

    public Integer getSolutionPart1() {
        return initTicketDocument().calculateTicketScanningErrorRate();
    }

    private TicketDocument initTicketDocument() {
        final List<String> documentLines = PuzzleInputParser.parseToString(resource);
        return ticketParser.parse(documentLines);
    }

    public Long getSolutionPart2() {
        final TicketDocument ticketDocument = initTicketDocument();
        ticketDocument.determineTicketRuleIndexes();
        return ticketDocument.multiplyFieldsContaining("departure");
    }
}
