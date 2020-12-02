package nl.smerik.adventofcode.aoc2020.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PasswordServiceTest {

    @Autowired
    private PasswordService passwordService;

    private static Stream<Arguments> provideSourceForPasswordPolicy() {
        return Stream.of(
                // @formatter:off
                Arguments.of(1, 3, 'a', "abcde"    , true ),
                Arguments.of(1, 3, 'b', "cdefg"    , false),
                Arguments.of(2, 9, 'c', "ccccccccc", true )
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForPasswordPolicy")
    void validatePasswordPolicy(final int atLeast,
                                final int atMost,
                                final char requiredCharacter,
                                final String password,
                                final boolean isValid) {

        assertEquals(isValid, passwordService.validatePasswordPolicy(password, requiredCharacter, atLeast, atMost));
    }

    @ParameterizedTest
    @MethodSource("provideSourceForPasswordPolicy")
    void validatePasswordPolicyString(final int atLeast,
                                      final int atMost,
                                      final char requiredCharacter,
                                      final String password,
                                      final boolean isValid) {
        final String policy = atLeast + "-" + atMost + ' ' + requiredCharacter + ": " + password;

        assertEquals(isValid, passwordService.validatePasswordPolicy(policy));
    }

    @Test
    void validatePasswordPolicies() {
        final List<String> policies = List.of("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc");
        assertEquals(2L, passwordService.validatePasswordPolicies(policies));
    }
}