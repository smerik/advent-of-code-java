package nl.smerik.adventofcode.aoc2020.model.combat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@Slf4j
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CombatPlayer {

    @EqualsAndHashCode.Include
    final int id;

    private final Queue<Integer> deck;
    private final Set<List<Integer>> previousDeckStates;

    public CombatPlayer(final int id) {
        this.id = id;
        this.deck = new ArrayDeque<>();
        this.previousDeckStates = new HashSet<>();
    }

    public CombatPlayer(final CombatPlayer player, final int numberOfCardsToCopy) {
        this(player.id);

        final Queue<Integer> tempDeck = new ArrayDeque<>(player.deck);
        for (int i = 0; i < numberOfCardsToCopy; i++) {
            addCard(tempDeck.remove());
        }
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
     * Checks if the current card order in the deck has happened before.
     *
     * @return <code>true</code> if happened before; <code>false</code> otherwise
     */
    public boolean isDeckInSameOrderAsBefore() {
        return previousDeckStates.contains(new ArrayList<>(deck));
    }

    /**
     * Draws the top card of the deck.
     *
     * @return the top card
     */
    public int drawCard() {
        previousDeckStates.add(new ArrayList<>(deck));
        final Integer cardValue = deck.remove();
        LOG.trace("Player {} plays: {}", id, cardValue);
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
