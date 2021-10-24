package nl.smerik.adventofcode.aoc2020.model.combat;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CombatParser {

    private static final Pattern PLAYER_LINE_PATTERN = Pattern.compile("Player (?<playerId>\\d+):");

    private CombatParser() {
    }

    public static Set<CombatPlayer> parse(final List<String> lines) {
        final Set<CombatPlayer> players = new HashSet<>();

        boolean parsePlayer = true;
        CombatPlayer player = new CombatPlayer(-1);
        for (final String line : lines) {
            if (parsePlayer) {
                final Matcher matcher = PLAYER_LINE_PATTERN.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("Given line is not a player line: '" + line + "'");
                }
                player = new CombatPlayer(Integer.parseInt(matcher.group("playerId")));
                players.add(player);
                parsePlayer = false;
                continue;
            }
            if (StringUtils.isBlank(line)) {
                parsePlayer = true;
                continue;
            }
            player.addCard(Integer.parseInt(line));
        }
        return players;
    }
}
