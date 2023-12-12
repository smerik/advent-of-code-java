package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionRecordTest {

    public static Stream<Arguments> countPossibleArrangementsSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 1, "???.### 1,1,3"),
                Arguments.of( 4, ".??..??...?##. 1,1,3"),
                Arguments.of( 1, "?#?#?#?#?#?#?#? 1,3,1,6"),
                Arguments.of( 1, "????.#...#... 4,1,1"),
                Arguments.of( 4, "????.######..#####. 1,6,5"),
                Arguments.of(10, "?###???????? 3,2,1")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("countPossibleArrangementsSource")
    void testCountPossibleArrangements(final int expectedCount, final String line) {
        final ConditionRecord record = new ConditionRecord(line);
        assertEquals(expectedCount, record.countPossibleArrangements());
    }
}