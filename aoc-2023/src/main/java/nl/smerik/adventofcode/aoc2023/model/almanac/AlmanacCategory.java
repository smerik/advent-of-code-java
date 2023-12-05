package nl.smerik.adventofcode.aoc2023.model.almanac;

public enum AlmanacCategory {

    SEED("seed"),
    SOIL("soil"),
    FERTILIZER("fertilizer"),
    WATER("water"),
    LIGHT("light"),
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    LOCATION("location");

    private final String category;

    AlmanacCategory(final String category) {
        this.category = category;
    }

    public static AlmanacCategory valueOfCategory(final String category) {
        for (final AlmanacCategory almanacCategory : values()) {
            if (almanacCategory.category.equals(category)) {
                return almanacCategory;
            }
        }
        throw new IllegalArgumentException("Invalid category '" + category + "'");
    }
}
