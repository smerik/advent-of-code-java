package nl.smerik.adventofcode.aoc2023.model.gondola;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EngineSchematicTest {

    private final List<String> example01part01 = List.of(
            // @formatter:off
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."
            // @formatter:on
    );
    final EngineSchematic schema = new EngineSchematic(example01part01);

    @Test
    void testValidateNonPartNumbers() {
        Set<Integer> numbers = schema.getSchemaNumbers().stream().filter(schemaNumber -> !schemaNumber.isEnginePart()).map(EngineSchematic.SchemaNumber::getNumber).collect(Collectors.toSet());
        assertEquals(Set.of(114, 58), numbers);
    }

    @Test
    void testValidatePartNumbers() {
        Set<Integer> numbers = schema.getSchemaNumbers().stream().filter(EngineSchematic.SchemaNumber::isEnginePart).map(EngineSchematic.SchemaNumber::getNumber).collect(Collectors.toSet());
        assertEquals(Set.of(467, 35, 633, 617, 592, 755, 664, 598), numbers);
    }

    @Test
    void testSumNumbers() {
        assertEquals(4361, schema.sumPartNumbers());
    }
}