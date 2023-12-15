package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsciiStringHelperTest {

    public static Stream<Arguments> hashSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 52, "HASH"),
                Arguments.of( 30, "rn=1"),
                Arguments.of(253, "cm-" ),
                Arguments.of( 97, "qp=3"),
                Arguments.of( 47, "cm=2"),
                Arguments.of( 14, "qp-" ),
                Arguments.of(180, "pc=4"),
                Arguments.of(  9, "ot=9"),
                Arguments.of(197, "ab=5"),
                Arguments.of( 48, "pc-" ),
                Arguments.of(214, "pc=6"),
                Arguments.of(231, "ot=7")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("hashSource")
    void testHash(final int expectedResult, final String text) {
        assertEquals(expectedResult, AsciiStringHelper.hash(text));
    }
}