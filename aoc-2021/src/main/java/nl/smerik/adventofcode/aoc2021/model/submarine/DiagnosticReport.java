package nl.smerik.adventofcode.aoc2021.model.submarine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int calculateLifeSupportRate() {
        return determineOxygenGeneratorRate() * determineCO2ScrubbingRate();
    }

    int determineOxygenGeneratorRate() {
        return findRateValueByMostCommonBit(true);
    }

    int determineCO2ScrubbingRate() {
        return findRateValueByMostCommonBit(false);
    }

    private int findRateValueByMostCommonBit(final boolean mostCommon) {
        if (binaryNumbers.isEmpty()) {
            return 0;
        }
        List<String> result = binaryNumbers;
        for (int i = 0; i < binaryNumbers.get(0).length(); i++) {
            result = filterNumbersAtIndexByBitCriteria(result, i, mostCommon);
            if (result.size() == 1) {
                break;
            }
        }
        if (result.size() != 1) {
            throw new IllegalStateException("Result should contain only 1 element");
        }
        return Integer.parseInt(result.get(0), 2);
    }

    /**
     * Filters given collection of binary numbers at index depending on the bit criteria parameter
     * <code>mostCommon</code>.
     * <ul>
     * <li>When filtering by the most common value (0 or 1) in the bit position
     *     <code>mostCommon == true</code>
     *     the returned collection will contain only the binary numbers
     *     with the most common bit value at given index position.<br>
     *     If 0 and 1 are equally common, values with a 1 in the position are being considered.
     * <li>When filtering by the least common value (0 or 1) in the bit position
     *     <code>mostCommon == false</code>
     *     the returned collection will contain only the binary numbers
     *     with the least common bit value at given index position.<br>
     *     If 0 and 1 are equally common, values with a 0 in the position are being considered.
     *
     * @param binaryNumbers the collection to filter
     * @param index         the index to filter at
     * @param mostCommon    whether to filter by most common index or least common index
     * @return the filtered binary numbers
     */
    List<String> filterNumbersAtIndexByBitCriteria(final List<String> binaryNumbers, final int index, final boolean mostCommon) {
        final Map<Integer, List<String>> binaryNumbersByBit = new HashMap<>();
        for (final String binaryNumber : binaryNumbers) {
            final int bitValue = Character.getNumericValue(binaryNumber.charAt(index));
            final List<String> numbers = binaryNumbersByBit.computeIfAbsent(bitValue, i -> new ArrayList<>());
            numbers.add(binaryNumber);
        }
        if (mostCommon) {
            return binaryNumbersByBit.computeIfAbsent(0, i -> new ArrayList<>()).size() > binaryNumbersByBit.computeIfAbsent(1, i -> new ArrayList<>()).size()
                    ? binaryNumbersByBit.get(0) : binaryNumbersByBit.get(1);
        }
        return binaryNumbersByBit.computeIfAbsent(1, i -> new ArrayList<>()).size() < binaryNumbersByBit.computeIfAbsent(0, i -> new ArrayList<>()).size()
                ? binaryNumbersByBit.get(1) : binaryNumbersByBit.get(0);
    }
}