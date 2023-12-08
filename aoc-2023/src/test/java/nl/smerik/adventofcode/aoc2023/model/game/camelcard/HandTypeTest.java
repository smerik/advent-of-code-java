package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2023.model.game.camelcard.Card.*;
import static nl.smerik.adventofcode.aoc2023.model.game.camelcard.HandType.*;
import static org.junit.jupiter.api.Assertions.*;

class HandTypeTest {

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "FIVE_OF_A_KIND , FOUR_OF_A_KIND ",
            "FOUR_OF_A_KIND , FULL_HOUSE     ",
            "FULL_HOUSE     , THREE_OF_A_KIND",
            "THREE_OF_A_KIND, TWO_PAIR       ",
            "TWO_PAIR       , ONE_PAIR       ",
            "ONE_PAIR       , HIGH_CARD      "
            // @formatter:on
    })
    void testOrder(final HandType type1, final HandType type2) {
        assertTrue(type1.compareTo(type2) < 0);
    }

    private static Stream<Arguments> valueByCardsSource() {
        return Stream.of(
                // @formatter:off
                // Five of a kind
                Arguments.of(FIVE_OF_A_KIND , List.of(ACE  , ACE  , ACE  , ACE  , ACE  )),
                Arguments.of(FIVE_OF_A_KIND , List.of(JOKER, JOKER, JOKER, JOKER, JOKER)),
                // Four of a kind
                Arguments.of(FOUR_OF_A_KIND , List.of(ACE  , ACE  , EIGHT, ACE  , ACE  )),
                Arguments.of(FOUR_OF_A_KIND , List.of(QUEEN, JOKER, JOKER, QUEEN, TWO  )),
                Arguments.of(FOUR_OF_A_KIND , List.of(TEN  , FIVE , FIVE , JOKER, FIVE )),
                Arguments.of(FOUR_OF_A_KIND , List.of(KING , TEN  , JOKER, JOKER, TEN  )),
                Arguments.of(FOUR_OF_A_KIND , List.of(QUEEN, QUEEN, QUEEN, JOKER, ACE  )),
                // Full house
                Arguments.of(FULL_HOUSE     , List.of(TWO  , THREE, THREE, THREE, TWO  )),
                // Three of a kind
                Arguments.of(THREE_OF_A_KIND, List.of(TEN  , TEN  , TEN  , NINE , EIGHT)),
                Arguments.of(THREE_OF_A_KIND, List.of(TEN  , FIVE , FIVE , JACK , FIVE )),
                Arguments.of(THREE_OF_A_KIND, List.of(QUEEN, QUEEN, QUEEN, JACK , ACE  )),
                // Two pair
                Arguments.of(TWO_PAIR       , List.of(TWO  , THREE, FOUR , THREE, TWO  )),
                Arguments.of(TWO_PAIR       , List.of(KING , KING , SIX  , SEVEN, SEVEN)),
                Arguments.of(TWO_PAIR       , List.of(KING , TEN  , JACK , JACK , TEN  )),
                Arguments.of(TWO_PAIR       , List.of(QUEEN, JACK , JACK,  QUEEN, TWO  )),
                // One pair
                Arguments.of(ONE_PAIR       , List.of(ACE  , TWO  , THREE, ACE  , FOUR )),
                Arguments.of(ONE_PAIR       , List.of(THREE, TWO  , TEN, THREE  , KING )),
                // High card
                Arguments.of(HIGH_CARD      , List.of(TWO  , THREE, FOUR , FIVE , SIX  ))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("valueByCardsSource")
    void testValueByCards(final HandType expectedType, final List<Card> cards) {
        assertEquals(expectedType, HandType.valueByCards(cards));
    }
}