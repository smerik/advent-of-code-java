package nl.smerik.adventofcode.aoc2020.model.cpu;

public interface Processor {

    /**
     * Runs the instruction set.
     */
    void run();

    /**
     * @return the accumulator value
     */
    int getAccumulator();
}
