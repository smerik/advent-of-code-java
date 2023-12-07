package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CamelCardsGameTest {

    private final List<String> EXAMPLE_01 = List.of(
            "32T3K 765",
            "T55J5 684",
            "KK677 28 ",
            "KTJJT 220",
            "QQQJA 483"
    );

    @Test
    void testDetermineTotalWinnings() {
        // Given
        final CamelCardsGame game = new CamelCardsGame(EXAMPLE_01);
        // When
        assertEquals(6440, game.determineTotalWinnings());
    }

    @Test
    void testSortHandsInOrderOfStrength() {
        // Given
        // Compare by bid for easier test setup
        final List<Integer> expectedOrderOfBids = List.of(765, 220, 28, 684, 483);
        final CamelCardsGame game = new CamelCardsGame(EXAMPLE_01);
        // When
        List<Hand> result = game.sortHandsInOrderOfStrength();
        // Then
        assertEquals(expectedOrderOfBids, result.stream().map(Hand::getBid).toList());
    }
}