package nl.smerik.adventofcode.aoc2019.model.spaceship;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Point;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EmergencyHullPaintingRobotTest {

    @Mock
    private IntcodeComputer intcodeComputer;

    @Test
    void Day11Part01Test01() {
        final Hull hull = new Hull();
        final EmergencyHullPaintingRobot robot = new EmergencyHullPaintingRobot(hull, intcodeComputer);
        Mockito.when(intcodeComputer.run(Mockito.anyLong()))
                .thenReturn(Arrays.asList(1L, 0L))
                .thenReturn(Arrays.asList(0L, 0L))
                .thenReturn(Arrays.asList(1L, 0L))
                .thenReturn(Arrays.asList(1L, 0L))
                .thenReturn(Arrays.asList(0L, 1L))
                .thenReturn(Arrays.asList(1L, 0L))
                .thenReturn(Arrays.asList(1L, 0L));
        Mockito.when(intcodeComputer.isPausedExecution())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        robot.draw();

        assertEquals(6, hull.countPaintedPanels());

        assertNotNull(hull.getPanels().get(new Point(0, 0)));
        assertNotNull(hull.getPanels().get(new Point(-1, 0)));
        assertNotNull(hull.getPanels().get(new Point(-1, -1)));
        assertNotNull(hull.getPanels().get(new Point(0, -1)));
        assertNotNull(hull.getPanels().get(new Point(1, 1)));

        assertEquals(2, hull.getPanels().get(new Point(0, 0)).getPaintedTimes());

        assertEquals(Color.BLACK, hull.getPanels().get(new Point(0, 0)).getCurrentColor());
        assertEquals(Color.BLACK, hull.getPanels().get(new Point(-1, 0)).getCurrentColor());
        assertEquals(Color.WHITE, hull.getPanels().get(new Point(-1, -1)).getCurrentColor());
        assertEquals(Color.WHITE, hull.getPanels().get(new Point(0, -1)).getCurrentColor());
        assertEquals(Color.WHITE, hull.getPanels().get(new Point(1, 1)).getCurrentColor());
    }
}