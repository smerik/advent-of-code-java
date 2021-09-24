package nl.smerik.adventofcode.aoc2020.service.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MathServiceTest {

    @Autowired
    private MathService mathService;

    private static Stream<Arguments> provideSourceForEvaluate() {
        return Stream.of(
                // @formatter:off
                // Day 18 - part 01
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6"                          , false,     71L),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))"                    , false,     51L),
                Arguments.of("2 * 3 + (4 * 5)"                                , false,     26L),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)"                    , false,    437L),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"      , false,  12240L),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", false,  13632L),
                // Day 18 - part 02
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6"                          , true ,    231L),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))"                    , true ,     51L),
                Arguments.of("2 * 3 + (4 * 5)"                                , true ,     46L),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)"                    , true ,   1445L),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"      , true , 669060L),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", true ,  23340L)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForEvaluate")
    void evaluate(final String expression, final boolean useAdvancedPrecedence, final Long expectedValue) {
        assertEquals(expectedValue, mathService.evaluate(expression, useAdvancedPrecedence));
    }

    @Test
    void sumEvaluation() {
        final List<String> expressions = List.of(
                "1 + 2 * 3 + 4 * 5 + 6",
                "1 + (2 * 3) + (4 * (5 + 6))",
                "2 * 3 + (4 * 5)",
                "5 + (8 * 3 + 9 + 3 * 4 * 3)",
                "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))",
                "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        );
        assertEquals(26457L, mathService.sumEvaluation(expressions, false));
    }
}