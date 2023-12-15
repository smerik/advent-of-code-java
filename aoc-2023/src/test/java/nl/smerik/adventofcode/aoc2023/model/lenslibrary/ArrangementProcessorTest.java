package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementProcessorTest {

    private static final String INPUT_EXAMPLE_01 = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";

    @Test
    void testGetLensBoxesByAsciiHash() {
        // Given
        final ArrangementProcessor processor = new ArrangementProcessor(INPUT_EXAMPLE_01);
        // When
        final Map<Integer, LensBox> result = processor.getLensBoxesByAsciiHash();
        // Then
        assertEquals("Box 0: [rn 1][cm 2]", result.get(0).toString());
        assertTrue(result.get(1).isEmpty());
        assertEquals("Box 3: [ot 7][ab 5][pc 6]", result.get(3).toString());
        assertEquals(3, result.size());
    }

    @Test
    void testSumFocusingPower() {
        final ArrangementProcessor processor = new ArrangementProcessor(INPUT_EXAMPLE_01);
        assertEquals(145, processor.sumFocusingPower());
    }
}