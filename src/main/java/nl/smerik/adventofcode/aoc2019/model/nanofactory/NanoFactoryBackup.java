package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class NanoFactoryBackup {

    private static final Logger LOGGER = LoggerFactory.getLogger(NanoFactoryBackup.class);

    private Map<String, Reaction> reactionRules;

    public NanoFactoryBackup(final List<Reaction> reactionRules) {
        this.reactionRules = new HashMap<>();
        for (final Reaction reactionRule : reactionRules) {
            this.reactionRules.put(reactionRule.getProducesChemicalUnits().getChemical(), reactionRule);
        }
    }

    public Map<String, Integer> getHowMuchChemicalUnitsIsNeededToProduce(final ChemicalUnits producedChemicalUnits) {
//        final Reaction reaction = this.reactionRules.get(producedChemicalUnits.getChemical());
//        if (reaction == null) {
//            return Collections.emptyMap();
//        }
//
//
        final Map<String, Integer> stock = new HashMap<>();
        return produceRequiredChemicalUnits(stock, producedChemicalUnits);
//        for (final ChemicalUnits chemicalUnits : reaction.getConsumesChemicalUnits()) {
//            result.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Integer::sum);
//        }
//        for (String key : result.keySet()) {
//            final Reaction reactionX = this.reactionRules.get(key);
//            getHowMuchChemicalUnitsIsNeededToProduce(reaction)
//        return result;
    }

    private Map<String, Integer> produceRequiredChemicalUnits(final Map<String, Integer> stock,
                                                              final ChemicalUnits chemicalUnitsToProduce) {

        final Map<String, Integer> result = new HashMap<>();

        // TODO: what to do with ORE to consume...

        final Reaction reaction = reactionRules.get(chemicalUnitsToProduce.getChemical());

        final int timesToRunReaction = chemicalUnitsToProduce.getUnits() / reaction.getProducesChemicalUnits().getUnits()
                + (chemicalUnitsToProduce.getUnits() % reaction.getProducesChemicalUnits().getUnits() == 0 ? 0 : 1);


        for (int i = 0; i < timesToRunReaction; i++) {

            for (ChemicalUnits requiredChemicalUnit : reaction.getConsumesChemicalUnits()) {
                final int requiredUnits = requiredChemicalUnit.getUnits();

                if ("ORE".equals(requiredChemicalUnit.getChemical())) {
                    addToStock(requiredChemicalUnit, stock);
                    continue;
                }

                if (!stock.containsKey(requiredChemicalUnit.getChemical()) || requiredUnits > stock.get(requiredChemicalUnit.getChemical())) {
                    produceRequiredChemicalUnits(stock, requiredChemicalUnit);
                }
                removeFromStock(stock, requiredChemicalUnit);
            }
            addToStock(reaction.getProducesChemicalUnits(), stock);
//            result.merge(reaction.getProducesChemicalUnits(), requiredUnits, Integer::sum);
        }

        return result;


        //////////////////////////////////////////////////
        // Poging 2
        //////////////////////////////////////////////////
//        result.merge(chemicalUnitsToProduce.getChemical(), chemicalUnitsToProduce.getUnits(), Integer::sum);
//
//        long foo = 10L;
//
//        final Reaction reaction = this.reactionRules.get(chemicalUnitsToProduce.getChemical());
//        for (ChemicalUnits requiredChemicalUnit : reaction.getConsumesChemicalUnits()) {
//            if ("ORE".equals(requiredChemicalUnit.getChemical())) {
//                result.merge(requiredChemicalUnit.getChemical(), requiredChemicalUnit.getUnits(), Integer::sum);
//                continue;
//            }
//
//            if (requiredChemicalUnit.getUnits() > result.get(requiredChemicalUnit.getChemical())) {
//                produceRequiredChemicalUnits(result, requiredChemicalUnit);
//            }
//        }


        //////////////////////////////////////////////////
        // Poging 1
        //////////////////////////////////////////////////
//        final List<ChemicalUnits> requiredChemicalUnits = this.reactionRules.get(chemicalUnitsToProduce.getChemical()).getConsumesChemicalUnits();
//        for (final ChemicalUnits requiredChemicalUnit : requiredChemicalUnits) {
//            // add consumed chemical units to existing result
//            result.merge(requiredChemicalUnit.getChemical(), requiredChemicalUnit.getUnits(), Integer::sum);
//            // if (total consumed chemical units > produced) { produceNewUnit() ;
//            if (this.reactionRules.get(requiredChemicalUnit.getChemical()) <= 1) {
//
//            }
//        }


//        final int timesToRunReaction = producedChemicalUnits.getUnits() / reaction.getProducesChemicalUnits().getUnits()
//                + (producedChemicalUnits.getUnits() % reaction.getProducesChemicalUnits().getUnits() == 0 ? 0 : 1);
//        for (int i = 0; i < timesToRunReaction; i++) {
//            LOGGER.info("result-before loop:{}", result);
//            for (final ChemicalUnits chemicalUnits : reaction.getConsumesChemicalUnits()) {
//                LOGGER.info("Merging result: {}...", result);
//                result.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Integer::sum);
//                LOGGER.info("Finished merging result: {}.", result);
//
////                // TODO: check if total amount needs to be updated because it can be possible we already produced enough
//                LOGGER.info("Calling getHowMuchChemicalUnitsIsNeededToProduce({}})", chemicalUnits);
//                final Map<String, Integer> neededToProduce = getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnits);
//                LOGGER.info("neededToProduce:{}", neededToProduce);
//                neededToProduce.forEach((key, value) -> {
//                    LOGGER.info("merging - key:'{}', value:{} into result:{}", key, value, result);
//                    result.merge(key, value, Integer::sum);
//                });
//            }
//            LOGGER.info("result-after loop:{}", result);
//
//        }
//        LOGGER.info("result:{}", result);
//        return result;
    }

    private void addToStock(final ChemicalUnits chemicalUnitsToProduce, final Map<String, Integer> stock) {
        stock.merge(chemicalUnitsToProduce.getChemical(), chemicalUnitsToProduce.getUnits(), Integer::sum);
    }

    private void removeFromStock(final Map<String, Integer> stock, final ChemicalUnits chemicalUnitsToProduce) {
        stock.merge(chemicalUnitsToProduce.getChemical(), -chemicalUnitsToProduce.getUnits(), Integer::sum);
    }

    public Map<String, Integer> getHowMuchChemicalUnitsIsNeededToProduceBak(final ChemicalUnits producedChemicalUnits) {
        final Reaction reaction = this.reactionRules.get(producedChemicalUnits.getChemical());
        if (reaction == null) {
            return Collections.emptyMap();
        }

        final Map<String, Integer> result = new HashMap<>();
        final int timesToRunReaction = producedChemicalUnits.getUnits() / reaction.getProducesChemicalUnits().getUnits()
                + (producedChemicalUnits.getUnits() % reaction.getProducesChemicalUnits().getUnits() == 0 ? 0 : 1);
        for (int i = 0; i < timesToRunReaction; i++) {
            for (final ChemicalUnits chemicalUnits : reaction.getConsumesChemicalUnits()) {
                // TODO: check if total amount needs to be updated because it can be possible we already produced enough
                result.merge(chemicalUnits.getChemical(), chemicalUnits.getUnits(), Integer::sum);

                final Map<String, Integer> neededToProduce = getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnits);
                neededToProduce.forEach((key, value) -> result.merge(key, value, Integer::sum));
            }
        }
        return result;
    }
}
