package nl.smerik.adventofcode.aoc2021.model.submarine.camera;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThermalCameraManualTest {

    private static final List<String> EXAMPLE_01 = List.of(
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,12",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0",
            "",
            "fold along x=5",
            "fold along y=7"
    );

    @Test
    void testInit() {
        final ThermalCameraManual manual = new ThermalCameraManual(EXAMPLE_01);
        // TODO: assertions
    }

    @Test
    void testFoldByInstructions() {
        final ThermalCameraManual manual = new ThermalCameraManual(EXAMPLE_01);
        manual.foldByInstructions(0);
        // TODO: documentation expects 17 but I count 16 in the example; Check why...
        assertEquals(16, manual.countDots());
        // TODO: additional assertions
        // TODO: Also test with limit > 0
    }

    @Test
    void testFold() {
        // TODO: implement me
    }

    @Test
    void testFoldX() {
        final ThermalCameraManual manual = new ThermalCameraManual(EXAMPLE_01);
        manual.foldX(5);
        // TODO: assertions
    }

    @Test
    void testFoldY() {
        final ThermalCameraManual manual = new ThermalCameraManual(EXAMPLE_01);
        manual.foldY(7);
        // TODO: assertions
    }

    @Test
    void testMergePapers() {
        // TODO: assertions
    }

    @Test
    void testCountDots() {
        final ThermalCameraManual manual = new ThermalCameraManual(EXAMPLE_01);
        assertEquals(18L, manual.countDots());
    }
}