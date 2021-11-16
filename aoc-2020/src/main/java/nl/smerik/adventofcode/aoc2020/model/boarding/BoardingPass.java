package nl.smerik.adventofcode.aoc2020.model.boarding;

import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class BoardingPass {

    private final int row;
    private final int column;

    public BoardingPass(final String passID) {
        final PrimitiveIterator.OfInt rowIterator = passID.substring(0, 7).chars().iterator();
        row = partition(rowIterator, 0, 127);

        final PrimitiveIterator.OfInt columnIterator = passID.substring(7, 10).chars().iterator();
        column = partition(columnIterator, 0, 8);
    }

    private int partition(final PrimitiveIterator.OfInt iterator, final int min, final int max) {
        final int middle = (max - min + 1) / 2;
        if (iterator.hasNext()) {
            final SeatLocationIdentifier identifier = SeatLocationIdentifier.valueOfToken(iterator.next());
            if (identifier.getHalfType() == SeatLocationIdentifier.HalfType.LOWER) {
                return partition(iterator, min, min + middle - 1);
            }
            return partition(iterator, min + middle, max);
        }
        return min;
    }

    public Long getSeatID() {
        return (long) row * 8 + column;
    }
}
