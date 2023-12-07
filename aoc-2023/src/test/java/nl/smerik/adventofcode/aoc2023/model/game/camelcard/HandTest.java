package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2023.model.game.camelcard.Card.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    private static Hand firstHand;

    @BeforeAll
    static void beforeAll() {
        firstHand = new Hand("32T3K 765");
    }

    @Test
    void testGetCards() {
        final List<Card> expectedCards = List.of(THREE, TWO, TEN, THREE, KING);
        assertEquals(expectedCards, firstHand.getCards());
    }

    @Test
    void testGetBid() {
        assertEquals(765, firstHand.getBid());
    }

    @Test
    void testGetType() {
        assertEquals(HandType.ONE_PAIR, firstHand.getType());
    }


    @Test
    void testCompareTo() {
        final Hand hand1 = new Hand("KK677 28");
        final Hand hand2 = new Hand("KTJJT 220");
        assertTrue(hand1.compareTo(hand2) < 0);

        final Hand hand3 = new Hand("T55J5 684");
        final Hand hand4 = new Hand("QQQJA 483");
        assertTrue(hand1.compareTo(hand2) < 0);
    }
}