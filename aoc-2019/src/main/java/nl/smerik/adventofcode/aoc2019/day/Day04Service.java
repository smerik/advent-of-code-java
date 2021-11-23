package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class Day04Service {

    public int getSolutionPart1(final int rangeStart, final int rangeEnd) {
        int result = 0;
        for (int i = rangeStart; i < rangeEnd; i++) {
            if (PasswordUtil.doesPasswordMeetCriteriaForPart1(i)) {
                result++;
            };
        }
        return result;
    }

    public int getSolutionPart2(final int rangeStart, final int rangeEnd) {
        int result = 0;
        for (int i = rangeStart; i < rangeEnd; i++) {
            if (PasswordUtil.doesPasswordMeetCriteriaForPart2(i)) {
                result++;
            };
        }
        return result;
    }
}
