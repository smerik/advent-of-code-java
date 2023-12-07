package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

/**
 * All cards in order of relative strength from the highest to the lowest.
 */
public enum Card {

    ACE('A'),
    KING('K'),
    QUEEN('Q'),
    JACK('J'),
    TEN('T'),
    NINE('9'),
    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2');

    private final char label;

    Card(final char label) {
        this.label = label;
    }

    public static Card cardByLabel(final char label) {
        for (final Card card : values()) {
            if (card.label == label) {
                return card;
            }
        }
        throw new IllegalArgumentException("Unknown label '" + label + "'");
    }
}
