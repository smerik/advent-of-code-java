package nl.smerik.adventofcode.aoc2020.model.allergen;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Day 21 Allergen Assessment model.
 */
@Slf4j
public class AllergenAssessment {

    private final Set<Food> foods;

    /**
     * Constructs the allergen assessment by parsing the day input lines.
     *
     * @param foodLines the day input containing the ingredients per food
     */
    public AllergenAssessment(final List<String> foodLines) {
        foods = parseFoodLines(foodLines);
    }

    private Set<Food> parseFoodLines(final List<String> foodLines) {
        return foodLines.stream().map(Food::new).collect(Collectors.toSet());
    }

    /**
     * Finds ingredients which might contain any allergens and map those ingredients by allergen.
     *
     * @return ingredients by allergen
     */
    public Map<String, Set<String>> findPossibleIngredientMatchesPerAllergen() {
        final Map<String, Set<String>> result = new HashMap<>();
        for (final Food food : foods) {
            for (final String allergen : food.getAllergens()) {
                final Set<String> ingredients = result.computeIfAbsent(allergen, k -> new HashSet<>(food.getIngredients()));
                final boolean changed = ingredients.retainAll(food.getIngredients());
                LOG.info("Allergen '{}' part of ingredients '{}' changed? {}", allergen, ingredients, changed);
            }
        }
        return result;
    }

    /**
     * Count the total number of ingredients which cannot possibly contain any allergens.
     *
     * @return the number of ingredients not possibly containing allergens
     */
    public long countIngredientsNotPossiblyContainingAllergens() {
        final Set<String> possibleAllergens = findPossibleIngredientMatchesPerAllergen().values()
                                                                                        .stream()
                                                                                        .flatMap(Set::stream)
                                                                                        .collect(Collectors.toSet());
        return foods.stream()
                    .map(Food::getIngredients)
                    .flatMap(Set::stream)
                    .filter(ingredient -> !possibleAllergens.contains(ingredient))
                    .count();
    }
}
