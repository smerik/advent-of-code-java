package nl.smerik.adventofcode.aoc2019.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmplificationCircuitServiceTest {

    private static Stream<Arguments> provideSourceForDay07Part01Test01() {
        return Stream.of(
                Arguments.of(43210, new int[]{3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0}),
                Arguments.of(54321, new int[]{3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0}),
                Arguments.of(65210, new int[]{3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0})
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDay07Part01Test01")
    void solveDay07Part01Test01(int output, int[] program) {
        final AmplificationCircuitService service = new AmplificationCircuitService();
        assertEquals(output, service.determineLargestOutputSignal(program));
    }
}