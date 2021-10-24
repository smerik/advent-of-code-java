package nl.smerik.adventofcode.aoc2020.model.allergen;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllergenAssessmentTest {

    private final List<String> example01Part01 = List.of(
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
    );

    @Test
    void testFindPossibleIngredientMatchesPerAllergen() {
        final AllergenAssessment assessment = new AllergenAssessment(example01Part01);
        final Map<String, Set<String>> ingredientMappedByAllergen = assessment.findPossibleIngredientMatchesPerAllergen();
        assertEquals(Set.of("mxmxvkd"), ingredientMappedByAllergen.get("dairy"));
        assertEquals(Set.of("sqjhc", "mxmxvkd"), ingredientMappedByAllergen.get("fish"));
        assertEquals(Set.of("sqjhc", "fvjkl"), ingredientMappedByAllergen.get("soy"));
    }

    @Test
    void testCountIngredientsNotPossiblyContainingAllergens() {
        final AllergenAssessment assessment = new AllergenAssessment(example01Part01);
        assertEquals(5L, assessment.countIngredientsNotPossiblyContainingAllergens());
    }

    @Test
    void testDetermineIngredientContainingAllergen() {
        final AllergenAssessment assessment = new AllergenAssessment(example01Part01);
        final Map<String, String> ingredientByAllergen = Map.of(
                "dairy", "mxmxvkd",
                "soy", "fvjkl",
                "fish", "sqjhc"
        );
        assertEquals(ingredientByAllergen, assessment.determineIngredientContainingAllergen());
    }

    @Test
    void testProduceCanonicalDangerousIngredientList() {
        final AllergenAssessment assessment = new AllergenAssessment(example01Part01);
        assertEquals("mxmxvkd,sqjhc,fvjkl", assessment.produceCanonicalDangerousIngredientList());
    }
}