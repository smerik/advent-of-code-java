package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import lombok.Getter;

import java.util.List;

@Getter
public class Hand implements Comparable<Hand> {

    private final List<Card> cards;
    private final int bid;
    private final HandType type;

    public Hand(final String line, final boolean applyAdditionalRule) {
        final String[] split = line.split(" ");
        this.cards = parseLabels(split[0], applyAdditionalRule);
        this.bid = Integer.parseInt(split[1]);
        this.type = HandType.valueByCards(this.cards);
    }

    private List<Card> parseLabels(final String labels, final boolean applyAdditionalRule) {
        return labels.chars().mapToObj(label -> (char) label).map(label -> Card.cardByLabel(label, applyAdditionalRule)).toList();
    }

    @Override
    public int compareTo(final Hand hand) {
        final int comparedTypes = this.type.compareTo(hand.getType());
        if (comparedTypes == 0) {
            for (int i = 0; i < this.cards.size(); i++) {
                final int comparedCards = this.cards.get(i).compareTo(hand.getCards().get(i));
                if (comparedCards == 0) {
                    continue;
                }
                return comparedCards;
            }
        }
        return comparedTypes;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
