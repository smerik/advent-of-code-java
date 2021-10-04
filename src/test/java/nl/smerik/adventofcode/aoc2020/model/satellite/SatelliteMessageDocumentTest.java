package nl.smerik.adventofcode.aoc2020.model.satellite;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SatelliteMessageDocumentTest {

    static final List<String> DOCUMENT_1_LINES = List.of(
            "0: 1 2",
            "1: \"a\"",
            "2: 1 3 | 3 1",
            "3: \"b\""
    );

    static final List<String> DOCUMENT_2_LINES = List.of(
            "0: 4 1 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            "4: \"a\"",
            "5: \"b\"",
            "",
            "ababbb",
            "bababa",
            "abbbab",
            "aaabbb",
            "aaaabbb");

    static final List<String> DOCUMENT_3_LINES = List.of(
            "42: 9 14 | 10 1",
            "9: 14 27 | 1 26",
            "10: 23 14 | 28 1",
            "1: \"a\"",
            "11: 42 31",
            "5: 1 14 | 15 1",
            "19: 14 1 | 14 14",
            "12: 24 14 | 19 1",
            "16: 15 1 | 14 14",
            "31: 14 17 | 1 13",
            "6: 14 14 | 1 14",
            "2: 1 24 | 14 4",
            "0: 8 11",
            "13: 14 3 | 1 12",
            "15: 1 | 14",
            "17: 14 2 | 1 7",
            "23: 25 1 | 22 14",
            "28: 16 1",
            "4: 1 1",
            "20: 14 14 | 1 15",
            "3: 5 14 | 16 1",
            "27: 1 6 | 14 18",
            "14: \"b\"",
            "21: 14 1 | 1 14",
            "25: 1 1 | 1 14",
            "22: 14 14",
            "8: 42",
            "26: 14 22 | 1 20",
            "18: 15 15",
            "7: 14 5 | 1 21",
            "24: 14 1",
            "",
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa",
            "bbabbbbaabaabba",
            "babbbbaabbbbbabbbbbbaabaaabaaa",
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa",
            "bbbbbbbaaaabbbbaaabbabaaa",
            "bbbababbbbaaaaaaaabbababaaababaabab",
            "ababaaaaaabaaab",
            "ababaaaaabbbaba",
            "baabbaaaabbaaaababbaababb",
            "abbbbabbbbaaaababbbbbbaaaababb",
            "aaaaabbaabaaaaababaa",
            "aaaabbaaaabbaaa",
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa",
            "babaaabbbaaabaababbaabababaaab",
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba");

    @Test
    void testInitializeDocument() {
        final SatelliteMessageDocument document1 = new SatelliteMessageDocument(DOCUMENT_1_LINES);
        assertEquals(4, document1.getMessageRulesByRuleId().size());
        assertEquals(0, document1.getMessages().size());

        final SatelliteMessageDocument document2 = new SatelliteMessageDocument(DOCUMENT_2_LINES);
        assertEquals(6, document2.getMessageRulesByRuleId().size());
        assertEquals(5, document2.getMessages().size());

        final SatelliteMessageDocument document3 = new SatelliteMessageDocument(DOCUMENT_3_LINES);
        assertEquals(31, document3.getMessageRulesByRuleId().size());
        assertEquals(15, document3.getMessages().size());

        final SatelliteMessageRule rule0 = document2.getMessageRulesByRuleId().get(0);
        assertEquals('\0', rule0.getCharacter());
        assertEquals(1, rule0.getSubRuleLists().size());
        assertTrue(rule0.getSubRuleLists().contains(createSubRuleListOf(document2, 4, 1, 5)));

        final SatelliteMessageRule rule2 = document2.getMessageRulesByRuleId().get(2);
        assertEquals('\0', rule2.getCharacter());
        assertEquals(2, rule2.getSubRuleLists().size());
        assertTrue(rule2.getSubRuleLists().contains(createSubRuleListOf(document2, 4, 4)));
        assertTrue(rule2.getSubRuleLists().contains(createSubRuleListOf(document2, 5, 5)));

        final SatelliteMessageRule rule4 = document2.getMessageRulesByRuleId().get(4);
        assertEquals('a', rule4.getCharacter());
        assertEquals(0, rule4.getSubRuleLists().size());

        assertEquals("ababbb", document2.getMessages().get(0));
        assertEquals("aaaabbb", document2.getMessages().get(4));
    }

    private List<SatelliteMessageRule> createSubRuleListOf(final SatelliteMessageDocument document, final int... ruleIds) {
        final List<SatelliteMessageRule> result = new ArrayList<>();
        for (final int ruleId : ruleIds) {
            result.add(document.getMessageRulesByRuleId().get(ruleId));
        }
        return result;
    }

    @Test
    void testFindMessagesMatchingRule() {
        final SatelliteMessageDocument document2 = new SatelliteMessageDocument(DOCUMENT_2_LINES);
        final Set<String> messagesMatchingRule = document2.findMessagesMatchingRule(0);
        assertEquals(2, messagesMatchingRule.size());
        assertTrue(messagesMatchingRule.contains("ababbb"));
        assertTrue(messagesMatchingRule.contains("abbbab"));
    }

    @Test
    void testFindMessagesMatchingRuleWithoutUpdatingRules() {
        final SatelliteMessageDocument document3 = new SatelliteMessageDocument(DOCUMENT_3_LINES);
        final Set<String> messagesMatchingRule = document3.findMessagesMatchingRule(0);
        assertEquals(3, messagesMatchingRule.size());
        assertTrue(messagesMatchingRule.contains("bbabbbbaabaabba"));
        assertTrue(messagesMatchingRule.contains("ababaaaaaabaaab"));
        assertTrue(messagesMatchingRule.contains("ababaaaaabbbaba"));
    }

    @Test
    void testFindMessagesMatchingRuleAfterUpdatingRules() {
        final SatelliteMessageDocument document3 = new SatelliteMessageDocument(DOCUMENT_3_LINES);
        document3.getMessageRulesByRuleId().get(8).addSubRuleList(createSubRuleListOf(document3, 42, 8));
        document3.getMessageRulesByRuleId().get(11).addSubRuleList(createSubRuleListOf(document3, 42, 11, 31));

        final Set<String> messagesMatchingRule = document3.findMessagesMatchingRule(0);
        assertEquals(12, messagesMatchingRule.size());
        assertTrue(messagesMatchingRule.contains("bbabbbbaabaabba"));
        assertTrue(messagesMatchingRule.contains("babbbbaabbbbbabbbbbbaabaaabaaa"));
        assertTrue(messagesMatchingRule.contains("aaabbbbbbaaaabaababaabababbabaaabbababababaaa"));
        assertTrue(messagesMatchingRule.contains("bbbbbbbaaaabbbbaaabbabaaa"));
        assertTrue(messagesMatchingRule.contains("bbbababbbbaaaaaaaabbababaaababaabab"));
        assertTrue(messagesMatchingRule.contains("ababaaaaaabaaab"));
        assertTrue(messagesMatchingRule.contains("ababaaaaabbbaba"));
        assertTrue(messagesMatchingRule.contains("baabbaaaabbaaaababbaababb"));
        assertTrue(messagesMatchingRule.contains("abbbbabbbbaaaababbbbbbaaaababb"));
        assertTrue(messagesMatchingRule.contains("aaaaabbaabaaaaababaa"));
        assertTrue(messagesMatchingRule.contains("aaaabbaabbaaaaaaabbbabbbaaabbaabaaa"));
        assertTrue(messagesMatchingRule.contains("aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"));
    }
}