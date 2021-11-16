package nl.smerik.adventofcode.aoc2020.model.allergen;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {

    public static Stream<Arguments> example() {
        return Stream.of(
                // @formatter:off
                Arguments.of("mxmxvkd kfcds sqjhc nhms (contains dairy, fish)", Set.of("mxmxvkd", "kfcds"  , "sqjhc", "nhms"   ), Set.of("dairy", "fish")),
                Arguments.of("trh fvjkl sbzzf mxmxvkd (contains dairy)"       , Set.of("trh"    , "fvjkl"  , "sbzzf", "mxmxvkd"), Set.of("dairy"        )),
                Arguments.of("sqjhc fvjkl (contains soy)"                     , Set.of("sqjhc"  , "fvjkl"                      ), Set.of("soy"          )),
                Arguments.of("sqjhc mxmxvkd sbzzf (contains fish)"            , Set.of("sqjhc"  , "mxmxvkd", "sbzzf"           ), Set.of("fish"         ))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("example")
    void testConstructor(final String line, final Set<String> ingredients, final Set<String> allergens) {
        final Food food = new Food(line);
        assertEquals(ingredients, food.getIngredients());
        assertEquals(allergens, food.getAllergens());
    }
}