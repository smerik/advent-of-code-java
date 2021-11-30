package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class NanoFactory {

    private Map<String, Reaction> reactionRules;

    public NanoFactory(final Set<Reaction> reactionRules) {
        this.reactionRules = new HashMap<>();
        for (final Reaction reactionRule : reactionRules) {
            this.reactionRules.put(reactionRule.getProducesChemicalUnits().getChemical(), reactionRule);
        }
    }

    public Map<String, Long> getHowMuchChemicalUnitsIsNeededToProduce(final ChemicalUnits producedChemicalUnits) {
        final Map<String, Long> result = new HashMap<>();
        final Map<String, Long> stock = new HashMap<>();
        return produceRequiredChemicalUnits(producedChemicalUnits, result, stock);
    }

    private Map<String, Long> produceRequiredChemicalUnits(final ChemicalUnits chemicalUnitsToProduce,
                                                           final Map<String, Long> result,
                                                           final Map<String, Long> stock) {

        //TODO: make constant
        if ("ORE".equals(chemicalUnitsToProduce.getChemical())) {
            addToStock(chemicalUnitsToProduce, stock, result);
            return result;
        }

        final Reaction reaction = reactionRules.get(chemicalUnitsToProduce.getChemical());
        final long timesToRunReaction = chemicalUnitsToProduce.getUnits() / reaction.getProducesChemicalUnits().getUnits()
                + (chemicalUnitsToProduce.getUnits() % reaction.getProducesChemicalUnits().getUnits() == 0 ? 0 : 1);
        for (int i = 0; i < timesToRunReaction; i++) {

            for (final ChemicalUnits requiredChemicalUnit : reaction.getConsumesChemicalUnits()) {
                final long requiredUnits = requiredChemicalUnit.getUnits();
                final long unitsInStock = stock.computeIfAbsent(requiredChemicalUnit.getChemical(), k -> 0L);
                if (requiredUnits > unitsInStock) {
                    // TODO: improve variable name
                    final ChemicalUnits chemicalUnits = new ChemicalUnits(requiredChemicalUnit.getChemical(), requiredUnits - unitsInStock);
                    produceRequiredChemicalUnits(chemicalUnits, result, stock);
                }
                removeFromStock(stock, requiredChemicalUnit);
            }
            addToStock(reaction.getProducesChemicalUnits(), stock, result);
        }

        return result;
    }

    private void addToStock(final ChemicalUnits chemicalUnits,
                            final Map<String, Long> stock,
                            final Map<String, Long> result) {

        stock.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Long::sum);
        result.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Long::sum);
    }

    private void removeFromStock(final Map<String, Long> stock, final ChemicalUnits chemicalUnits) {
        stock.merge(chemicalUnits.getChemical(), -1 * chemicalUnits.getUnits(), Long::sum);
    }

    public Long getHowMuchFuelCanBeProduced(final Long oreUnits) {
        return 0L;
    }
}
