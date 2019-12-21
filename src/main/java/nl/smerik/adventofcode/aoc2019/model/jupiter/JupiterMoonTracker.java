package nl.smerik.adventofcode.aoc2019.model.jupiter;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Getter
public class JupiterMoonTracker {

    private static final Logger LOGGER = LoggerFactory.getLogger(JupiterMoonTracker.class);

    private final List<Moon> moons;
    private final Map<Integer, List<Integer>> states;

    private int currentStep;

    public JupiterMoonTracker(final List<Moon> moons) {
        this.moons = moons;

        this.currentStep = 0;
        this.states = new HashMap<>();
        storeHashedUniverse();
    }

    public void simulateMotion(final Long steps) {
        for (long i = 0; i < steps; i++) {
            currentStep++;

            applyGravity();
            moons.forEach(Moon::applyVelocity);

//            storeHashedUniverse();
        }
    }

    private void storeHashedUniverse() {
        final List<Integer> steps = states.computeIfAbsent(hashUniverse(), k -> new ArrayList<>());
        steps.add(currentStep);
    }

    private int hashUniverse() {
        final List<Object> hashedObjects = new ArrayList<>();
        for (final Moon moon : moons) {
            hashedObjects.add(moon);
            hashedObjects.add(moon.getVelocity());
            hashedObjects.add(moon.getPosition());
        }
        return Objects.hash(hashedObjects);
    }

    private void applyGravity() {
        final Set<Moon> pairedMoons = new HashSet<>();
        for (final Moon pairedMoon : moons) {
            for (final Moon moon : moons) {
                if (pairedMoons.contains(moon)) {
                    continue;
                }
                pairedMoon.applyGravity(moon);
            }
            pairedMoons.add(pairedMoon);
        }
    }

    /**
     * Calculates the total energy in the system.
     *
     * @return the total energy
     */
    public int getTotalEnergy() {
        return moons.stream().mapToInt(Moon::getTotalEnergy).sum();
    }
}