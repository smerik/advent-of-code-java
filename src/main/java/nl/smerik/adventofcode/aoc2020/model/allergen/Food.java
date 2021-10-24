package nl.smerik.adventofcode.aoc2020.model.allergen;

import lombok.Data;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Food {

    private static final Pattern FOOD_LINE_PATTERN = Pattern.compile("(?<ingredients>.+)\\(contains (?<allergens>.+)\\)");
    private final Set<String> ingredients;
    private final Set<String> allergens;

    public Food(final String foodLine) {
        final Matcher matcher = FOOD_LINE_PATTERN.matcher(foodLine);
        if (!matcher.find()) {
            throw new IllegalArgumentException("No food line: " + foodLine);
        }
        this.ingredients = Set.of(matcher.group("ingredients").split(" "));
        this.allergens = Set.of(matcher.group("allergens").split(", "));
    }
}
