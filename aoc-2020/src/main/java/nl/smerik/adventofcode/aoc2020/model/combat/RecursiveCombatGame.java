package nl.smerik.adventofcode.aoc2020.model.combat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Data
public class RecursiveCombatGame {

    private final Set<CombatPlayer> players;

    private int totalGameCount;

    public RecursiveCombatGame(final List<String> lines) {
        players = CombatParser.parse(lines);
        totalGameCount = 0;
    }

    /**
     * Plays the game until the game ends and returns the winning player.
     *
     * @return the winning player
     */
    public CombatPlayer playUntilGameEnds() {
        final Map<CombatPlayer, Integer> deckSizeMappedByPlayer = players.stream().collect(
                Collectors.toMap(Function.identity(), player -> player.getDeck().size()));
        return playUntilGameEnds(deckSizeMappedByPlayer);
    }

    private CombatPlayer playUntilGameEnds(final Map<CombatPlayer, Integer> deckSizeMappedByPlayer) {
        this.totalGameCount++; // We are starting a new game here

        final int gameId = totalGameCount;
        final Set<CombatPlayer> currentGamePlayers = deckSizeMappedByPlayer.entrySet().stream().map(e -> new CombatPlayer(e.getKey(), e.getValue())).collect(Collectors.toSet());

        int round = 0;
        CombatPlayer winningPlayer = null;
        LOG.trace("=== Game {} ===", gameId);
        while (currentGamePlayers.stream().noneMatch(CombatPlayer::deckIsEmpty)) {
            round++;
            LOG.trace("-- Round {} (Game {}) --", round, gameId);
            if (LOG.isTraceEnabled()) {
                currentGamePlayers.forEach(player -> LOG.trace("Player {}'s deck: {}", player.getId(), player.getDeck()));
            }

            if (currentGamePlayers.stream().anyMatch(CombatPlayer::isDeckInSameOrderAsBefore)) {
                LOG.trace("Infinite game detected: player 1 always wins the game!");
                winningPlayer = currentGamePlayers.stream().filter(p -> p.getId() == 1).findAny().orElseThrow();
                break;
            }

            winningPlayer = play(gameId, currentGamePlayers);
            LOG.trace("Player {} wins round {} of game {}!", winningPlayer.id, round, gameId);
        }
        if (winningPlayer == null) {
            throw new IllegalStateException("Game should end with a winning player.");
        }
        LOG.trace("The winner of game {} is player {}!", gameId, winningPlayer.id);
        if (gameId == 1) {
            LOG.debug("== Post-game results ==");
            if (LOG.isDebugEnabled()) {
                currentGamePlayers.forEach(player -> LOG.debug("Player {}'s deck: {}", player.getId(), player.getDeck()));
            }
        }
        return winningPlayer;
    }

    /**
     * Plays the game for one round and returns the round winning player.
     *
     * @param gameId the current game play id
     * @param currentGamePlayers the combat players in game
     * @return the winning player
     */
    public CombatPlayer play(final int gameId, final Set<CombatPlayer> currentGamePlayers) {
        CombatPlayer winner = null;
        final Map<CombatPlayer, Integer> takenCardMappedByPlayers = currentGamePlayers.stream().collect(Collectors.toMap(Function.identity(), CombatPlayer::drawCard));
            if (shouldPlayRecursiveCombat(takenCardMappedByPlayers)) {
                LOG.trace("Playing a sub-game to determine the winner...");
                final int subGameWinnerId = playUntilGameEnds(takenCardMappedByPlayers).getId();
                winner = currentGamePlayers.stream().filter(player -> player.getId() == subGameWinnerId).findAny().orElseThrow();
                LOG.trace("...anyway, back to game {}.", gameId);
            } else {
                winner = Collections.max(takenCardMappedByPlayers.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
            }

        final CombatPlayer finalWinner = winner;
        // Sort winner card to be the first
        final List<Integer> sortedCards = takenCardMappedByPlayers.entrySet()
                                                                  .stream()
                                                                  .sorted((o1, o2) -> o1.getKey().equals(finalWinner) ? -1 : 1)
                                                                  .map(Map.Entry::getValue)
                                                                  .toList();
        winner.addCards(sortedCards);
        return winner;
    }

    /**
     * Checks if both players have at least as many cards remaining in their deck as the value of the card they just drew.
     *
     * @param takenCardMappedByPlayers the card values mapped by player
     * @return <code>true</code> when a sub-game needs to be played; <code>false</code> otherwise
     */
    private boolean shouldPlayRecursiveCombat(final Map<CombatPlayer, Integer> takenCardMappedByPlayers) {
        return takenCardMappedByPlayers.entrySet().stream().allMatch(e -> e.getKey().getDeck().size() >= e.getValue());
    }
}
