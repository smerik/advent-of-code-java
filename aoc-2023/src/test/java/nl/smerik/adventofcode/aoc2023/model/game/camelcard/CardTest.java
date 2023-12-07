package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "ACE  , A",
            "KING , K",
            "QUEEN, Q",
            "JACK, J",
            "TEN  , T",
            "NINE , 9",
            "EIGHT, 8",
            "SEVEN, 7",
            "SIX  , 6",
            "FIVE , 5",
            "FOUR , 4",
            "THREE, 3",
            "TWO  , 2"
            // @formatter:on
    })
    void testCardByLabel(final Card expectedCard, final char label) {
        assertEquals(expectedCard, Card.cardByLabel(label));
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
            "THREE, TWO"
            // @formatter:on
    })
    void testOrder(final Card card1, final Card card2) {
        assertTrue(card1.compareTo(card2) < 0);
    }
}