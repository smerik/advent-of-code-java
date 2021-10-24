package nl.smerik.adventofcode.aoc2020.model.combat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

@Slf4j
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CombatPlayer {

    @EqualsAndHashCode.Include
    final int id;

    private final Queue<Integer> deck;

    public CombatPlayer(final int id) {
        this.id = id;
        deck = new ArrayDeque<>();
    }

    /**
     * Places given card to the bottom of the deck.
     *
     * @param cardValue the card's value
     */
    public void addCard(final int cardValue) {
        deck.add(cardValue);
    }

    /**
     * Adds all given cards to the bottom of the deck.
     *
     * @param cards the cards to add
     */
    public void addCards(final List<Integer> cards) {
        deck.addAll(cards);
    }

    /**
     * Draws the top card of the deck.
     *
     * @return the top card
     */
    public int drawCard() {
        final Integer cardValue = deck.remove();
        LOG.debug("Player {} plays: {}", id, cardValue);
        return cardValue;
    }

    /**
     * @return <code>true</code> if the deck is empty; <code>false</code> otherwise
     */
    public boolean deckIsEmpty() {
        return deck.isEmpty();
    }

    /**
     * Calculates the player's score.
     * The bottom card in the deck is worth the value of the card multiplied by 1,
     * the second-from-the-bottom card is worth the value of the card multiplied by 2,
     * and so on.
     *
     * @return the score
     */
    public int calculateScore() {
        int result = 0;

        final Iterator<Integer> deckIterator = deck.iterator();
        for (int i = deck.size(); i > 0; i--) {
            result += deckIterator.next() * i;
        }
        return result;
    }
}
