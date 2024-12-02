package nl.smerik.adventofcode.aoc2024.model.report;

import java.util.List;

public final class RedNosedReportUtil {

    private RedNosedReportUtil() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean isReportSafe(final List<Integer> levels) {
        int lastSignum = 0;
        for (int i = 0; i < levels.size() - 1; i++) {
            int currentLevel = levels.get(i);
            int nextLevel = levels.get(i + 1);
            int diff = nextLevel - currentLevel;
            if (diff == 0) {
                return false;
            }
            // Any two adjacent levels differ by at least one and at most three
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }
            int currentSignum = Integer.signum(diff);
            if (lastSignum == 0) {
                lastSignum = currentSignum;
                // The levels are either all increasing or all decreasing
            } else if (currentSignum != lastSignum) {
                return false;
            }
        }
        return true;
    }
}
