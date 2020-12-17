package nl.smerik.adventofcode.aoc2020.model.train;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Documented;

import static org.junit.jupiter.api.Assertions.*;

class TicketDocumentTest {

    @Test
    void calculateTicketScanningErrorRate() {
        final TicketDocument document = new TicketDocument();
        document.addRule(new TicketRule("class: 1-3 or 5-7"));
        document.addRule(new TicketRule("row: 6-11 or 33-44"));
        document.addRule(new TicketRule("seat: 13-40 or 45-50"));

        document.addNearbyTicket(new Ticket("7,3,47"));
        document.addNearbyTicket(new Ticket("40,4,50"));
        document.addNearbyTicket(new Ticket("55,2,20"));
        document.addNearbyTicket(new Ticket("38,6,12"));

        assertEquals(71, document.calculateTicketScanningErrorRate());
    }
}