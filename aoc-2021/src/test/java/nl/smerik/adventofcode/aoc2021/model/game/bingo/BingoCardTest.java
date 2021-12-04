package nl.smerik.adventofcode.aoc2021.model.game.bingo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BingoCardTest {

    private static final List<String> THIRD_CARD_EXAMPLE_PART_01 = List.of(
            "14 21 17 24  4",
            "10 16 15  9 19",
            "18  8 23 26 20",
            "22 11 13  6  5",
            " 2  0 12  3  7"
    );

    @Test
    void testInit() {
        final BingoCard card = new BingoCard(THIRD_CARD_EXAMPLE_PART_01);

        final int[][] cardNumbers = card.getCardNumbers();
        // @formatter:off
        assertArrayEquals(new int[]{14, 21, 17, 24,  4}, cardNumbers[0]);
        assertArrayEquals(new int[]{10, 16, 15,  9, 19}, cardNumbers[1]);
        assertArrayEquals(new int[]{18,  8, 23, 26, 20}, cardNumbers[2]);
        assertArrayEquals(new int[]{22, 11, 13,  6,  5}, cardNumbers[3]);
        assertArrayEquals(new int[]{ 2,  0, 12,  3,  7}, cardNumbers[4]);
        // @formatter:on

        assertTrue(card.getMarkedNumbers().isEmpty());
    }

    @Test
    void testMarkNumber() {
        final BingoCard card = new BingoCard(THIRD_CARD_EXAMPLE_PART_01);
        // try to mark a number that does not exist on the card first
        assertFalse(card.markNumber(1));
        assertTrue(card.getMarkedNumbers().isEmpty());

        assertFalse(card.markNumber(14));
        assertFalse(card.markNumber(21));
        assertFalse(card.markNumber(17));
        assertFalse(card.markNumber(24));
        assertTrue(card.markNumber(4)); // BINGO!
        assertEquals(List.of(14, 21, 17, 24, 4), card.getMarkedNumbers());
    }


    private static Stream<Arguments> isCompleted() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, Collections.emptyList())    , // marked none
                Arguments.of(false, List.of(14, 16, 23,  6,  7)), // marked diagonally
                Arguments.of(true , List.of( 2,  0, 12,  3,  7)), // marked by row
                Arguments.of(true , List.of( 4, 19, 20,  5,  7))  // marked by column
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("isCompleted")
    void testIsCompleted(final boolean expectedCompleted, final List<Integer> numbersToMark) {
        final BingoCard card = new BingoCard(THIRD_CARD_EXAMPLE_PART_01);
        markNumbers(card, numbersToMark);
        assertEquals(expectedCompleted, card.isCompleted());
    }

    @Test
    void testCalculateScore() {
        final BingoCard card = new BingoCard(THIRD_CARD_EXAMPLE_PART_01);
        markNumbers(card, List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24)); // draw numbers by example part 01
        assertEquals(4512, card.calculateScore());
    }

    private void markNumbers(final BingoCard card, final List<Integer> numbersToMark) {
        for (final Integer numberToMark : numbersToMark) {
            card.markNumber(numberToMark);
        }
    }
}