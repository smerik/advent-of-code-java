package nl.smerik.adventofcode.aoc2025.model.giftshop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductIdRangeTest {

    private static Stream<Arguments> examplePart01() {
        return Stream.of(
                // @formatter:off
                Arguments.of(                "11-22", List.of(11L, 22L)   ),
                Arguments.of(               "95-115", List.of(99L)        ),
                Arguments.of(             "998-1012", List.of(1010L)      ),
                Arguments.of("1188511880-1188511890", List.of(1188511885L)),
                Arguments.of(        "222220-222224", List.of(222222L)    ),
                Arguments.of(      "1698522-1698528", List.of()           ),
                Arguments.of(        "446443-446449", List.of(446446L)    ),
                Arguments.of(    "38593856-38593862", List.of(38593859L)  ),
                Arguments.of(        "565653-565659", List.of()           ),
                Arguments.of(  "824824821-824824827", List.of()           ),
                Arguments.of("2121212118-2121212124", List.of()           )
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("examplePart01")
    void testFindInvalidIds(final String range, final List<Long> expected) {
        // Given
        final ProductIdRange idRange = new ProductIdRange(range);
        // When & Then
        assertEquals(expected, idRange.findInvalidIds());
    }
}