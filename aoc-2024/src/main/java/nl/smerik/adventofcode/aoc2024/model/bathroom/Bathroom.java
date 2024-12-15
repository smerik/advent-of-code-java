package nl.smerik.adventofcode.aoc2024.model.bathroom;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class Bathroom {

    private static final Pattern ROBOT_PATTERN = Pattern.compile("p=(?<x>\\d+),(?<y>\\d+) v=(?<vx>-?\\d+),(?<vy>-?\\d+)");

    private final int width;
    private final int height;
    private final Set<Robot> robots;

    public Bathroom(final int width, final int height, final List<String> lines) {
        this.width = width;
        this.height = height;
        this.robots = parseLines(lines);
    }

    private Set<Robot> parseLines(final List<String> lines) {
        final Set<Robot> result = new HashSet<>();
        for (final String line : lines) {
            result.add(parseLine(line));
        }
        return result;
    }

    private Robot parseLine(final String line) {
        final Matcher matcher = ROBOT_PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Unknown robot line '" + line + "'");
        }
        final int posX = Integer.parseInt(matcher.group("x"));
        final int posY = Integer.parseInt(matcher.group("y"));
        final int velocityX = Integer.parseInt(matcher.group("vx"));
        final int velocityY = Integer.parseInt(matcher.group("vy"));
        return new Robot(width, height, new Point(posX, posY), velocityX, velocityY);
    }

    public void move(final int seconds) {
        robots.forEach(robot -> robot.move(seconds));
    }

    public Map<Point, Set<Robot>> mapRobotsByPosition() {
        return robots.stream().collect(Collectors.groupingBy(Robot::getCurrentPosition, Collectors.toSet()));
    }

    public int calculateSafetyFactor() {
        return mapRobotsByQuadrant().values().stream().map(Set::size).reduce(1, (a, b) -> a * b);
    }

    public Map<Integer, Set<Robot>> mapRobotsByQuadrant() {
        final int horizontalAxis = height / 2;
        final int verticalAxis = width / 2;

        final Map<Integer, Set<Robot>> result = new HashMap<>();
        for (final Robot robot : robots) {
            if (robot.getCurrentPosition().y < horizontalAxis) {
                if (robot.getCurrentPosition().x < verticalAxis) {
                    // upper left
                    result.computeIfAbsent(1, key -> new HashSet<>()).add(robot);
                } else if (robot.getCurrentPosition().x > verticalAxis) {
                    // upper right
                    result.computeIfAbsent(2, key -> new HashSet<>()).add(robot);
                }
            } else if (robot.getCurrentPosition().y > horizontalAxis) {
                if (robot.getCurrentPosition().x < verticalAxis) {
                    // bottom left
                    result.computeIfAbsent(3, key -> new HashSet<>()).add(robot);
                } else if (robot.getCurrentPosition().x > verticalAxis) {
                    // bottom right
                    result.computeIfAbsent(4, key -> new HashSet<>()).add(robot);
                }
            }
        }
        return result;
    }

    public int determineSecondsToFindEasterEgg() {
        int result = 0;
        while (!hasEasterEgg()) {
            move(1);
            result++;
        }
        LOG.info("{}{}", System.lineSeparator(), render());
        return result;
    }

    public boolean hasEasterEgg() {
        return robots.stream()
                .map(Robot::getCurrentPosition)
                .collect(Collectors.groupingBy(point -> point.y, Collectors.toSet()))
                .values()
                .stream()
                .anyMatch(pointsOnSameY -> {
                    final List<Integer> sortedX = pointsOnSameY.stream()
                            .map(point -> point.x)
                            .sorted()
                            .toList();

                    int consecutiveCount = 1;
                    for (int i = 1; i < sortedX.size(); i++) {
                        if (sortedX.get(i).equals(sortedX.get(i - 1) + 1)) {
                            consecutiveCount++;
                            if (consecutiveCount >= 8) {
                                render();
                                return true;
                            }
                        } else {
                            consecutiveCount = 1;
                        }
                    }
                    return false;
                });
    }

    public String render() {
        final char[][] grid = new char[height][width];
        robots.forEach(robot -> grid[robot.getCurrentPosition().y][robot.getCurrentPosition().x] = '#');
        final StringBuilder stringBuilder = new StringBuilder();
        for (final char[] row : grid) {
            for (int col = 0; col < grid[0].length; col++) {
                stringBuilder.append(row[col] == '#' ? '#' : '.');
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
