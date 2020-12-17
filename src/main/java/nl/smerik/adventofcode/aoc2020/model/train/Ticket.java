package nl.smerik.adventofcode.aoc2020.model.train;

import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Ticket {

    private final Set<Integer> fields;

    public Ticket(final String fields) {
        this.fields = Stream.of(fields.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());
    }
}
