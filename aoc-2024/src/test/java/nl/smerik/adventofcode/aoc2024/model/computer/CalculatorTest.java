package nl.smerik.adventofcode.aoc2024.model.computer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CalculatorTest {

    private static Stream<Arguments> provideSourceForGetInstructions() {
        return Stream.of(
                //@formatter:off
                Arguments.of(Collections.singletonList("mul(44,46)"), "mul(44,46)", false),
                Arguments.of(Collections.singletonList("mul(123,4)"), "mul(123,4)", false),
                Arguments.of(Collections.emptyList(), "mul(4*", false),
                Arguments.of(Collections.emptyList(), "mul(6,9!", false),
                Arguments.of(Collections.emptyList(), "?(12,34)", false),
                Arguments.of(Collections.emptyList(), "mul ( 2 , 4 )", false),
                Arguments.of(List.of("mul(2,4)", "mul(5,5)", "mul(11,8)", "mul(8,5)"), "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", false),
                Arguments.of(List.of("mul(2,4)", "mul(8,5)"), "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))", true),
                // Additional edge cases
                Arguments.of(List.of("mul(2,4)", "mul(8,5)"), "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))don't()mul(1,2)", true),
                Arguments.of(List.of("mul(2,4)", "mul(8,5)"), "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))don't()mul(1,2)do()", true)
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForGetInstructions")
    void getInstructions(final List<String> expectedInstructions, final String line, final boolean handleConditionalStatements) {
        final Calculator calculator = new Calculator(Collections.singletonList(line), handleConditionalStatements);
        assertIterableEquals(expectedInstructions, calculator.getInstructions());
    }


    private static Stream<Arguments> provideSourceForExecuteInstruction() {
        return Stream.of(
                //@formatter:off
                Arguments.of( 8, "mul(2,4)" ),
                Arguments.of(25, "mul(5,5)" ),
                Arguments.of(88, "mul(11,8)"),
                Arguments.of(40, "mul(8,5)" )
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForExecuteInstruction")
    void executeInstruction(final int expectedResult, final String instruction) {
        final Calculator calculator = new Calculator(Collections.emptyList(), false);
        assertEquals(expectedResult, calculator.executeInstruction(instruction));
    }


    private static Stream<Arguments> provideSourceForSumAllMultiplications() {
        return Stream.of(
                //@formatter:off
                Arguments.of(161, Collections.singletonList("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"), false),
                Arguments.of( 48, Collections.singletonList("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"), true)
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForSumAllMultiplications")
    void sumAllMultiplications(final long expectedResult, final List<String> lines, final boolean handleConditionalStatements) {
        final Calculator calculator = new Calculator(lines, handleConditionalStatements);
        assertEquals(expectedResult, calculator.sumAllMultiplications());
    }
}