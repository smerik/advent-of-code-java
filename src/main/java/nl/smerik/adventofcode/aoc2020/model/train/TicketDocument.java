package nl.smerik.adventofcode.aoc2020.model.train;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TicketDocument {

    private final Map<String, TicketRule> ticketRules;
    private final Set<Ticket> nearbyTickets;
    private Ticket yourTicket;


    public TicketDocument() {
        this.ticketRules = new HashMap<>();
        this.nearbyTickets = new HashSet<>();
    }

    public void addRule(final TicketRule ticketRule) {
        this.ticketRules.put(ticketRule.getField(), ticketRule);
    }

    public void addNearbyTicket(final Ticket ticket) {
        this.nearbyTickets.add(ticket);
    }

    public int calculateTicketScanningErrorRate() {
        final Set<Integer> validNumbers = this.ticketRules.values()
                                                          .stream()
                                                          .flatMap(tr -> tr.getValidValues().stream())
                                                          .collect(Collectors.toSet());
        return this.nearbyTickets.stream()
                .flatMap(x -> x.getFields().stream())
                .filter(i -> !validNumbers.contains(i))
                .mapToInt(Integer::intValue)
                .sum();

    }
}
