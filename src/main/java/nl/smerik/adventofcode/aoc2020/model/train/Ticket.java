package nl.smerik.adventofcode.aoc2020.model.train;

import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

@Data
public class Ticket {

    private final List<Integer> fields;

    public Ticket(final String fields) {
        this.fields = Stream.of(fields.split(","))
                            .map(Integer::parseInt)
                            .toList();
    }
}
