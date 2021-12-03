package nl.smerik.adventofcode.aoc2021.model.submarine;

import java.util.List;

public class DiagnosticReport {

    private final List<String> binaryNumbers;

    public DiagnosticReport(final List<String> binaryNumbers) {
        this.binaryNumbers = binaryNumbers;
    }

    /**
     * Calculates the power consumption in decimal by multiplying the gamma rate and epsilon rate together.
     *
     * @return the power consumption
     */
    public int calculatePowerConsumption() {
        return determineGammaRate() * determineEpsilonRate();
    }

    public int determineGammaRate() {
        if (binaryNumbers.isEmpty()) {
            return 0;
        }

        final StringBuilder rateBuilder = new StringBuilder();
        for (int i = 0; i < binaryNumbers.get(0).length(); i++) {
            rateBuilder.append(findMostCommonBit(i));
        }
        return Integer.parseInt(rateBuilder.toString(), 2);
    }

    public int determineEpsilonRate() {
        if (binaryNumbers.isEmpty()) {
            return 0;
        }

        final StringBuilder rateBuilder = new StringBuilder();
        for (int i = 0; i < binaryNumbers.get(0).length(); i++) {
            rateBuilder.append(findLeastCommonBit(i));
        }
        return Integer.parseInt(rateBuilder.toString(), 2);
    }

    /**
     * Finds the most common bit for given index position, starting from the left.
     *
     * @param index the bit position
     * @return the most common bit
     */
    int findMostCommonBit(final int index) {
        return findLeastCommonBit(index) == 0 ? 1 : 0;
    }

    /**
     * Finds the least common bit for given index position, starting from the left.
     *
     * @param index the bit position
     * @return the most common bit
     */
    int findLeastCommonBit(final int index) {
        if (binaryNumbers.isEmpty()) {
            return 0;
        }

        int countZeroBits = 0;
        for (final String binaryNumber : binaryNumbers) {
            if (binaryNumber.charAt(index) == '0') {
                countZeroBits++;
            }
        }
        return countZeroBits < (binaryNumbers.size() / 2) ? 0 : 1;
    }
}
