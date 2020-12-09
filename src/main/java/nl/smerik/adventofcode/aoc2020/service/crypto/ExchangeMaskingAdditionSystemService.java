package nl.smerik.adventofcode.aoc2020.service.crypto;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The eXchange-Masking Addition System (XMAS) cypher.
 */
@Service
public class ExchangeMaskingAdditionSystemService {

    public Long validate(final List<Long> message, final int preambleSize) {
        for (int i = 0; i < message.size() - preambleSize; i++) {
            final List<Long> preamble = message.subList(i, i + preambleSize);
            final Long expectedSum = message.get(i + preambleSize);
            if (!findSum(preamble, expectedSum)) {
                return expectedSum;
            }
        }
        return 0L;
    }

    private boolean findSum(final List<Long> preamble, final Long expectedSum) {
        for (int i = 0; i < preamble.size(); i++) {
            for (int j = 1; j < preamble.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (preamble.get(i) + preamble.get(j) == expectedSum) {
                    return true;
                }
            }
        }
        return false;
    }
}
