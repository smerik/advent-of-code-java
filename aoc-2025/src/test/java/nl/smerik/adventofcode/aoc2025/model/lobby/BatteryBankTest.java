package nl.smerik.adventofcode.aoc2025.model.lobby;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BatteryBankTest {

    public static Stream<Arguments> example() {
        return Stream.of(
                // @formatter:off
                // Part 1
                Arguments.of("987654321111111",  2,           98L),
                Arguments.of("811111111111119",  2,           89L),
                Arguments.of("234234234234278",  2,           78L),
                Arguments.of("818181911112111",  2,           92L),
                // Part 2
                Arguments.of("987654321111111", 12, 987654321111L),
                Arguments.of("811111111111119", 12, 811111111119L),
                Arguments.of("234234234234278", 12, 434234234278L),
                Arguments.of("818181911112111", 12, 888911112111L)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("example")
    void testFindLargestJoltage(final String line, final int batteryCountToTurnOn, final long expected) {
        // Given
        BatteryBank bank = new BatteryBank(line);
        // When & Then
        assertEquals(expected, bank.findLargestJoltage(batteryCountToTurnOn));
    }
}