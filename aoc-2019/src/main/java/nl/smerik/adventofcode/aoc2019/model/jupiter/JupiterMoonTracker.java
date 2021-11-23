package nl.smerik.adventofcode.aoc2019.model.jupiter;

import lombok.Getter;
import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.apache.commons.math3.util.ArithmeticUtils.lcm;

@Getter
public class JupiterMoonTracker {

    private static final Logger LOGGER = LoggerFactory.getLogger(JupiterMoonTracker.class);

    private final List<Moon> moons;

    private long currentStep;

    public JupiterMoonTracker(final List<Moon> moons) {
        this.moons = moons;
        this.currentStep = 0;
    }

    /**
     * Simulates the motion of all moons in time steps.
     * Within each time step, first the velocity of every moon is being updated by applying gravity.
     * Then, once all moons' velocities have been updated, the position of every moon is updated by applying velocity.
     *
     * @param steps the time steps
     */
    public void simulateMotion(final long steps) {
        for (long i = 0; i < steps; i++) {
            currentStep++;

            applyGravity();
            moons.forEach(Moon::applyVelocity);
        }
    }

    /**
     * Simulates the motions until the moons' positions and velocities exactly match a previous point in time.
     * <p>
     * NOTE:
     * This is the implementation for Day 12 - Part 2, which was a hard one.
     * I needed to checkout the reddit SOLUTION MEGATHREAD
     * <a href="https://www.reddit.com/r/adventofcode/comments/e9j0ve/2019_day_12_solutions">-🎄- 2019 Day 12 Solutions -🎄-</a>.
     * The general solution relies on the fact that X, Y and Z are actually independent.
     * This means you can figure out how long X takes to repeat by itself,
     * how long Y takes to repeat by itself, and how long Z takes to repeat by itself.
     * <p>
     * In the end my solution is based on
     * <a href="https://github.com/rweekers/adventofcode-2019/blob/master/src/main/kotlin/adventofcode/Exercise12.kt">rweekers' solution</a>
     * using the least common multiple (lcm).
     */
    public long simulateStepsToSameState() {
        final List<Pair<Integer, Integer>> startingStateX = getStateX();
        final List<Pair<Integer, Integer>> startingStateY = getStateY();
        final List<Pair<Integer, Integer>> startingStateZ = getStateZ();

        Long stepsToSameStateX = null;
        Long stepsToSameStateY = null;
        Long stepsToSameStateZ = null;
        while (stepsToSameStateX == null || stepsToSameStateY == null || stepsToSameStateZ == null) {
            simulateMotion(1);
            if (stepsToSameStateX == null && startingStateX.equals(getStateX())) {
                stepsToSameStateX = currentStep;
            }
            if (stepsToSameStateY == null && startingStateY.equals(getStateY())) {
                stepsToSameStateY = currentStep;
            }
            if (stepsToSameStateZ == null && startingStateZ.equals(getStateZ())) {
                stepsToSameStateZ = currentStep;
            }
        }
        return lcm(stepsToSameStateX, lcm(stepsToSameStateY, stepsToSameStateZ));
    }

    private List<Pair<Integer, Integer>> getStateX() {
        return moons.stream().map(moon -> new Pair<>(moon.getPosition().x, moon.getVelocity().x)).toList();
    }

    private List<Pair<Integer, Integer>> getStateY() {
        return moons.stream().map(moon -> new Pair<>(moon.getPosition().y, moon.getVelocity().y)).toList();
    }

    private List<Pair<Integer, Integer>> getStateZ() {
        return moons.stream().map(moon -> new Pair<>(moon.getPosition().z, moon.getVelocity().z)).toList();
    }

    /**
     * Applies gravity to every pair of moons.
     */
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
    public long getTotalEnergy() {
        return moons.stream().mapToLong(Moon::getTotalEnergy).sum();
    }
}