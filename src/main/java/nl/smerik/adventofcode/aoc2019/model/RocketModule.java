package nl.smerik.adventofcode.aoc2019.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RocketModule {

    private Long mass;
    public static final BigDecimal DIVISOR = new BigDecimal("3");
    public static final BigDecimal TWO = new BigDecimal("2");

    public RocketModule(final Long mass) {
        this.mass = mass;
    }

//    public Long getRequiredFuel() {
//        return BigDecimal.valueOf(mass)
//                .divide(DIVISOR, RoundingMode.FLOOR)
//                .subtract(TWO)
//                .longValue();
//    }

    public Long getRequiredFuel() {
        return calculateFuel(BigDecimal.valueOf(mass)).longValue();
    }

    private BigDecimal calculateFuel(final BigDecimal m) {
        // TODO: use Long instead of BigDecimal
        BigDecimal result = m.divide(DIVISOR, RoundingMode.FLOOR).subtract(TWO);

        if (result.signum() == 1) {
            BigDecimal x = calculateFuel(result);
            return result.add(x);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "SantaModule{" +
                "mass=" + mass +
                '}';
    }
}
