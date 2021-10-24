package nl.smerik.adventofcode.aoc2020.model.combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombatPlayerTest {

    @Test
    void testAddCard() {
        final CombatPlayer player = new CombatPlayer(1);
        player.addCard(9);
        player.addCard(2);
        player.addCard(6);

        assertEquals(3, player.getDeck().size());
        assertEquals(9, player.getDeck().peek());

    }

    @Test
    void testDrawCard() {
        final CombatPlayer player = new CombatPlayer(1);
        player.addCard(9);
        player.addCard(2);
        player.addCard(6);

        assertEquals(3, player.getDeck().size());
        assertEquals(9, player.drawCard());
        assertEquals(2, player.getDeck().size());
    }

    @Test
    void testDeckIsEmpty() {
        final CombatPlayer player = new CombatPlayer(1);
        assertTrue(player.deckIsEmpty());

        player.addCard(9);
        assertFalse(player.deckIsEmpty());
    }

    @Test
    void calculateScore() {
        final CombatPlayer player = new CombatPlayer(2);
        player.addCard(3);
        player.addCard(2);
        player.addCard(10);
        player.addCard(6);
        player.addCard(8);
        player.addCard(5);
        player.addCard(9);
        player.addCard(4);
        player.addCard(7);
        player.addCard(1);

        assertEquals(306, player.calculateScore());
    }
}