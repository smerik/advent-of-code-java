package nl.smerik.adventofcode.aoc2020.model.satellite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SatelliteMessageRuleTest {

    @Test
    void testIsCharacter() {
        final SatelliteMessageRule rule = new SatelliteMessageRule(0);
        assertFalse(rule.isCharacter());

        rule.setCharacter('a');
        assertTrue(rule.isCharacter());
    }

    private static Stream<Arguments> provideSourceForRuleMatches() {
        final Map<Integer, SatelliteMessageRule> ruleSet1MappedById = new HashMap<>();
        addRule(0, ruleSet1MappedById, '\0', Set.of(List.of(1, 2)));
        addRule(1, ruleSet1MappedById, 'a', Collections.emptySet());
        addRule(2, ruleSet1MappedById, '\0', Set.of(List.of(1, 3), List.of(3, 1)));
        addRule(3, ruleSet1MappedById, 'b', Collections.emptySet());

        final Map<Integer, SatelliteMessageRule> ruleSet2MappedById = new HashMap<>();
        addRule(0, ruleSet2MappedById, '\0', Set.of(List.of(4, 1, 5)));
        addRule(1, ruleSet2MappedById, '\0', Set.of(List.of(2, 3), List.of(3, 2)));
        addRule(2, ruleSet2MappedById, '\0', Set.of(List.of(4, 4), List.of(5, 5)));
        addRule(3, ruleSet2MappedById, '\0', Set.of(List.of(4, 5), List.of(5, 4)));
        addRule(4, ruleSet2MappedById, 'a', Collections.emptySet());
        addRule(5, ruleSet2MappedById, 'b', Collections.emptySet());

        return Stream.of(
                // @formatter:off
                Arguments.of(ruleSet1MappedById, 1, "a"  , true ),
                Arguments.of(ruleSet1MappedById, 1, "b"  , false),
                Arguments.of(ruleSet1MappedById, 3, "b"  , true ),
                Arguments.of(ruleSet1MappedById, 3, "a"  , false),
                Arguments.of(ruleSet1MappedById, 2, "ab" , true ),
                Arguments.of(ruleSet1MappedById, 2, "ba" , true ),
                Arguments.of(ruleSet1MappedById, 2, "aa" , false),
                Arguments.of(ruleSet1MappedById, 2, "bb" , false),
                Arguments.of(ruleSet1MappedById, 0, "aab", true ),
                Arguments.of(ruleSet1MappedById, 0, "aba", true ),
                Arguments.of(ruleSet1MappedById, 0, "baa", false),

                Arguments.of(ruleSet2MappedById, 4, "a"      , true ),
                Arguments.of(ruleSet2MappedById, 5, "b"      , true ),
                Arguments.of(ruleSet2MappedById, 2, "aa"     , true ),
                Arguments.of(ruleSet2MappedById, 2, "bb"     , true ),
                Arguments.of(ruleSet2MappedById, 3, "ab"     , true ),
                Arguments.of(ruleSet2MappedById, 3, "ba"     , true ),
                Arguments.of(ruleSet2MappedById, 1, "aaab"   , true ),
                Arguments.of(ruleSet2MappedById, 1, "aaba"   , true ),
                Arguments.of(ruleSet2MappedById, 1, "bbab"   , true ),
                Arguments.of(ruleSet2MappedById, 1, "bbba"   , true ),
                Arguments.of(ruleSet2MappedById, 1, "abaa"   , true ),
                Arguments.of(ruleSet2MappedById, 1, "abbb"   , true ),
                Arguments.of(ruleSet2MappedById, 1, "baaa"   , true ),
                Arguments.of(ruleSet2MappedById, 1, "babb"   , true ),
                Arguments.of(ruleSet2MappedById, 0, "aaaabb" , true ),
                Arguments.of(ruleSet2MappedById, 0, "aaabab" , true ),
                Arguments.of(ruleSet2MappedById, 0, "abbabb" , true ),
                Arguments.of(ruleSet2MappedById, 0, "abbbab" , true ),
                Arguments.of(ruleSet2MappedById, 0, "aabaab" , true ),
                Arguments.of(ruleSet2MappedById, 0, "aabbbb" , true ),
                Arguments.of(ruleSet2MappedById, 0, "abaaab" , true ),
                Arguments.of(ruleSet2MappedById, 0, "ababbb" , true ),
                Arguments.of(ruleSet2MappedById, 0, "bababa" , false),
                Arguments.of(ruleSet2MappedById, 0, "aaabbb" , false),
                Arguments.of(ruleSet2MappedById, 0, "aaaabbb", false)
                // @formatter:on
        );
    }

    private static void addRule(final int ruleId, final Map<Integer, SatelliteMessageRule> rulesMappedById, final char character, final Set<List<Integer>> subRuleIdsLists) {
        final SatelliteMessageRule rule = rulesMappedById.computeIfAbsent(ruleId, SatelliteMessageRule::new);
        rule.setCharacter(character);
        for (final List<Integer> subRuleIdsList : subRuleIdsLists) {
            final List<SatelliteMessageRule> subRuleLists = new ArrayList<>();
            for (final Integer subRuleId : subRuleIdsList) {
                subRuleLists.add(rulesMappedById.computeIfAbsent(subRuleId, SatelliteMessageRule::new));
            }
            rule.addSubRuleList(subRuleLists);
        }
    }

    @ParameterizedTest
    @MethodSource("provideSourceForRuleMatches")
    void testMatches(final Map<Integer, SatelliteMessageRule> ruleSetMappedById, final int ruleId, final String message, final boolean expectedResult) {
        assertEquals(expectedResult, ruleSetMappedById.get(ruleId).matches(message));
    }
}