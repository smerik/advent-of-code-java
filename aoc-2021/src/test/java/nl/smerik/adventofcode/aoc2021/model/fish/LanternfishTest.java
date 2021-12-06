package nl.smerik.adventofcode.aoc2021.model.fish;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LanternfishTest {

    @Test
    void testInit() {
        final Lanternfish fish1 = new Lanternfish();
        assertEquals(8, fish1.getTimer());

        final Lanternfish fish2 = new Lanternfish(1);
        assertEquals(1, fish2.getTimer());
    }

    @Test
    void simulate() {
        final Lanternfish fish = new Lanternfish(1);
        final Optional<Lanternfish> noNewbornFish = fish.simulate();
        assertEquals(0, fish.getTimer());
        assertTrue(noNewbornFish.isEmpty());

        final Optional<Lanternfish> newBornFish = fish.simulate();
        assertEquals(6, fish.getTimer());
        assertTrue(newBornFish.isPresent());
        assertEquals(8, newBornFish.get().getTimer());
    }

    @Test
    void resetTimer() {
        final Lanternfish fish = new Lanternfish();
        fish.resetTimer();
        assertEquals(6, fish.getTimer());
    }
}