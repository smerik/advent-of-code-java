package nl.smerik.adventofcode.aoc2021.model.fish;

import lombok.Getter;

import java.util.Optional;

@Getter
public class Lanternfish {

    public static final int TIMER_INITIAL_VALUE = 8;
    public static final int TIMER_RESET_VALUE = 6;

    private int timer;

    /**
     * Constructs a new lanternfish with an initial timer of {@value TIMER_INITIAL_VALUE}.
     */
    public Lanternfish() {
        this(TIMER_INITIAL_VALUE);
    }

    /**
     * Constructs a new lanternfish with given timer value as initial timer.
     */
    public Lanternfish(final int timer) {
        this.timer = timer;
    }

    /**
     * Simulates a day of fish live.
     * In case a new fish has been created, it will return that fish; Empty otherwise.
     *
     * @return the optional newborn fish
     */
    public Optional<Lanternfish> simulate() {
        if (timer == 0) {
            resetTimer();
            return Optional.of(new Lanternfish());
        }
        timer--;
        return Optional.empty();
    }

    /**
     * Resets the fish timer to the default reset value {@value TIMER_RESET_VALUE}.
     */
    void resetTimer() {
        timer = TIMER_RESET_VALUE;
    }
}
