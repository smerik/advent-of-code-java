package nl.smerik.adventofcode.aoc2020.model.passport;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Passport {

    @NotNull
    @Min(1920)
    @Max(2002)
    private Integer birthYear;

    @NotNull
    @Min(2010)
    @Max(2020)
    private Integer issueYear;

    @NotNull
    @Min(2020)
    @Max(2030)
    private Integer expirationYear;

    @NotBlank
    @Pattern(regexp = "((1[5-8][0-9]|19[0-3])cm)|((59|6[0-9]|7[0-6])in)")
    private String height;

    @NotBlank
    @Pattern(regexp = "#([0-9]|[a-f]){6}")
    private String hairColor;

    @NotBlank
    @Pattern(regexp = "amb|blu|brn|gry|grn|hzl|oth")
    private String eyeColor;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String passportId;

    private Integer countryId;

    /**
     * Returns true when all required fields are filled; false otherwise.
     * <br>
     * Optional field is:
     * <ul>
     *     <li>countryId</li>
     * </ul>
     *
     * @return true when valid; false otherwise
     */
    public boolean isValid() {
        return birthYear != null
                && issueYear != null
                && expirationYear != null
                && StringUtils.isNotBlank(height)
                && StringUtils.isNotBlank(hairColor)
                && StringUtils.isNotBlank(eyeColor)
                && StringUtils.isNotBlank(passportId);
    }
}
