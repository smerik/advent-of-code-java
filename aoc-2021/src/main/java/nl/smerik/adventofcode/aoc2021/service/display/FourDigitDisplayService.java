package nl.smerik.adventofcode.aoc2021.service.display;

import nl.smerik.adventofcode.aoc2021.model.display.FourDigitDisplay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FourDigitDisplayService {

    public int countDigits(final List<String> notes, final Set<Integer> digits) {
        int result = 0;
        for (final String note : notes) {
            final FourDigitDisplay display = new FourDigitDisplay(note);
            result += display.countDigits(digits);
        }
        return result;
    }
}
