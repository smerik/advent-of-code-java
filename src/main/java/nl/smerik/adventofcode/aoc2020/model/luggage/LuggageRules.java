package nl.smerik.adventofcode.aoc2020.model.luggage;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
public class LuggageRules {

    private static final Pattern CONTENT_PATTERN
            = Pattern.compile("((?<amount>\\d+) (?<bagType>(\\w+) (\\w+)) (?! bag(s,|s.|,)))");

    private final Map<String, Bag> bags;

    public LuggageRules(final List<String> rules) {
        bags = new HashMap<>();
        for (final String rule : rules) {
            final Bag bag = parseRule(rule);
            bags.put(bag.getBagType(), bag);
        }
    }

    private Bag parseRule(final String rule) {
        final String[] bagTypeContains = rule.split(" bags contain ");
        final String bagType = bagTypeContains[0];
        final String bagContains = bagTypeContains[1];
        final Map<Bag, Integer> content = parseContent(bagContains);
        final Bag bag = bags.computeIfAbsent(bagType, Bag::new);
        bag.addContent(content);

        return bag;
    }

    private Map<Bag, Integer> parseContent(final String content) {
        final Map<Bag, Integer> result = new HashMap<>();
        final Matcher matcher = CONTENT_PATTERN.matcher(content);
        while (matcher.find()) {
            final Bag bag = bags.computeIfAbsent(matcher.group("bagType"), Bag::new);
            result.put(bag, Integer.valueOf(matcher.group("amount")));
        }
        return result;
    }

    public Set<Bag> getBagsEventuallyContaining(final String bagType) {
        if (!bags.containsKey(bagType)) {
            return Collections.emptySet();
        }

        final Bag bagToFind = bags.get(bagType);
        return bags.values().stream()
                            .filter(bag -> !bag.equals(bagToFind))
                            .filter(bag -> bag.containsBag(bagToFind))
                            .collect(Collectors.toSet());
    }

    /**
     * Counts the total number of bags inside given bag type.
     * Bags inside other bags included.
     *
     * @param bagType the bag type to count the total for
     * @return the total number of bags inside
     */
    public int countTotalNumberOfBagsInside(final String bagType) {
        if (!bags.containsKey(bagType)) {
            return 0;
        }
        return bags.get(bagType).countTotalNumberOfBags() - 1;
    }
}
