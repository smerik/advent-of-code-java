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
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6"                          ,    71L),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))"                    ,    51L),
                Arguments.of("2 * 3 + (4 * 5)"                                ,    26L),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)"                    ,   437L),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"      , 12240L),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632L)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForEvaluate")
    void evaluate(final String expression, final Long expectedValue) {
        assertEquals(expectedValue, mathService.evaluate(expression));
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
        assertEquals(26457L, mathService.sumEvaluation(expressions));
    }
}