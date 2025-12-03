package nl.smerik.adventofcode.aoc2025.model.lobby;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BatteryBankTest {

    public static Stream<Arguments> example01() {
        return Stream.of(
                // @formatter:off
                Arguments.of("987654321111111", 98),
                Arguments.of("811111111111119", 89),
                Arguments.of("234234234234278", 78),
                Arguments.of("818181911112111", 92)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("example01")
    void testFindLargestJoltage(final String line, final int expected) {
        // Given
        BatteryBank bank = new BatteryBank(line);
        // When & Then
        assertEquals(expected, bank.findLargestJoltage());
    }
}