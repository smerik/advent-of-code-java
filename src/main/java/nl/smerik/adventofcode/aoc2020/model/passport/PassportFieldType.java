package nl.smerik.adventofcode.aoc2020.model.passport;

public enum PassportFieldType {

    BIRTH_YEAR("byr"),
    ISSUE_YEAR("iyr"),
    EXPIRATION_YEAR("eyr"),
    HEIGHT("hgt"),
    HAIR_COLOR("hcl"),
    EYE_COLOR("ecl"),
    PASSPORT_ID("pid"),
    COUNTRY_ID("cid");

    private final String key;

    PassportFieldType(final String key) {
        this.key = key;
    }

    public static PassportFieldType valueOfKey(final String label) {
        for (final PassportFieldType type : values()) {
            if (type.key.equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid label '" + label + "'");
    }
}
