package nl.smerik.adventofcode.aoc2019.model;

public class RocketModule {

    public static final int DIVISOR = 3;
    public static final int SUBTRAHEND = 2;

    private final Integer mass;

    public RocketModule(final Integer mass) {
        this.mass = mass;
    }

    public Integer calculateRequiredFuel(final boolean includeAddedFuel) {
        return calculateRequiredFuel(mass, includeAddedFuel);
    }

    private Integer calculateRequiredFuel(final Integer mass, final boolean includeAddedFuel) {
        final int result = Math.floorDiv(mass, DIVISOR) - SUBTRAHEND;
        if (!includeAddedFuel) {
            return result;
        }

        if (Math.signum(result) == 1) {
            return result + calculateRequiredFuel(result, true);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "SantaModule{" +
                "mass=" + mass +
                '}';
    }
}
