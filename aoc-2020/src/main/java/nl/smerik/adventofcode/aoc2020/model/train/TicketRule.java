package nl.smerik.adventofcode.aoc2020.model.train;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TicketRule {

    private static final Pattern TICKET_FIELD_RULE = Pattern.compile(
            "(?<field>[\\w ]+): (?<range1Start>\\d+)-(?<range1End>\\d+) or (?<range2Start>\\d+)-(?<range2End>\\d+)");

    @EqualsAndHashCode.Include
    private final String field;
    private final Set<Integer> validValues;

    @Setter
    private int fieldIndex;

    public TicketRule(final String rule) {
        final Matcher matcher = TICKET_FIELD_RULE.matcher(rule);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid ticket rule '" + rule + "'");
        }

        this.field = matcher.group("field");
        this.validValues = new HashSet<>();
        this.validValues.addAll(produceRange(matcher.group("range1Start"), matcher.group("range1End")));
        this.validValues.addAll(produceRange(matcher.group("range2Start"), matcher.group("range2End")));
        this.fieldIndex = -1;
    }

    private Set<Integer> produceRange(final String rangeStart, final String rangeEnd) {
        return IntStream.rangeClosed(Integer.parseInt(rangeStart), Integer.parseInt(rangeEnd))
                        .boxed()
                        .collect(Collectors.toSet());
    }

    public boolean isValidFor(final Integer field) {
        return validValues.contains(field);
    }
}
