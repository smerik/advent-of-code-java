package nl.smerik.adventofcode.aoc2019.model;


import nl.smerik.adventofcode.aoc2019.model.RocketModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RocketModuleTest {

    @Test
    void getRequiredFuel() {
        RocketModule module = new RocketModule(100756L);
        RocketModule module2 = new RocketModule(1969L);

        Assertions.assertEquals(50346L, module.getRequiredFuel());
        Assertions.assertEquals(966, module2.getRequiredFuel());
    }
}