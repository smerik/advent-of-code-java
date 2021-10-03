package nl.smerik.adventofcode.aoc2020.model.satellite;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Data
public class SatelliteMessageDocument {

    private final Map<Integer, SatelliteMessageRule> messageRulesByRuleId;
    private final List<String> messages;

    public SatelliteMessageDocument(final List<String> documentLines) {
        this.messageRulesByRuleId = new HashMap<>();
        this.messages = new ArrayList<>();

        final Iterator<String> lineIterator = documentLines.iterator();
        parseRules(lineIterator);
        parseMessages(lineIterator);
    }

    private void parseRules(final Iterator<String> lineIterator) {
        LOG.debug("Start parsing message rules...");
        while (lineIterator.hasNext()) {
            final String line = lineIterator.next();
            if (line.isBlank()) {
                LOG.debug("Finished parsing message rules.");
                break;
            }
            parseRule(line);
        }
    }

    private void parseRule(final String line) {
        final String[] splitLine = line.split(": ");
        final int ruleId = Integer.parseInt(splitLine[0]);

        final SatelliteMessageRule rule = messageRulesByRuleId.computeIfAbsent(ruleId, SatelliteMessageRule::new);
        List<SatelliteMessageRule> subRuleList = new ArrayList<>();

        final String[] splitRule = splitLine[1].split(" ");
        for (final String subRule : splitRule) {
            if (subRule.startsWith("\"")) {
                rule.setCharacter(subRule.charAt(1));
                continue;
            }
            if (subRule.equals("|")) {
                rule.addSubRuleList(subRuleList);
                subRuleList = new ArrayList<>();
                continue;
            }
            final SatelliteMessageRule subR = messageRulesByRuleId.computeIfAbsent(Integer.parseInt(subRule), SatelliteMessageRule::new);
            subRuleList.add(subR);
        }
        if (subRuleList.isEmpty()) {
            return;
        }
        rule.addSubRuleList(subRuleList);
    }

    private void parseMessages(final Iterator<String> lineIterator) {
        LOG.debug("Start parsing messages...");
        while (lineIterator.hasNext()) {
            messages.add(lineIterator.next());
        }
        LOG.debug("Finished parsing messages.");
    }

    /**
     * Counts the number of messages that completely match given rule.
     *
     * @param ruleId the rule to match
     * @return the number of matched messages
     */
    public long countMessagesMatchingRule(final int ruleId) {
        final SatelliteMessageRule rule = this.messageRulesByRuleId.get(ruleId);
        return messages.stream().filter(rule::matches).count();
    }
}
