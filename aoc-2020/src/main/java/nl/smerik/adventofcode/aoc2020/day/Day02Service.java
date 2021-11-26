package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.service.security.PasswordService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day02Service {

    private final PasswordService passwordService;

    @Value("classpath:input/day-02.txt")
    private Resource resource;

    public Day02Service(final PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public Long getSolutionPart1() {
        final List<String> passwordPolicies = PuzzleInputParser.parseToString(resource);
        return passwordService.validatePasswordPoliciesForSledRentalPlace(passwordPolicies);
    }

    public Long getSolutionPart2() {
        final List<String> passwordPolicies = PuzzleInputParser.parseToString(resource);
        return passwordService.validatePasswordPoliciesForToboggan(passwordPolicies);
    }
}
