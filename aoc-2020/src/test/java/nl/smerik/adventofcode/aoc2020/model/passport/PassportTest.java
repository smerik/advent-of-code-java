package nl.smerik.adventofcode.aoc2020.model.passport;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassportTest {

    @Test
    void foo() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        final Passport passport = createValidPassport();
        final Set<ConstraintViolation<Passport>> validate = validator.validate(passport);
        assertTrue(validate.isEmpty());
    }

    @Test
    void isValid() {
        final Passport passport = createValidPassport();
        assertTrue(passport.isValid());
    }

    @Test
    void isValidWhenMissingCountryID() {
        final Passport passport = createValidPassport();
        passport.setCountryId(null);
        assertTrue(passport.isValid());
    }

    @Test
    void isInvalidWhenMissingBirthYear() {
        final Passport passport = createValidPassport();
        passport.setBirthYear(null);
        assertFalse(passport.isValid());
    }

    private Passport createValidPassport() {
        final Passport passport = new Passport();
        passport.setBirthYear(1937);
        passport.setIssueYear(2017);
        passport.setExpirationYear(2020);
        passport.setHeight("183cm");
//        passport.setHeight("59in");
        passport.setHairColor("#fffffd");
//        passport.setHairColor("#123abc");
//        passport.setHairColor("#123abz");
//        passport.setHairColor("123abc");
//        passport.setEyeColor("gry");
        passport.setEyeColor("gry");
        passport.setPassportId("860033327");
//        passport.setPassportId("060033327");
//        passport.setPassportId("12345678");
        passport.setCountryId(147);
        return passport;
    }
}