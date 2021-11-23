package nl.smerik.adventofcode.aoc2019.util;

import java.util.PrimitiveIterator;

public final class PasswordUtil {

    private PasswordUtil() {
    }

    public static boolean doesPasswordMeetCriteriaForPart1(final int password) {
        return isSixDigitNumber(password)
                && doesContainTwoAdjacentDigitsAreTheSame(password)
                && allDigitsNeverDecreaseFromLeftToRight(password);
    }

    public static boolean doesPasswordMeetCriteriaForPart2(final int password) {
        return isSixDigitNumber(password)
                && doesContainTwoAdjacentDigitsAreTheSameButNotPartOfALargerGroupOfMatchingDigits(password)
                && allDigitsNeverDecreaseFromLeftToRight(password);
    }

    private static boolean isSixDigitNumber(final int password) {
        return password >= 100000 && password <= 999999;
    }

    private static boolean doesContainTwoAdjacentDigitsAreTheSame(final int password) {
        final PrimitiveIterator.OfInt iterator = String.valueOf(password).chars().iterator();
        Integer lastNumber = null;
        while (iterator.hasNext()) {
            final Integer number = iterator.next();
            if (number.equals(lastNumber)) {
                return true;
            }
            lastNumber = number;
        }
        return false;
    }

    private static boolean doesContainTwoAdjacentDigitsAreTheSameButNotPartOfALargerGroupOfMatchingDigits(final int password) {
        final char[] chars = String.valueOf(password).toCharArray();
        int i = 0;
        while (i < chars.length) {
            if ((i + 2) < chars.length && chars[i] == chars[i + 1] && chars[i] == chars[i + 2]) {
                i = i + 3;
                while (i < chars.length && chars[i - 1] == chars[i]) {
                    i++;
                }
                continue;
            }
            if ((i + 1) < chars.length && chars[i] == chars[i + 1]) {
                return true;
            }
            i++;
        }
        return false;
    }

    private static boolean allDigitsNeverDecreaseFromLeftToRight(final int password) {
        final PrimitiveIterator.OfInt iterator = String.valueOf(password).chars().iterator();
        Integer lastNumber = null;
        while (iterator.hasNext()) {
            final Integer number = iterator.next();
            if (lastNumber != null && number < lastNumber) {
                return false;
            }
            lastNumber = number;
        }
        return true;
    }
}
