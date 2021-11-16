package nl.smerik.adventofcode.aoc2020.model.allergen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The Day 21 Allergen Assessment model.
 */
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
                ingredients.retainAll(food.getIngredients());
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

    /**
     * Figures out which ingredient contains which allergen.
     *
     * @return ingredient mapped by allergen
     */
    public Map<String, String> determineIngredientContainingAllergen() {
        final Map<String, Set<String>> possibleIngredientMatchesPerAllergen = findPossibleIngredientMatchesPerAllergen();
        final Map<String, String> result = possibleIngredientMatchesPerAllergen.entrySet()
                .stream()
                .filter(e -> e.getValue().size() == 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new ArrayList<>(e.getValue()).get(0)
                ));
        while (result.size() != possibleIngredientMatchesPerAllergen.size()) {
            for (final Map.Entry<String, Set<String>> entry : possibleIngredientMatchesPerAllergen.entrySet()) {
                for (final Map.Entry<String, String> resultEntry : result.entrySet()) {
                    if (!resultEntry.getKey().equals(entry.getKey())
                            && entry.getValue().remove(resultEntry.getValue())
                            && entry.getValue().size() == 1) {
                        result.put(entry.getKey(), entry.getValue().stream().findAny().orElseThrow());
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Produces the canonical dangerous ingredient list.
     * <p>
     * This is a list of ingredients arranged alphabetically by their allergen
     * and separated by commas without any spaces.
     *
     * @return the canonical dangerous ingredient list
     */
    public String produceCanonicalDangerousIngredientList() {
        // A TreeMap uses the natural ordering of its keys.
        return String.join(",", new TreeMap<>(determineIngredientContainingAllergen()).values());
    }
}
