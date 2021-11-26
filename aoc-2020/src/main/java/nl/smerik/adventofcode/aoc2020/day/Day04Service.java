package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.passport.Passport;
import nl.smerik.adventofcode.aoc2020.service.passport.PassportService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class Day04Service {

    private final PassportService passportService;

    @Value("classpath:input/day-04.txt")
    private Resource resource;

    public Day04Service(final PassportService passportService) {
        this.passportService = passportService;
    }

    public Integer getSolutionPart1() {
        final Set<Passport> passports = initPassports();
        return passportService.getValidPassports(passports).size();
    }

    public Integer getSolutionPart2() {
        final Set<Passport> passports = initPassports();
        return passportService.getValidatedPassports(passports).size();
    }

    private Set<Passport> initPassports() {
        final List<String> batchInput = PuzzleInputParser.parseToString(resource);
        return passportService.parseBatch(batchInput);
    }
}
