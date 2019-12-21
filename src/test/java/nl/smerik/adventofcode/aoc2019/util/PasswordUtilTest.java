package nl.smerik.adventofcode.aoc2019.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    void doesPasswordMeetCriteriaForPart1() {
        // event examples
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart1(111111));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart1(123789)); // no double
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart1(223450)); // decreasing pair of digits

        // no six digit number
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart1(99999));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart1(1000000));

        // Part 02
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart1(112233));
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart1(123444));
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart1(111122));
    }

    @Test
    void doesPasswordMeetCriteriaForPart2() {
        // event examples
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(111111)); // changed since part 02

        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(123789)); // no double
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(223450)); // decreasing pair of digits

        // no six digit number
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(99999));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(1000000));


        // Part 02: the two adjacent matching digits are not part of a larger group of matching digits
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart2(112233));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(123444));

        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart2(111122));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(111222));
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart2(111223));
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart2(111233));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(122234));
        assertTrue(PasswordUtil.doesPasswordMeetCriteriaForPart2(112223));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(111112));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(122222));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(122223));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(123444));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(123333));
        assertFalse(PasswordUtil.doesPasswordMeetCriteriaForPart2(123334));
    }
}