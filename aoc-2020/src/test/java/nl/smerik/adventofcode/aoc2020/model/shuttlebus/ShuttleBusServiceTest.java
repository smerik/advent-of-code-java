package nl.smerik.adventofcode.aoc2020.model.shuttlebus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShuttleBusServiceTest {

    @Test
    void initShuttleBusService() {
        final ShuttleBusService busService = new ShuttleBusService("7,13,x,x,59,x,31,19");
        assertEquals(5, busService.getBuses().size());
    }

    @Test
    void getSubsequentTimestamp() {
        final ShuttleBusService busService = new ShuttleBusService("7,13,x,x,59,x,31,19");
        for (ShuttleBus bus : busService.getBuses()) {
            switch (bus.getId()) {
                case 7 -> assertEquals(0L, bus.getSubsequentTimestamp());
                case 13 -> assertEquals(1L, bus.getSubsequentTimestamp());
                case 59 -> assertEquals(4L, bus.getSubsequentTimestamp());
                case 31 -> assertEquals(6L, bus.getSubsequentTimestamp());
                case 19 -> assertEquals(7L, bus.getSubsequentTimestamp());
                default -> throw new IllegalArgumentException("Unexpected bus ID " + bus.getId());
            }
        }
    }

    @Test
    void findEarliestBusToTake() {
        final ShuttleBusService busService = new ShuttleBusService("7,13,x,x,59,x,31,19");
        assertEquals(59, busService.findEarliestBusToTake(939).getId());
    }

    private static Stream<Arguments> provideSourceForFindSubsequentBusDeparturesTimestamp() {
        return Stream.of(
                // @formatter:off
                Arguments.of("7,13,x,x,59,x,31,19",    1068781L),
                Arguments.of("17,x,13,19"         ,       3417L),
                Arguments.of("67,7,59,61"         ,     754018L),
                Arguments.of("67,x,7,59,61"       ,     779210L),
                Arguments.of("67,7,x,59,61"       ,    1261476L),
                Arguments.of("1789,37,47,1889"    , 1202161486L)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForFindSubsequentBusDeparturesTimestamp")
    void findSubsequentBusDepartures(final String busIDsInService, final long expectedTimestamp) {
        final ShuttleBusService busService = new ShuttleBusService(busIDsInService);
        assertEquals(expectedTimestamp, busService.findSubsequentBusDeparturesTimestamp(0L));
    }
}