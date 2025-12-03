package nl.smerik.adventofcode.aoc2025.model.lobby;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmergencyPowerTest {

    @Value("classpath:input/day-03/example-01.txt")
    private Resource example01Resource;

    public static Stream<Arguments> example() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 2,           357L),
                Arguments.of(12, 3121910778619L)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("example")
    void calcTotalOutputJoltage(final int batteryCountToTurnOn, final long expected) {
        // Given
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final EmergencyPower power = new EmergencyPower(lines);
        // When & Then
        assertEquals(expected, power.calcTotalOutputJoltage(batteryCountToTurnOn));
    }
}