package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class NanoFactoryWorkingPartly2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(NanoFactoryWorkingPartly2.class);

    private Map<String, Reaction> reactionRules;

    public NanoFactoryWorkingPartly2(final List<Reaction> reactionRules) {
        this.reactionRules = new HashMap<>();
        for (final Reaction reactionRule : reactionRules) {
            this.reactionRules.put(reactionRule.getProducesChemicalUnits().getChemical(), reactionRule);
        }
    }

    public Map<String, Integer> getHowMuchChemicalUnitsIsNeededToProduce(final ChemicalUnits producedChemicalUnits) {
        final Map<String, Integer> result = new HashMap<>();
        final Map<String, Integer> stock = new HashMap<>();
        final Map<String, Integer> foo = produceRequiredChemicalUnits(result, stock, producedChemicalUnits);
        LOGGER.info("stock:{}", stock);
        LOGGER.info("result:{}", result);
        return foo;
    }

    private Map<String, Integer> produceRequiredChemicalUnits(final Map<String, Integer> result,
                                                              final Map<String, Integer> stock,
                                                              final ChemicalUnits chemicalUnitsToProduce) {

        if ("ORE".equals(chemicalUnitsToProduce.getChemical())) {
            addToStock(chemicalUnitsToProduce, stock, result);
            return result;
        }

        final Reaction reaction = reactionRules.get(chemicalUnitsToProduce.getChemical());

        final int timesToRunReaction = chemicalUnitsToProduce.getUnits() / reaction.getProducesChemicalUnits().getUnits()
                + (chemicalUnitsToProduce.getUnits() % reaction.getProducesChemicalUnits().getUnits() == 0 ? 0 : 1);


        for (int i = 0; i < timesToRunReaction; i++) {

            for (ChemicalUnits requiredChemicalUnit : reaction.getConsumesChemicalUnits()) {
                final int requiredUnits = requiredChemicalUnit.getUnits();

                if (!stock.containsKey(requiredChemicalUnit.getChemical())
                        || requiredUnits > stock.get(requiredChemicalUnit.getChemical())) {

                    produceRequiredChemicalUnits(result, stock, requiredChemicalUnit);
                }
                removeFromStock(stock, requiredChemicalUnit);
            }
            addToStock(reaction.getProducesChemicalUnits(), stock, result);
        }

        return result;
    }

    private void addToStock(final ChemicalUnits chemicalUnits, final Map<String, Integer> stock, final Map<String, Integer> result) {
        LOGGER.debug("Add: {}", chemicalUnits);
        stock.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Integer::sum);
        result.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Integer::sum);
    }

    private void removeFromStock(final Map<String, Integer> stock, final ChemicalUnits chemicalUnits) {
        LOGGER.debug("remove: {}", chemicalUnits);
        stock.merge(chemicalUnits.getChemical(), -chemicalUnits.getUnits(), Integer::sum);
    }
}
