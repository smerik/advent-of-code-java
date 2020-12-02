package nl.smerik.adventofcode.aoc2020.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordService {

    private static final Pattern PASSWORD_POLICY_PATTERN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");

    public long validatePasswordPolicies(final List<String> policies) {
        return policies.stream()
                       .filter(this::validatePasswordPolicy)
                       .count();
    }

    boolean validatePasswordPolicy(final String policy) {
        final Matcher matcher = PASSWORD_POLICY_PATTERN.matcher(policy);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid password policy '" + policy + "'");
        }
        final int atLeast = Integer.parseInt(matcher.group(1));
        final int atMost = Integer.parseInt(matcher.group(2));
        final char requiredCharacter = matcher.group(3).charAt(0);
        final String password = matcher.group(4);
        return validatePasswordPolicy(password, requiredCharacter, atLeast, atMost);
    }

    boolean validatePasswordPolicy(final String password,
                                   final char requiredCharacter,
                                   final int atLeast,
                                   final int atMost) {
        final int matches = StringUtils.countMatches(password, requiredCharacter);
        return matches >= atLeast && matches <= atMost;
    }
}
