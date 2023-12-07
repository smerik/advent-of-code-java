package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * All existing hand types in order of strongest to weakest.
 */
public enum HandType {

    /**
     * All five cards have the same label.
     * For example: <code>AAAAA</code>.
     */
    FIVE_OF_A_KIND,

    /**
     * Four cards have the same label and one card has a different label.
     * For example: <code>AA8AA</code>.
     */
    FOUR_OF_A_KIND,

    /**
     * Three cards have the same label, and the remaining two cards share a different label.
     * For example: <code>23332</code>.
     */
    FULL_HOUSE,

    /**
     * Three cards have the same label, and the remaining two cards are each different from any other card in the hand.
     * For example: <code>TTT98</code>.
     */
    THREE_OF_A_KIND,

    /**
     * Two cards share one label, two other cards share a second label, and the remaining card has a third label.
     * For example: <code>23432</code>.
     */
    TWO_PAIR,

    /**
     * Two cards share one label, and the other three cards have a different label from the pair and each other
     * For example: <code>A23A4</code>.
     */
    ONE_PAIR,

    /**
     * All cards' labels are distinct.
     * For example: <code>23456</code>.
     */
    HIGH_CARD;

    public static HandType valueByCards(final List<Card> cards) {
        final Map<Card, Long> countByCard = cards.stream().collect(Collectors.groupingBy(card -> card, Collectors.counting()));
        final Collection<Long> counts = countByCard.values();
        if (counts.contains(5L)) {
            return FIVE_OF_A_KIND;
        }
        if (counts.contains(4L)) {
            return FOUR_OF_A_KIND;
        }
        if (counts.contains(3L) && counts.contains(2L)) {
            return FULL_HOUSE;
        }
        if (counts.contains(3L)) {
            return THREE_OF_A_KIND;
        }

        final long pairCount = counts.stream().filter(count -> count > 1).count();
        if (pairCount == 2) {
            return TWO_PAIR;
        }
        if (pairCount == 1) {
            return ONE_PAIR;
        }
        return HIGH_CARD;
    }
}
