package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class NanoFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(NanoFactory.class);

    private Map<String, Reaction> reactionRules;

    public NanoFactory(final Set<Reaction> reactionRules) {
        this.reactionRules = new HashMap<>();
        for (final Reaction reactionRule : reactionRules) {
            this.reactionRules.put(reactionRule.getProducesChemicalUnits().getChemical(), reactionRule);
        }
    }

    public Map<String, Integer> getHowMuchChemicalUnitsIsNeededToProduce(final ChemicalUnits producedChemicalUnits) {
        final Map<String, Integer> result = new HashMap<>();
        final Map<String, Integer> stock = new HashMap<>();
        final Map<String, Integer> foo = produceRequiredChemicalUnits(producedChemicalUnits, result, stock);
        LOGGER.info("stock:{}", stock);
        LOGGER.info("result:{}", result);
        LOGGER.info("foo:{}", foo);
        return foo;
    }

    private Map<String, Integer> produceRequiredChemicalUnits(final ChemicalUnits chemicalUnitsToProduce,
                                                              final Map<String, Integer> result,
                                                              final Map<String, Integer> stock) {

        //TODO: make constant
        if ("ORE".equals(chemicalUnitsToProduce.getChemical())) {
            addToStock(chemicalUnitsToProduce, stock, result);
            return result;
        }

        final Reaction reaction = reactionRules.get(chemicalUnitsToProduce.getChemical());
        final int timesToRunReaction = chemicalUnitsToProduce.getUnits() / reaction.getProducesChemicalUnits().getUnits()
                + (chemicalUnitsToProduce.getUnits() % reaction.getProducesChemicalUnits().getUnits() == 0 ? 0 : 1);
        for (int i = 0; i < timesToRunReaction; i++) {

            for (final ChemicalUnits requiredChemicalUnit : reaction.getConsumesChemicalUnits()) {
                final int requiredUnits = requiredChemicalUnit.getUnits();
                final int unitsInStock = stock.computeIfAbsent(requiredChemicalUnit.getChemical(), k -> 0);
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
                            final Map<String, Integer> stock,
                            final Map<String, Integer> result) {

        LOGGER.debug("Add: {}", chemicalUnits);
        stock.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Integer::sum);
        result.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Integer::sum);
    }

    private void removeFromStock(final Map<String, Integer> stock, final ChemicalUnits chemicalUnits) {
        LOGGER.debug("remove: {}", chemicalUnits);
        stock.merge(chemicalUnits.getChemical(), -chemicalUnits.getUnits(), Integer::sum);
    }

    public int getHowMuchFuelCanBeProduced(final int oreUnits) {
        return 0;
    }
}
