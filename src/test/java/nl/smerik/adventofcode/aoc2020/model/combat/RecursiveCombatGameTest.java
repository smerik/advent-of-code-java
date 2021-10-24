package nl.smerik.adventofcode.aoc2020.model.combat;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveCombatGameTest {

    private final List<String> EXAMPLE_01_STARTING_DECKS = List.of(
            "Player 1:",
            "9",
            "2",
            "6",
            "3",
            "1",
            "",
            "Player 2:",
            "5",
            "8",
            "4",
            "7",
            "10"
    );

    @Test
    void testPlayUntilGameEnds() {
        final RecursiveCombatGame game = new RecursiveCombatGame(EXAMPLE_01_STARTING_DECKS);
        final CombatPlayer player2 = game.getPlayers().stream().filter(p -> p.getId() == 2).findAny().orElseThrow();

        final CombatPlayer winner = game.playUntilGameEnds();

        assertEquals(winner, player2);

        assertEquals(10, winner.getDeck().size());
        final Queue<Integer> deck = new ArrayDeque<>(winner.getDeck());
        assertEquals(7, deck.poll());
        assertEquals(5, deck.poll());
        assertEquals(6, deck.poll());
        assertEquals(2, deck.poll());
        assertEquals(4, deck.poll());
        assertEquals(1, deck.poll());
        assertEquals(10, deck.poll());
        assertEquals(8, deck.poll());
        assertEquals(9, deck.poll());
        assertEquals(3, deck.poll());

        assertEquals(291, winner.calculateScore()); // sanity test
    }
}