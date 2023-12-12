package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionTypeTest {


    public static Stream<Arguments> valueOfConditionTokenSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(ConditionType.OPERATIONAL, '.'),
                Arguments.of(ConditionType.DAMAGED    , '#'),
                Arguments.of(ConditionType.UNKNOWN    , '?')
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("valueOfConditionTokenSource")
    void testValueOfConditionToken(final ConditionType expectedType, final char token) {
        assertEquals(expectedType, ConditionType.valueOfConditionToken(token));
    }
}