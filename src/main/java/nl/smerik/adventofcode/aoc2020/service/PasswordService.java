package nl.smerik.adventofcode.aoc2020.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordService {

    private static final Pattern PASSWORD_POLICY_PATTERN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");

    public long validatePasswordPoliciesForSledRentalPlace(final List<String> policies) {
        return policies.stream()
                       .filter(this::validatePasswordPolicyForSledRentalPlace)
                       .count();
    }

    boolean validatePasswordPolicyForSledRentalPlace(final String policy) {
        final Matcher matcher = PASSWORD_POLICY_PATTERN.matcher(policy);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid password policy '" + policy + "'");
        }
        final int atLeast = Integer.parseInt(matcher.group(1));
        final int atMost = Integer.parseInt(matcher.group(2));
        final char requiredCharacter = matcher.group(3).charAt(0);
        final String password = matcher.group(4);
        return validatePasswordPolicyForSledRentalPlace(password, requiredCharacter, atLeast, atMost);
    }

    boolean validatePasswordPolicyForSledRentalPlace(final String password,
                                                     final char requiredCharacter,
                                                     final int atLeast,
                                                     final int atMost) {
        final int matches = StringUtils.countMatches(password, requiredCharacter);
        return matches >= atLeast && matches <= atMost;
    }

    public long validatePasswordPoliciesForToboggan(final List<String> policies) {
        return policies.stream()
                       .filter(this::validatePasswordPolicyForToboggan)
                       .count();
    }

    boolean validatePasswordPolicyForToboggan(final String policy) {
        final Matcher matcher = PASSWORD_POLICY_PATTERN.matcher(policy);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid password policy '" + policy + "'");
        }
        final int positionContaining = Integer.parseInt(matcher.group(1));
        final int positionNotContaining = Integer.parseInt(matcher.group(2));
        final char character = matcher.group(3).charAt(0);
        final String password = matcher.group(4);
        return validatePasswordPolicyForToboggan(password, character, positionContaining, positionNotContaining);
    }

    boolean validatePasswordPolicyForToboggan(final String password,
                                              final char character,
                                              final int positionContaining,
                                              final int positionNotContaining) {

        return password.charAt(positionContaining - 1) == character
                ^ password.charAt(positionNotContaining - 1) == character;
    }
}
