package nl.smerik.adventofcode.aoc2020.model.combat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Data
public class CombatGame {

    private final Set<CombatPlayer> players;

    public CombatGame(final List<String> lines) {
        players = CombatParser.parse(lines);
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
            if (LOG.isTraceEnabled()) {
                players.forEach(player -> LOG.trace("Player {}'s deck: {}", player.getId(), player.getDeck()));
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
        final Map<Integer, CombatPlayer> playersMappedByTakenCard = players.stream().collect(Collectors.toMap(CombatPlayer::drawCard, Function.identity()));
        final List<Integer> sortedCards = playersMappedByTakenCard.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        final CombatPlayer winner = playersMappedByTakenCard.get(sortedCards.get(0));
        LOG.debug("Player {} wins the round!", winner.id);
        winner.addCards(sortedCards);
        return winner;
    }
}
