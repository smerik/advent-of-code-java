package nl.smerik.adventofcode.aoc2020.model.satellite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
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
        return matches(message, 0) == message.length();
    }

    /**
     * Checks if this rule matches given message starting from given message index.
     * <p>
     * When this rule matches the returned index will be higher than given message index.
     * If the returned value is the same as given message index then there was no match at all.
     *
     * @param message      the message to match
     * @param messageIndex the message index to start matching from
     * @return the index to continue matching from
     */
    private int matches(final String message, final int messageIndex) {
        if (isCharacter()) {
            return this.character == message.charAt(messageIndex) ? messageIndex + 1 : messageIndex;
        }
        for (final List<SatelliteMessageRule> subRuleList : subRuleLists) {
            final int resultIndex = matchesSubRuleList(message, messageIndex, subRuleList);
            // When the result index is higher than the message index the sub-rule list matched
            if (resultIndex > messageIndex) {
                return resultIndex;
            }
        }
        // No match
        return messageIndex;
    }

    /**
     * Checks if this rule has a matching sub-rule list starting from given message index.
     * <p>
     * When there is a matching sub-rule list the returned index will be higher than given message index.
     * If the returned value is the same as given message index then there was no match at all.
     *
     * @param message      the message to match
     * @param messageIndex the message index to start matching from
     * @param subRuleList  the sub-rule list to match
     * @return the index to continue matching from
     */
    private int matchesSubRuleList(final String message, final int messageIndex, final List<SatelliteMessageRule> subRuleList) {
        int resultIndex = messageIndex;
        for (final SatelliteMessageRule rule : subRuleList) {
            int lastIndex = resultIndex;
            resultIndex = rule.matches(message, lastIndex);
            // check if index has moved since last call
            if (resultIndex == lastIndex) {
                // index has not moved so the sub-rule list will never match: return the message index parameter
                return messageIndex;
            }
        }
        return resultIndex;
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
