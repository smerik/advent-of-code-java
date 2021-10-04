package nl.smerik.adventofcode.aoc2020.model.satellite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SatelliteMessageRule {

    @EqualsAndHashCode.Include
    private final int id;

    private final Set<List<SatelliteMessageRule>> subRuleLists = new HashSet<>();

    private char character = '\0';

    public boolean isCharacter() {
        return character != '\0';
    }

    public void addSubRuleList(final List<SatelliteMessageRule> subRuleList) {
        this.subRuleLists.add(subRuleList);
    }

    /**
     * Checks if this rule matches given message.
     *
     * @param message the message to match
     * @return <code>true</code> when matches; <code>false</code> otherwise
     */
    public boolean matches(final String message) {
        return matches(message, Set.of(0)).stream().anyMatch(index -> message.length() == index);
    }

    /**
     * Checks if this rule matches given message starting from given message indices.
     * <p>
     * When there are matches the returned collection will contain the indices to be used as a starting point for the
     * next check.
     * If the returned collection is empty then there was no match at all.
     *
     * @param message        the message to match
     * @param messageIndices the message indices to start matching from
     * @return the indices to continue matching from
     */
    private Set<Integer> matches(final String message, final Set<Integer> messageIndices) {
        final Set<Integer> result = new HashSet<>();
        for (final Integer messageIndex : messageIndices) {
            if (messageIndex > message.length() - 1) {
                // There is no need to process any further when the message index passed the message length
                continue;
            }
            if (isCharacter() && this.character == message.charAt(messageIndex)) {
                result.add(messageIndex + 1);
                // No need for `continue;` here: for-loop deals with an empty `subRuleLists`
            }
            for (final List<SatelliteMessageRule> subRuleList : subRuleLists) {
                // It is safe to use `addAll`: matchesSubRuleList will return an empty collection when there was no match
                result.addAll(matchesSubRuleList(message, messageIndex, subRuleList));
            }
        }
        return result;
    }

    /**
     * Checks if this rule has a matching sub-rule list starting from given message index.
     * <p>
     * When there is a matching sub-rule list the returned collection will contain the indices to be used as a starting
     * point for the next check.
     * If the returned collection is empty then there was no match at all.
     *
     * @param message      the message to match
     * @param messageIndex the message index to start matching from
     * @param subRuleList  the sub-rule list to match
     * @return the indices to continue matching from
     */
    private Set<Integer> matchesSubRuleList(final String message, final int messageIndex, final List<SatelliteMessageRule> subRuleList) {
        Set<Integer> resultIndices = new HashSet<>();
        resultIndices.add(messageIndex);

        for (final SatelliteMessageRule rule : subRuleList) {
            final Set<Integer> lastIndices = resultIndices;
            resultIndices = rule.matches(message, lastIndices);
            // Check if the rule has any matches
            if (resultIndices.isEmpty()) {
                // The rule has no matches so the sub-rule list is not a match: stop looking for matches
                break;
            }
        }
        return resultIndices;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(getId() + ":");
        if (isCharacter()) {
            builder.append(' ').append(character);
        } else {
            final Iterator<List<SatelliteMessageRule>> subRuleListsIterator = getSubRuleLists().iterator();
            while (subRuleListsIterator.hasNext()) {
                for (final SatelliteMessageRule rule : subRuleListsIterator.next()) {
                    builder.append(' ').append(rule.getId());
                }
                if (subRuleListsIterator.hasNext()) {
                    builder.append(" |");
                }
            }
        }
        return builder.toString();
    }
}
