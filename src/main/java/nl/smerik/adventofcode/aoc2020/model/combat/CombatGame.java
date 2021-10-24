package nl.smerik.adventofcode.aoc2020.model.combat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Data
public class CombatGame {

    private static final Pattern PLAYER_LINE_PATTERN = Pattern.compile("Player (?<playerId>\\d+):");

    private final Set<CombatPlayer> players = new HashSet<>();

    public CombatGame(final List<String> lines) {
        parse(lines);
    }

    private void parse(final List<String> lines) {
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
    }

    /**
     * Plays the game until the game ends and returns the winning player.
     *
     * @return the winning player
     */
    public CombatPlayer playUntilGameEnds() {
        int round = 0;
        CombatPlayer winningPlayer = null;
        while (players.stream().noneMatch(CombatPlayer::deckIsEmpty)) {
            round++;
            LOG.debug("-- Round {} --", round);
            if (LOG.isDebugEnabled()) {
                players.forEach(player -> LOG.debug("Player {}'s deck: {}", player.getId(), player.getDeck()));
            }
            winningPlayer = play();
        }
        if (winningPlayer == null) {
            throw new IllegalStateException("Game should end with a winning player.");
        }
        LOG.debug("== Post-game results ==");
        if (LOG.isDebugEnabled()) {
            players.forEach(player -> LOG.debug("Player {}'s deck: {}", player.getId(), player.getDeck()));
        }
        return winningPlayer;
    }

    /**
     * Plays the game for one round and returns the round winning player.
     *
     * @return the winning player
     */
    public CombatPlayer play() {
        final Map<Integer, CombatPlayer> playersMappedByTakenCard  = players.stream().collect(Collectors.toMap(CombatPlayer::drawCard, Function.identity()));
        final List<Integer> sortedCards = playersMappedByTakenCard.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        final CombatPlayer winner = playersMappedByTakenCard.get(sortedCards.get(0));
        LOG.debug("Player {} wins the round!", winner.id);
        winner.addCards(sortedCards);
        return winner;
    }
}
