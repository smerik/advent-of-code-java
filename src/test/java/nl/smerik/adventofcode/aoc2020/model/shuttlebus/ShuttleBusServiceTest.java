package nl.smerik.adventofcode.aoc2020.model.shuttlebus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleBusServiceTest {

    @Test
    void initShuttleBusService() {
        final ShuttleBusService busService = new ShuttleBusService("7,13,x,x,59,x,31,19");
        assertEquals(5, busService.getBuses().size());
    }

    @Test
    void findNextBusToTake() {
        final ShuttleBusService busService = new ShuttleBusService("7,13,x,x,59,x,31,19");
        assertEquals(59, busService.findEarliestBusToTake(939).getId());
    }
}