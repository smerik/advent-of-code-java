package nl.smerik.adventofcode.aoc2020.model.joltage;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JoltageAdapterArrayTest {

    /**
     * Day 10 - Part 01 - example 01
     */
    @Test
    void countDifferencesForJoltPart01Example01() {
        final List<Integer> adapters = List.of(
                16,
                10,
                15,
                5,
                1,
                11,
                7,
                19,
                6,
                12,
                4
        );
        JoltageAdapterArray adapterChain = new JoltageAdapterArray(adapters);
        assertEquals(7, adapterChain.countDifferencesForJolt(1));
        assertEquals(5, adapterChain.countDifferencesForJolt(3));
    }

    /**
     * Day 10 - Part 01 - example 02
     */
    @Test
    void countDifferencesForJoltPart01Example02() {
        final List<Integer> adapters = List.of(
                28,
                33,
                18,
                42,
                31,
                14,
                46,
                20,
                48,
                47,
                24,
                23,
                49,
                45,
                19,
                38,
                39,
                11,
                1,
                32,
                25,
                35,
                8,
                17,
                7,
                9,
                4,
                2,
                34,
                10,
                3
        );
        JoltageAdapterArray adapterChain = new JoltageAdapterArray(adapters);
        assertEquals(22, adapterChain.countDifferencesForJolt(1));
        assertEquals(10, adapterChain.countDifferencesForJolt(3));
    }
}