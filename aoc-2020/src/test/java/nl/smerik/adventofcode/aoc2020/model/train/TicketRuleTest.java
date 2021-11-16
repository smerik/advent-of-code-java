package nl.smerik.adventofcode.aoc2020.model.train;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketRuleTest {

    @Test
    void testEquals() {
        final TicketRule rule1 = new TicketRule("foo: 1-2 or 4-5");
        final TicketRule rule2 = new TicketRule("foo: 2-3 or 5-6");
        assertEquals(rule1, rule2);
    }
}