package nl.smerik.adventofcode.aoc2023.model.game.scratchcard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScratchCardGameTest {

    private static Stream<Arguments> cardNumberSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(  1, "Card   1: 66 92  4 54 39 76 49 27 61 56 | 66 59 85 54 61 86 37 49  6 18 81 39  4 56  2 48 76 72 71 25 27 67 10 92 13"),
                Arguments.of(199, "Card 199: 16 23 27 61 14 11 89 80 98 88 | 57 48 25 66 72 45  2 33 93  9 73 58 94 79 40  6 47 19 78 69 70  8 30 29 34")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("cardNumberSource")
    public void testCardNumber(final int expectedCardNumber, final String line) {
        final ScratchCardGame game = new ScratchCardGame(line);
        assertEquals(expectedCardNumber, game.getCardNumber());
    }

    private static final List<String> CARDS_EXAMPLE_01 = List.of(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
    );

    private static Stream<Arguments> findWinningNumbersSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(Set.of(48, 83, 17, 86), 1),
                Arguments.of(Set.of(        32, 61), 2),
                Arguments.of(Set.of(         1, 21), 3),
                Arguments.of(Set.of(            84), 4),
                Arguments.of(Set.of(              ), 5),
                Arguments.of(Set.of(              ), 6)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findWinningNumbersSource")
    void testFindWinningNumbers(final Set<Integer> expectedWinningNumbers, final int cardNumber) {
        final ScratchCardGame game = new ScratchCardGame(CARDS_EXAMPLE_01.get(cardNumber - 1));
        assertEquals(expectedWinningNumbers, game.findWinningNumbers());
    }

    private static Stream<Arguments> determinePointsSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(8, 1),
                Arguments.of(2, 2),
                Arguments.of(2, 3),
                Arguments.of(1, 4),
                Arguments.of(0, 5),
                Arguments.of(0, 6)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("determinePointsSource")
    void testDeterminePoints(final int expectedPoints, final int cardNumber) {
        final ScratchCardGame game = new ScratchCardGame(CARDS_EXAMPLE_01.get(cardNumber - 1));
        assertEquals(expectedPoints, game.determinePoints());
    }
}