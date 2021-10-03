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

    @Test
    void testInitializeDocument() {
        final SatelliteMessageDocument document1 = new SatelliteMessageDocument(DOCUMENT_1_LINES);
        assertEquals(4, document1.getMessageRulesByRuleId().size());
        assertEquals(0, document1.getMessages().size());

        final SatelliteMessageDocument document2 = new SatelliteMessageDocument(DOCUMENT_2_LINES);
        assertEquals(6, document2.getMessageRulesByRuleId().size());
        assertEquals(5, document2.getMessages().size());

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
}