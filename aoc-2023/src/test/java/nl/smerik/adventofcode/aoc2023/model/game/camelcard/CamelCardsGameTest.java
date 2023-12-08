package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CamelCardsGameTest {

    private final List<String> EXAMPLE_01 = List.of(
            "32T3K 765",
            "T55J5 684",
            "KK677 28 ",
            "KTJJT 220",
            "QQQJA 483"
    );

    @ParameterizedTest
    @CsvSource({
            "6440, false",
            "5905, true "
    })
    void testDetermineTotalWinnings() {
        // Given
        final CamelCardsGame game = new CamelCardsGame(EXAMPLE_01, false);
        // When
        assertEquals(6440, game.determineTotalWinnings());
    }

    private static Stream<Arguments> sortHandsInOrderOfStrengthSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(List.of(765, 220,  28, 684, 483), false),
                Arguments.of(List.of(765,  28, 684, 483, 220), true )
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("sortHandsInOrderOfStrengthSource")
    void testSortHandsInOrderOfStrength(final List<Integer> expectedOrderOfBids, final boolean applyAdditionalRule) {
        final CamelCardsGame game = new CamelCardsGame(EXAMPLE_01, applyAdditionalRule);
        // When
        List<Hand> result = game.sortHandsInOrderOfStrength();
        // Then
        // Compare by bid for easier test setup
        assertEquals(expectedOrderOfBids, result.stream().map(Hand::getBid).toList());
    }
}