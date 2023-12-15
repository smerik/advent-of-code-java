package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

import java.util.LinkedHashMap;
import java.util.Map;

public class LensBox {

    private final Map<String, Integer> focalLengthsByLabel;
    private final int boxNumber;

    public LensBox(final int boxNumber) {
        this.boxNumber = boxNumber;
        this.focalLengthsByLabel = new LinkedHashMap<>();
    }

    public void add(final String label, final int focalLength) {
        focalLengthsByLabel.put(label, focalLength);
    }

    public void remove(final String label) {
        focalLengthsByLabel.remove(label);
    }

    boolean isEmpty() {
        return focalLengthsByLabel.isEmpty();
    }

    public int sumFocusingPower() {
        int result = 0;
        int slotCount = 0;
        for (final Integer focalLength : focalLengthsByLabel.values()) {
            slotCount++;
            result += (1 + boxNumber) * slotCount * focalLength;
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Box " + boxNumber + ": ");
        for (Map.Entry<String, Integer> entry : focalLengthsByLabel.entrySet()) {
            builder.append("[").append(entry.getKey()).append(" ").append(entry.getValue()).append("]");
        }
        return builder.toString();
    }
}
