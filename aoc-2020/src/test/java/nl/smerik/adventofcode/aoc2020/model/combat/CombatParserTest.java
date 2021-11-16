package nl.smerik.adventofcode.aoc2020.model.combat;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombatParserTest {

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
    void testParse() {
        final CombatGame game = new CombatGame(EXAMPLE_01_STARTING_DECKS);
        assertEquals(2, game.getPlayers().size());

        final CombatPlayer player1 = game.getPlayers().stream().filter(p -> p.getId() == 1).findAny().orElseThrow();
        assertEquals(5, player1.getDeck().size());
        assertTrue(player1.getDeck().containsAll(List.of(9, 2, 6, 3, 1)));


        final CombatPlayer player2 = game.getPlayers().stream().filter(p -> p.getId() == 2).findAny().orElseThrow();
        assertEquals(5, player2.getDeck().size());
        assertTrue(player2.getDeck().containsAll(List.of(5, 8, 4, 7, 10)));
    }
}