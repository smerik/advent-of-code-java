package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "ACE  , A, false",
            "KING , K, false",
            "QUEEN, Q, false",
            "JACK , J, false",
            "JOKER, J, true",
            "TEN  , T, false",
            "NINE , 9, false",
            "EIGHT, 8, false",
            "SEVEN, 7, false",
            "SIX  , 6, false",
            "FIVE , 5, false",
            "FOUR , 4, false",
            "THREE, 3, false",
            "TWO  , 2, false"
            // @formatter:on
    })
    void testCardByLabel(final Card expectedCard, final char label, final boolean applyAdditionalRule) {
        assertEquals(expectedCard, Card.cardByLabel(label, applyAdditionalRule));
    }

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "ACE  , KING",
            "KING , QUEEN",
            "QUEEN, JACK",
            "JACK , TEN",
            "TEN  , NINE",
            "NINE , EIGHT",
            "EIGHT, SEVEN",
            "SEVEN, SIX",
            "SIX  , FIVE",
            "FIVE , FOUR",
            "FOUR , THREE",
            "THREE, TWO",
            "TWO  , JOKER"
            // @formatter:on
    })
    void testOrder(final Card card1, final Card card2) {
        assertTrue(card1.compareTo(card2) < 0);
    }
}