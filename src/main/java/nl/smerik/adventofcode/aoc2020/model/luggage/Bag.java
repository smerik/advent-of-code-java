package nl.smerik.adventofcode.aoc2020.model.luggage;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Bag {

    @EqualsAndHashCode.Include
    private final String bagType;

    private final Map<Bag, Integer> content;

    public Bag(final String bagType) {
        this.bagType = bagType;
        this.content = new HashMap<>();
    }

    public void addContent(final Map<Bag, Integer> content) {
        this.content.putAll(content);
    }

    public int getNumberOfBags() {
        return content.values().stream().mapToInt(Integer::valueOf).sum();
    }

    /**
     * Counts the total number of bags.
     * This includes this bag + all bags inside this bag and bags inside those bags, et cetera.
     *
     * @return the total number of bags
     */
    public int countTotalNumberOfBags() {
        int result = 1;
        for (final Map.Entry<Bag, Integer> entry : content.entrySet()) {
            result += entry.getValue() * entry.getKey().countTotalNumberOfBags();
        }
        return result;
    }

    public boolean containsBag(final Bag bag) {
        if (content.isEmpty()) {
            return false;
        }
        if (content.containsKey(bag)) {
            return true;
        }
        for (final Bag contentBag : content.keySet()) {
            if (contentBag.containsBag(bag)) {
                return true;
            }
        }
        return false;
    }
}
