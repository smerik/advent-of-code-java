package nl.smerik.adventofcode.aoc2020.model.train;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
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

    public long multiplyFieldsContaining(final String text) {
        return ticketRules.entrySet()
                          .stream()
                          .filter(entry -> entry.getKey().contains(text))
                          .mapToLong(entry -> yourTicket.getFields().get(entry.getValue().getFieldIndex()))
                          .reduce(1, (a, b) -> a * b);
    }

    public void determineTicketRuleIndexes() {
        final Map<Integer, TicketRule> mappedTicketRuleFieldIndexes = new HashMap<>();
        final Set<TicketRule> availableRules = new HashSet<>(ticketRules.values());
        final Set<Ticket> validTickets = findValidTickets();

        while (!availableRules.isEmpty()) {
            for (int i = 0; i < yourTicket.getFields().size(); i++) {
                if (mappedTicketRuleFieldIndexes.containsKey(i)) {
                    continue;
                }

                final Optional<TicketRule> applicableTicketRule
                        = findApplicableTicketRule(availableRules, validTickets, i);
                if (applicableTicketRule.isPresent()) {
                    applicableTicketRule.get().setFieldIndex(i);
                    mappedTicketRuleFieldIndexes.put(i, applicableTicketRule.get());
                    availableRules.remove(applicableTicketRule.get());
                }
            }
        }
    }

    public Set<Ticket> findValidTickets() {
        final Set<Integer> validNumbers = this.ticketRules.values()
                                                          .stream()
                                                          .flatMap(tr -> tr.getValidValues().stream())
                                                          .collect(Collectors.toSet());

        return this.nearbyTickets.stream()
                                 .filter(t -> validNumbers.containsAll(t.getFields()))
                                 .collect(Collectors.toSet());
    }

    private Optional<TicketRule> findApplicableTicketRule(final Set<TicketRule> availableRules,
                                                          final Set<Ticket> tickets,
                                                          final Integer fieldIndex) {

        final Set<TicketRule> rules = new HashSet<>(availableRules);
        for (final Ticket ticket : tickets) {
            final Integer field = ticket.getFields().get(fieldIndex);
            rules.retainAll(findApplicableTicketRules(field));
            if (rules.size() == 1) {
                return rules.stream().findAny();
            }
        }
        return Optional.empty();
    }

    private Set<TicketRule> findApplicableTicketRules(final Integer field) {
        return ticketRules.values()
                          .stream().filter(tr -> tr.isValidFor(field))
                          .collect(Collectors.toSet());
    }
}
