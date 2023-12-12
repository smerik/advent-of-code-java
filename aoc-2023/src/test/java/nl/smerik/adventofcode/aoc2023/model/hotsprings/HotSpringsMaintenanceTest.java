package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotSpringsMaintenanceTest {

    private static final List<String> INPUT_EXAMPLE_01 = List.of(
            // @formatter:off
            "???.### 1,1,3",
            ".??..??...?##. 1,1,3",
            "?#?#?#?#?#?#?#? 1,3,1,6",
            "????.#...#... 4,1,1",
            "????.######..#####. 1,6,5",
            "?###???????? 3,2,1"
            // @formatter:on
    );

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "    21, 1",
            "525152, 5"
            // @formatter:on
    })
    void sumPossibleArrangements(final long expectedResult, final int copyCount) {
        final HotSpringsMaintenance maintenance = new HotSpringsMaintenance(INPUT_EXAMPLE_01, copyCount);
        assertEquals(expectedResult, maintenance.sumPossibleArrangements());
    }
}