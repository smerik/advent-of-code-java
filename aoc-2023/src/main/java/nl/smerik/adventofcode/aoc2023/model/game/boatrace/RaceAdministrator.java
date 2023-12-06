package nl.smerik.adventofcode.aoc2023.model.game.boatrace;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class RaceAdministrator {

    private static final Pattern NUMBERS_PATTERN = Pattern.compile("(?<number>\\d+)");

    private final Set<Race> races;

    public RaceAdministrator(final List<String> lines, boolean ignoreSpacesBetweenNumbers) {
        races = ignoreSpacesBetweenNumbers? parseLinesIgnoringSpaces(lines) : parseLines(lines);
    }

    private Set<Race> parseLinesIgnoringSpaces(List<String> lines) {
        final long raceDuration = Long.parseLong(lines.get(0).substring("Time:".length()).replace(" ", ""));
        final long recordDistance = Long.parseLong(lines.get(1).substring("Distance:".length()).replace(" ", ""));
        return Set.of(new Race(raceDuration, recordDistance));
    }

    private Set<Race> parseLines(final List<String> lines) {
        final List<Integer> raceDurations = retrieveNumbers(lines.get(0));
        final List<Integer> distanceRecords = retrieveNumbers(lines.get(1));

        final Set<Race> result = new HashSet<>(raceDurations.size());
        for (int i = 0; i < raceDurations.size(); i++) {
            result.add(new Race(raceDurations.get(i), distanceRecords.get(i)));
        }
        return result;
    }

    private List<Integer> retrieveNumbers(final String line) {
        final List<Integer> result = new ArrayList<>();
        final Matcher matcher = NUMBERS_PATTERN.matcher(line);
        while (matcher.find()) {
            result.add(Integer.valueOf(matcher.group("number")));
        }
        return result;
    }

    public long calcMarginOfError() {
        return races.stream().map(Race::determineNumberOfWaysToWinRace).reduce(1, (a, b) -> a * b);
    }
}
