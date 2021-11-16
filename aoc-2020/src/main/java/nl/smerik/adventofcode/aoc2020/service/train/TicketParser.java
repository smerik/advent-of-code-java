package nl.smerik.adventofcode.aoc2020.service.train;

import nl.smerik.adventofcode.aoc2020.model.train.Ticket;
import nl.smerik.adventofcode.aoc2020.model.train.TicketDocument;
import nl.smerik.adventofcode.aoc2020.model.train.TicketRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketParser {

    private enum ParserState {
        RULE {
            @Override
            ParserState next() {
                return YOUR_TICKET;
            }
        },
        YOUR_TICKET {
            @Override
            ParserState next() {
                return NEARBY_TICKET;
            }
        },
        NEARBY_TICKET {
            @Override
            ParserState next() {
                return null;
            }
        };

        abstract ParserState next();
    }

    public TicketDocument parse(final List<String> documentLines) {
        ParserState state = ParserState.RULE;

        final TicketDocument ticketDocument = new TicketDocument();

        for (final String line : documentLines) {
            if (line.isBlank()) {
                state = state.next();
                continue;
            }
            switch (state) {
                case RULE -> ticketDocument.addRule(new TicketRule(line));
                case YOUR_TICKET -> {
                    if (line.equals("your ticket:")) {
                        continue;
                    }
                    ticketDocument.setYourTicket(new Ticket(line));
                }
                case NEARBY_TICKET -> {
                    if (line.equals("nearby tickets:")) {
                        continue;
                    }
                    ticketDocument.addNearbyTicket(new Ticket(line));
                }
                default -> throw new IllegalStateException("Unexpected value: " + state);
            }
        }

        return ticketDocument;
    }
}
