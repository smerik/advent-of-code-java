package nl.smerik.adventofcode.aoc2020.model.passport;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Passport {

    private Integer birthYear;
    private Integer issueYear;
    private Integer expirationYear;
    private String height;
    private String hairColor;
    private String eyeColor;
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
