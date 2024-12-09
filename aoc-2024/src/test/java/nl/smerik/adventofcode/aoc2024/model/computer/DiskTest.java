package nl.smerik.adventofcode.aoc2024.model.computer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DiskTest {

    private static Stream<Arguments> provideSourceForDiskMap() {
        return Stream.of(
          Arguments.of("0..111....22222", "12345"),
          Arguments.of("000000000111111111222222222", "90909"),
          Arguments.of("00...111...2...333.44.5555.6666.777.888899", "2333133121414131402")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDiskMap")
    void testDiskMap(final String expectedResult, final String line) {
        final Disk disk = new Disk(line);
        assertEquals(expectedResult, disk.toString());
    }


    private static Stream<Arguments> provideSourceForDefragment() {
        return Stream.of(
          Arguments.of("022111222......", "12345"),
          Arguments.of("0099811188827773336446555566..............", "2333133121414131402")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDefragment")
    void testDefragment(final String expectedResult, final String line) {
        final Disk disk = new Disk(line);
        disk.defragment();
        assertEquals(expectedResult, disk.toString());
    }

    @Test
    void testCalculateChecksum() {
        // Given
        final String line = "2333133121414131402";
        final Disk disk = new Disk(line);
        // When
        disk.defragment();
        // Then
        assertEquals(1928, disk.calculateChecksum());
    }
}