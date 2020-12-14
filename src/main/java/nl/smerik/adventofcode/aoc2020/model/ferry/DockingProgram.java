package nl.smerik.adventofcode.aoc2020.model.ferry;

public class DockingProgram extends AbstractDockingProgram {

    private Long andMask;
    private Long orMask;

    public DockingProgram() {
        super();
        andMask = 0L;
        orMask = 0L;
    }

    @Override
    public void updateBitmask(final String bitmask) {
        andMask = Long.parseLong(bitmask.replace('X', '1'), 2);
        orMask = Long.parseLong(bitmask.replace('X', '0'), 2);
    }

    @Override
    public void writeToMemory(final long address, final long value) {
        memory.put(address, applyBitmask(value));
    }

    private Long applyBitmask(final long value) {
        return value & andMask | orMask;
    }
}
