package nl.smerik.adventofcode.aoc2024.model.bridge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationTest {

    private static Stream<Arguments> provideSourceForIsEquationTrue() {
        return Stream.of(
                //@formatter:off
                Arguments.of(true ,    190L, List.of(10L, 19L)          , false),
                Arguments.of(true ,   3267L, List.of(81L, 40L, 27L)     , false),
                Arguments.of(false,     83L, List.of(17L,  5L)          , false),
                Arguments.of(false,    156L, List.of(15L,  6L)          , false),
                Arguments.of(false,   7290L, List.of( 6L,  8L,  6L, 15L), false),
                Arguments.of(false, 161011L, List.of(16L, 10L, 13L)     , false),
                Arguments.of(false,    192L, List.of(17L,  8L, 14L)     , false),
                Arguments.of(false,  21037L, List.of( 9L,  7L, 18L, 13L), false),
                Arguments.of(true ,    292L, List.of(11L,  6L, 16L, 20L), false),
                // Apply the concatenation operator
                Arguments.of(true ,    190L, List.of(10L, 19L)          , true ),
                Arguments.of(true ,   3267L, List.of(81L, 40L, 27L)     , true ),
                Arguments.of(false,     83L, List.of(17L,  5L)          , true ),
                Arguments.of(true ,    156L, List.of(15L,  6L)          , true ),
                Arguments.of(true ,   7290L, List.of( 6L,  8L,  6L, 15L), true ),
                Arguments.of(false, 161011L, List.of(16L, 10L, 13L)     , true ),
                Arguments.of(true ,    192L, List.of(17L,  8L, 14L)     , true ),
                Arguments.of(false,  21037L, List.of( 9L,  7L, 18L, 13L), true ),
                Arguments.of(true ,    292L, List.of(11L,  6L, 16L, 20L), true )
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForIsEquationTrue")
    void testIsEquationTrue(
            final boolean expectedResult,
            final Long testValue,
            final List<Long> numbers,
            final boolean applyConcatenationOperator) {
        final Equation equation = new Equation(testValue, numbers);
        assertEquals(expectedResult, equation.isEquationTrue(applyConcatenationOperator));
    }
}