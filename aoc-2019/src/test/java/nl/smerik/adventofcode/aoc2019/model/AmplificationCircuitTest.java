package nl.smerik.adventofcode.aoc2019.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmplificationCircuitTest {

    private static Stream<Arguments> provideSourceForDay07Part01Test01() {
        return Stream.of(
                Arguments.of(43210, List.of(4, 3, 2, 1, 0), new long[]{3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0}),
                Arguments.of(54321, List.of(0, 1, 2, 3, 4), new long[]{3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0}),
                Arguments.of(65210, List.of(1, 0, 4, 3, 2), new long[]{3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0})
        );
    }

    private static Stream<Arguments> provideSourceForDay07Part02Test01() {
        return Stream.of(
                Arguments.of(139629729, List.of(9, 8, 7, 6, 5), new long[]{3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5}),
                Arguments.of(18216, List.of(9, 7, 8, 5, 6), new long[]{3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54, -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4, 53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10})
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDay07Part01Test01")
    void solveDay07Part01Test01(long output, List<Integer> phaseSequence, long[] program) {
        final AmplificationCircuit circuit = new AmplificationCircuit(program, phaseSequence);
        assertEquals(output, circuit.run(Collections.singletonList(0L)).get(0));
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDay07Part02Test01")
    void solveDay07Part02Test01(long output, List<Integer> phaseSequence, long[] program) {
        final AmplificationCircuit circuit = new AmplificationCircuit(program, phaseSequence);
        assertEquals(output, circuit.run(Collections.singletonList(0L)).get(0));
    }
}