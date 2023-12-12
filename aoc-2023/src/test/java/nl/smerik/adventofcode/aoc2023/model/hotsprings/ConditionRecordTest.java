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
                // Copy count: 1
                Arguments.of(     1, "???.### 1,1,3"            , 1),
                Arguments.of(     4, ".??..??...?##. 1,1,3"     , 1),
                Arguments.of(     1, "?#?#?#?#?#?#?#? 1,3,1,6"  , 1),
                Arguments.of(     1, "????.#...#... 4,1,1"      , 1),
                Arguments.of(     4, "????.######..#####. 1,6,5", 1),
                Arguments.of(    10, "?###???????? 3,2,1"       , 1),
                // Copy count: 5
                Arguments.of(     1, "???.### 1,1,3"            , 5),
                Arguments.of( 16384, ".??..??...?##. 1,1,3"     , 5),
                Arguments.of(     1, "?#?#?#?#?#?#?#? 1,3,1,6"  , 5),
                Arguments.of(    16, "????.#...#... 4,1,1"      , 5),
                Arguments.of(  2500, "????.######..#####. 1,6,5", 5),
                Arguments.of(506250, "?###???????? 3,2,1"       , 5)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("countPossibleArrangementsSource")
    void testCountPossibleArrangements(final long expectedCount, final String line, final int copyCount) {
        final ConditionRecord record = new ConditionRecord(line, copyCount);
        assertEquals(expectedCount, record.countPossibleArrangements());
    }
}