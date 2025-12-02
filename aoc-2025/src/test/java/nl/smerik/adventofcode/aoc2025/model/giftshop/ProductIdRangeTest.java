package nl.smerik.adventofcode.aoc2025.model.giftshop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductIdRangeTest {

    private static Stream<Arguments> exampleRanges() {
        return Stream.of(
                // @formatter:off
                // Single repeat
                Arguments.of(                "11-22", false, List.of(11L, 22L)   ),
                Arguments.of(               "95-115", false, List.of(99L)        ),
                Arguments.of(             "998-1012", false, List.of(1010L)      ),
                Arguments.of("1188511880-1188511890", false, List.of(1188511885L)),
                Arguments.of(        "222220-222224", false, List.of(222222L)    ),
                Arguments.of(      "1698522-1698528", false, List.of()           ),
                Arguments.of(        "446443-446449", false, List.of(446446L)    ),
                Arguments.of(    "38593856-38593862", false, List.of(38593859L)  ),
                Arguments.of(        "565653-565659", false, List.of()           ),
                Arguments.of(  "824824821-824824827", false, List.of()           ),
                Arguments.of("2121212118-2121212124", false, List.of()           ),
                // Multiple repeats
                Arguments.of(                "11-22", true , List.of(11L, 22L)   ),
                Arguments.of(               "95-115", true , List.of(99L, 111L)  ),
                Arguments.of(             "998-1012", true , List.of(999L, 1010L)),
                Arguments.of("1188511880-1188511890", true , List.of(1188511885L)),
                Arguments.of(        "222220-222224", true , List.of(222222L)    ),
                Arguments.of(      "1698522-1698528", true , List.of()           ),
                Arguments.of(        "446443-446449", true , List.of(446446L)    ),
                Arguments.of(    "38593856-38593862", true , List.of(38593859L)  ),
                Arguments.of(        "565653-565659", true , List.of(565656L)    ),
                Arguments.of(  "824824821-824824827", true , List.of(824824824L) ),
                Arguments.of("2121212118-2121212124", true , List.of(2121212121L))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("exampleRanges")
    void testFindInvalidIds(final String range, final boolean shouldHaveMultipleRepeats, final List<Long> expected) {
        // Given
        final ProductIdRange idRange = new ProductIdRange(range);
        // When & Then
        assertEquals(expected, idRange.findInvalidIds(shouldHaveMultipleRepeats));
    }

    private static Stream<Arguments> exampleIds() {
        return Stream.of(
                // @formatter:off
                // Single repeat
                Arguments.of(    55L, false ,true ),
                Arguments.of(  6464L, false, true ),
                Arguments.of(123123L, false, true ),
                Arguments.of(   101L, false, false),
                // Multiple repeats
                Arguments.of(  12341234L, true , true ),
                Arguments.of( 123123123L, true , true ),
                Arguments.of(1212121212L, true , true ),
                Arguments.of(   1111111L, true , true )
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("exampleIds")
    void testIsInvalid(final long id, final boolean shouldHaveMultipleRepeats, final boolean expected) {
        // Given, When & Then
        assertEquals(expected, ProductIdRange.isInvalid(id, shouldHaveMultipleRepeats));
    }
}