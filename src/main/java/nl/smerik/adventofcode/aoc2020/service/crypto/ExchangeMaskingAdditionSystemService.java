package nl.smerik.adventofcode.aoc2020.service.crypto;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Long> findContiguousRange(final List<Long> message, final Long invalidNumber) {
        for (int i = 0; i < message.size(); i++) {
            final List<Long> contiguousRangeFromIndex = findContiguousRangeFromIndex(message, invalidNumber, i);
            if (!contiguousRangeFromIndex.isEmpty()) {
                return contiguousRangeFromIndex;
            }
        }
        return Collections.emptyList();
    }

    private List<Long> findContiguousRangeFromIndex(final List<Long> message,
                                                    final Long invalidNumber,
                                                    final int startIndex) {

        final List<Long> result = new ArrayList<>();
        long sum = 0;
        for (int i = startIndex; i < message.size(); i++) {
            final Long number = message.get(i);
            result.add(number);
            sum += number;

            if (sum > invalidNumber) {
                result.clear();
                sum = 0;
                break;
            }

            if (sum == invalidNumber) {
                if (result.size() == 1) {
                    sum = 0;
                    result.clear();
                    break;
                }
                return result;
            }
        }
        return Collections.emptyList();
    }

    public Long findEncryptionWeakness(final List<Long> contiguousRange) {
        final Long min = contiguousRange.stream().min(Long::compareTo).orElseThrow();
        final Long max = contiguousRange.stream().max(Long::compareTo).orElseThrow();
        return min + max;
    }
}
