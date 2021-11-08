package nl.smerik.adventofcode.aoc2020.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComboBreakerUtilTest {

    @Test
    void testCalculateValue() {
        assertEquals(5764801, ComboBreakerUtil.calculateValue(1, 5764801));
        assertEquals(5764801, ComboBreakerUtil.calculateValue(1, 5764801));
    }

    @Test
    void testDetermineLoopSize() {
        assertEquals(8, ComboBreakerUtil.determineLoopSize(5764801));
        assertEquals(11, ComboBreakerUtil.determineLoopSize(17807724));
    }

    @Test
    void testCalculateEncryptionKey() {
        assertEquals(14897079, ComboBreakerUtil.calculateEncryptionKey(17807724, 8));
        assertEquals(14897079, ComboBreakerUtil.calculateEncryptionKey(5764801, 11));
    }

    @Test
    void testDetermineEncryptionKey() {
        assertEquals(14897079, ComboBreakerUtil.determineEncryptionKey(5764801, 17807724));
    }
}