package nl.smerik.adventofcode.aoc2024.model.notes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssortmentOfNotesTest {

    private static final List<String> EXAMPLE_01 = List.of(
            "3   4",
            "4   3",
            "2   5",
            "1   3",
            "3   9",
            "3   3"
    );

    @Test
    void testCalculateTotalDistance() {
        // Given
        final AssortmentOfNotes assortmentOfNotes = new AssortmentOfNotes(EXAMPLE_01);
        // When & Then
        assertEquals(11, assortmentOfNotes.calculateTotalDistance());
    }

    @Test
    void testDetermineDistances() {
        // Given
        final AssortmentOfNotes assortmentOfNotes = new AssortmentOfNotes(EXAMPLE_01);
        // When
        final List<Integer> result = assortmentOfNotes.determineDistances();
        // Then
        assertEquals(2, result.get(0));
        assertEquals(1, result.get(1));
        assertEquals(0, result.get(2));
        assertEquals(1, result.get(3));
        assertEquals(2, result.get(4));
        assertEquals(5, result.get(5));
    }
}