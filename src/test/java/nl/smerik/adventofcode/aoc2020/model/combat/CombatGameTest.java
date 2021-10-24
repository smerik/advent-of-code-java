package nl.smerik.adventofcode.aoc2020.model.combat;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombatGameTest {

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
    void testConstructor() {
        final CombatGame game = new CombatGame(EXAMPLE_01_STARTING_DECKS);
        assertEquals(2, game.getPlayers().size());

        final CombatPlayer player1 = game.getPlayers().stream().filter(p -> p.getId() == 1).findAny().orElseThrow();
        assertEquals(5, player1.getDeck().size());
        assertTrue(player1.getDeck().containsAll(List.of(9, 2, 6, 3, 1)));


        final CombatPlayer player2 = game.getPlayers().stream().filter(p -> p.getId() == 2).findAny().orElseThrow();
        assertEquals(5, player2.getDeck().size());
        assertTrue(player2.getDeck().containsAll(List.of(5, 8, 4, 7, 10)));
    }

    @Test
    void testPlay() {
        final CombatGame game = new CombatGame(EXAMPLE_01_STARTING_DECKS);
        final CombatPlayer player1 = game.getPlayers().stream().filter(p -> p.getId() == 1).findAny().orElseThrow();
        final CombatPlayer player2 = game.getPlayers().stream().filter(p -> p.getId() == 2).findAny().orElseThrow();

        final CombatPlayer winner = game.play();
        assertEquals(player1, winner);
        assertTrue(player1.getDeck().containsAll(List.of(2, 6, 3, 1, 9, 5)));
        assertTrue(player2.getDeck().containsAll(List.of(8, 4, 7, 10)));

        final ArrayDeque<Integer> deck = new ArrayDeque<>(player1.getDeck());
        assertEquals(5, deck.removeLast());
        assertEquals(9, deck.removeLast());
    }

    @Test
    void testPlayUntilGameEnds() {
        final CombatGame game = new CombatGame(EXAMPLE_01_STARTING_DECKS);
        final CombatPlayer player1 = game.getPlayers().stream().filter(p -> p.getId() == 1).findAny().orElseThrow();
        final CombatPlayer player2 = game.getPlayers().stream().filter(p -> p.getId() == 2).findAny().orElseThrow();

        final CombatPlayer winner = game.playUntilGameEnds();
        assertEquals(winner, player2);

        assertTrue(player1.getDeck().isEmpty());

        assertEquals(10, player2.getDeck().size());
        final Queue<Integer> deck = new ArrayDeque<>(player2.getDeck());
        assertEquals(3, deck.poll());
        assertEquals(2, deck.poll());
        assertEquals(10, deck.poll());
        assertEquals(6, deck.poll());
        assertEquals(8, deck.poll());
        assertEquals(5, deck.poll());
        assertEquals(9, deck.poll());
        assertEquals(4, deck.poll());
        assertEquals(7, deck.poll());
        assertEquals(1, deck.poll());

        assertEquals(306, winner.calculateScore()); // sanity test
    }
}