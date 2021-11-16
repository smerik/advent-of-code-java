package nl.smerik.adventofcode.aoc2020.service.passport;

import nl.smerik.adventofcode.aoc2020.model.passport.Passport;
import nl.smerik.adventofcode.aoc2020.model.passport.PassportFieldType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PassportService {

    private final Validator validator;

    public PassportService(final Validator validator) {
        this.validator = validator;
    }

    public Set<Passport> parseBatch(final List<String> input) {
        final Set<Passport> result = new HashSet<>();

        Passport passport = new Passport();
        result.add(passport);
        for (final String line : input) {
            if (StringUtils.isBlank(line)) {
                passport = new Passport();
                result.add(passport);
            } else {
                fillPassport(passport, line);
            }
        }
        return result;
    }

    private void fillPassport(final Passport passport, final String line) {
        final String[] pairs = line.split(" ");
        for (final String pair : pairs) {
            final String[] keyValue = pair.split(":");
            final String value = keyValue[1];
            switch (PassportFieldType.valueOfKey(keyValue[0])) {
                case BIRTH_YEAR -> passport.setBirthYear(Integer.valueOf(value));
                case ISSUE_YEAR -> passport.setIssueYear(Integer.valueOf(value));
                case EXPIRATION_YEAR -> passport.setExpirationYear(Integer.valueOf(value));
                case HEIGHT -> passport.setHeight(value);
                case HAIR_COLOR -> passport.setHairColor(value);
                case EYE_COLOR -> passport.setEyeColor(value);
                case PASSPORT_ID -> passport.setPassportId(value);
                case COUNTRY_ID -> passport.setCountryId(Integer.valueOf(value));
                default -> throw new IllegalStateException("Unexpected value: " + PassportFieldType.valueOf(keyValue[0]));
            }
        }
    }

    public Set<Passport> getValidPassports(final Set<Passport> passports) {
        return passports.stream().filter(Passport::isValid).collect(Collectors.toSet());
    }

    public Set<Passport> getValidatedPassports(final Set<Passport> passports) {
        return passports.stream().filter(this::isValid).collect(Collectors.toSet());
    }

    private boolean isValid(final Passport passport) {
        return validator.validate(passport).isEmpty();
    }
}
