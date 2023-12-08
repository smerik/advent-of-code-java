package nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland.NodeDirection.LEFT;
import static nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland.NodeDirection.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HauntedWastelandMapTest {

    private static final List<String> INPUT_EXAMPLE_01 = List.of(
            // @formatter:off
            "RL",
            "",
            "AAA = (BBB, CCC)",
            "BBB = (DDD, EEE)",
            "CCC = (ZZZ, GGG)",
            "DDD = (DDD, DDD)",
            "EEE = (EEE, EEE)",
            "GGG = (GGG, GGG)",
            "ZZZ = (ZZZ, ZZZ)"
            // @formatter:on
    );

    private static final List<String> INPUT_EXAMPLE_02 = List.of(
            // @formatter:off
            "LLR",
            "",
            "AAA = (BBB, BBB)",
            "BBB = (AAA, ZZZ)",
            "ZZZ = (ZZZ, ZZZ)"
            // @formatter:on
    );

    private static final List<String> INPUT_EXAMPLE_03 = List.of(
            // @formatter:off
            "LR",
            "",
            "11A = (11B, XXX)",
            "11B = (XXX, 11Z)",
            "11Z = (11B, XXX)",
            "22A = (22B, XXX)",
            "22B = (22C, 22C)",
            "22C = (22Z, 22Z)",
            "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)"
            // @formatter:on
    );

    private static HauntedWastelandMap exampleMap;


    @BeforeAll
    static void beforeAll() {
        exampleMap = new HauntedWastelandMap(INPUT_EXAMPLE_01);
    }


    @Test
    void testGetDirections() {
        assertEquals(List.of(RIGHT, LEFT), exampleMap.getDirections());
    }

    @Test
    void testGetMapByNode() {
        final Map<String, List<String>> result = exampleMap.getMapByNode();
        assertEquals(List.of("BBB", "CCC"), result.get("AAA"));
        assertEquals(List.of("DDD", "EEE"), result.get("BBB"));
        assertEquals(List.of("ZZZ", "GGG"), result.get("CCC"));
        assertEquals(List.of("DDD", "DDD"), result.get("DDD"));
        assertEquals(List.of("EEE", "EEE"), result.get("EEE"));
        assertEquals(List.of("GGG", "GGG"), result.get("GGG"));
        assertEquals(List.of("ZZZ", "ZZZ"), result.get("ZZZ"));
    }

    private static Stream<Arguments> determineStepCountToReachEndpointSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(2, INPUT_EXAMPLE_01),
                Arguments.of(6, INPUT_EXAMPLE_02)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("determineStepCountToReachEndpointSource")
    void testDetermineStepCountToReachEndpoint(final int expectedStepCount, final List<String> lines) {
        final HauntedWastelandMap map = new HauntedWastelandMap(lines);
        assertEquals(expectedStepCount, map.determineStepCountToReachEndpoint());
    }

    @Test
    void testFollowInstructions() {
        assertEquals(List.of("AAA", "CCC", "ZZZ"), exampleMap.followInstructions());
    }

    @Test
    void testDetermineStepCountToReachEndpointAsAGhost() {
        final HauntedWastelandMap map = new HauntedWastelandMap(INPUT_EXAMPLE_03);
        assertEquals(BigInteger.valueOf(6), map.determineStepCountToReachEndpointAsAGhost());
    }

    @Test
    void testDetermineStartingNodesForGhost() {
        final HauntedWastelandMap map = new HauntedWastelandMap(INPUT_EXAMPLE_03);
        assertEquals(Set.of("11A", "22A"), map.determineStartNodesForGhost());
    }

    private static Stream<Arguments> isPossibleEndNodeSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, "AAA"),
                Arguments.of(false, "ZAA"),
                Arguments.of(false, "ZZA"),
                Arguments.of(true , "ZZZ"),
                Arguments.of(true , "AZZ"),
                Arguments.of(true , "AAZ")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("isPossibleEndNodeSource")
    void testIsPossibleEndNode(final boolean expectedResult, final String node) {
        assertEquals(expectedResult, HauntedWastelandMap.isPossibleEndNode(node));
    }
}