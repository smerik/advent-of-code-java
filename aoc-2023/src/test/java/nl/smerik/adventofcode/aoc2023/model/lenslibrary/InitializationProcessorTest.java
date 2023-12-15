package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializationProcessorTest {

    private static final String INPUT_EXAMPLE_01 = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";

    @Test
    void sumInitializationSteps() {
        final InitializationProcessor processor = new InitializationProcessor(INPUT_EXAMPLE_01);
        assertEquals(1320, processor.sumInitializationSteps());
    }
}