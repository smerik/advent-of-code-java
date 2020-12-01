package nl.smerik.adventofcode.aoc2020.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseReportService {

    public Long calculateExpenseReport(final List<Integer> strings) {
        for (int i = 0; i < strings.size(); i++) {
            for (int j = i + 1; j < strings.size(); j++) {
                final long x = strings.get(i);
                final long y = strings.get(j);
                if (x + y == 2020) {
                    return x * y;
                }
            }
        }
        return -1L;
    }

    public Long calculateExpenseReportForThreeNumbers(final List<Integer> strings) {
        for (int i = 0; i < strings.size(); i++) {
            for (int j = i + 1; j < strings.size(); j++) {
                for (int k = i + 2; k < strings.size(); k++) {
                    final long x = strings.get(i);
                    final long y = strings.get(j);
                    final long z = strings.get(k);
                    if (x + y + z == 2020) {
                        return x * y * z;
                    }
                }
            }
        }
        return -1L;
    }
}
