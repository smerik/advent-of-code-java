package nl.smerik.adventofcode.aoc2023.model.hotsprings;

import org.junit.jupiter.api.Test;

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

    @Test
    void sumPossibleArrangements() {
        final HotSpringsMaintenance maintenance = new HotSpringsMaintenance(INPUT_EXAMPLE_01);
        assertEquals(21, maintenance.sumPossibleArrangements());
    }
}