package nl.smerik.adventofcode.aoc2020.service.train;

import nl.smerik.adventofcode.aoc2020.model.train.Ticket;
import nl.smerik.adventofcode.aoc2020.model.train.TicketDocument;
import nl.smerik.adventofcode.aoc2020.model.train.TicketRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TicketParserTest {

    @Autowired
    private TicketParser ticketParser;

    @Test
    void parse() {
        final List<String> input = List.of(
                "class: 1-3 or 5-7",
                "row: 6-11 or 33-44",
                "seat: 13-40 or 45-50",
                "",
                "your ticket:",
                "7,1,14",
                "",
                "nearby tickets:",
                "7,3,47",
                "40,4,50",
                "55,2,20",
                "38,6,12"
        );

        final TicketDocument document = ticketParser.parse(input);

        final Map<String, TicketRule> rules = document.getTicketRules();
        assertEquals(3, rules.size());

        final Ticket yourTicket = document.getYourTicket();
        assertNotNull(yourTicket);

        final Set<Ticket> nearbyTickets = document.getNearbyTickets();
        assertEquals(4, nearbyTickets.size());
    }
}