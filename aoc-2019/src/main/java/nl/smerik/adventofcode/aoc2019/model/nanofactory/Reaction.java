package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import lombok.Data;

import java.util.List;

@Data
public class Reaction {

    private final ChemicalUnits producesChemicalUnits;
    private final List<ChemicalUnits> consumesChemicalUnits;

    public Reaction(final ChemicalUnits producesChemicalUnits, final List<ChemicalUnits> consumesChemicalUnits) {
        this.producesChemicalUnits = producesChemicalUnits;
        this.consumesChemicalUnits = consumesChemicalUnits;
    }
}
