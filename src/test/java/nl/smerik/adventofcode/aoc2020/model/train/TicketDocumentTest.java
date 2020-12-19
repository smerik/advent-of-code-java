package nl.smerik.adventofcode.aoc2020.model.train;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketDocumentTest {

    private TicketDocument createTicketDocumentPart01Example01() {
        final TicketDocument document = new TicketDocument();
        document.addRule(new TicketRule("class: 1-3 or 5-7"));
        document.addRule(new TicketRule("row: 6-11 or 33-44"));
        document.addRule(new TicketRule("seat: 13-40 or 45-50"));

        document.addNearbyTicket(new Ticket("7,3,47"));
        document.addNearbyTicket(new Ticket("40,4,50"));
        document.addNearbyTicket(new Ticket("55,2,20"));
        document.addNearbyTicket(new Ticket("38,6,12"));
        return document;
    }

    @Test
    void calculateTicketScanningErrorRate() {
        final TicketDocument document = createTicketDocumentPart01Example01();
        assertEquals(71, document.calculateTicketScanningErrorRate());
    }

    @Test
    void findValidTickets() {
        final TicketDocument document = createTicketDocumentPart01Example01();
        assertEquals(Set.of(new Ticket("7,3,47")), document.findValidTickets());
    }

    private TicketDocument createTicketDocumentPart02Example01() {
        final TicketDocument document = new TicketDocument();
        document.addRule(new TicketRule("class: 0-1 or 4-19"));
        document.addRule(new TicketRule("row: 0-5 or 8-19"));
        document.addRule(new TicketRule("seat: 0-13 or 16-19"));

        document.setYourTicket(new Ticket("11,12,13"));

        document.addNearbyTicket(new Ticket("3,9,18"));
        document.addNearbyTicket(new Ticket("15,1,5"));
        document.addNearbyTicket(new Ticket("5,14,9"));
        return document;
    }

    @Test
    void determineTicketRuleIndexes() {
        final TicketDocument document = createTicketDocumentPart02Example01();

        document.determineTicketRuleIndexes();
        assertEquals(0, document.getTicketRules().get("row").getFieldIndex());
        assertEquals(1, document.getTicketRules().get("class").getFieldIndex());
        assertEquals(2, document.getTicketRules().get("seat").getFieldIndex());
    }


    @Test
    void multiplyFieldsContaining() {
        final TicketDocument document = createTicketDocumentPart02Example01();
        document.determineTicketRuleIndexes();

        assertEquals(11, document.multiplyFieldsContaining("row"));
        assertEquals(12, document.multiplyFieldsContaining("class"));
        assertEquals(13, document.multiplyFieldsContaining("seat"));
        assertEquals(156, document.multiplyFieldsContaining("a")); // class * seat
    }
}