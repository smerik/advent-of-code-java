package nl.smerik.adventofcode.aoc2024.model.printer;

import java.util.*;
import java.util.function.Predicate;

public class SafetyManual {

    private final Map<Integer, Set<Integer>> pagesToBePrintedAfterPage;
    private final List<List<Integer>> pagesPerUpdate;

    public SafetyManual(final List<String> lines) {
        pagesToBePrintedAfterPage = new HashMap<>();
        pagesPerUpdate = new ArrayList<>();
        parseLines(lines);
    }

    private void parseLines(final List<String> lines) {
        boolean parsePageOrdingRules = true;
        for (final String line : lines) {
            if (line.isBlank()) {
                parsePageOrdingRules = false;
                continue;
            }
            if (parsePageOrdingRules) {
                parsePageOrdingRules(line);
                continue;
            }
            parsePagesPerUpdate(line);
        }
    }

    private void parsePageOrdingRules(final String line) {
        final String[] pageRules = line.split("\\|");
        final int page = Integer.parseInt(pageRules[0]);
        final int printAfterPage = Integer.parseInt(pageRules[1]);
        pagesToBePrintedAfterPage.computeIfAbsent(page, k -> new HashSet<>()).add(printAfterPage);
    }

    private void parsePagesPerUpdate(final String line) {
        final List<Integer> pagesToUpdate = Arrays.stream(line.split(",")).mapToInt(Integer::valueOf).boxed().toList();
        pagesPerUpdate.add(pagesToUpdate);
    }

    public long sumMiddlePagesOfCorrectlyOrderedUpdates() {
        return findCorrectlyOrderedUpdates().stream().map(this::findMiddlePage).mapToInt(pageNumber -> pageNumber).sum();
    }

    public long sumMiddlePagesOfFixedIncorrectlyOrderedUpdates() {
        return findIncorrectlyOrderedUpdates().stream()
                .map(this::fixIncorrectlyOrderedUpdate)
                .map(this::findMiddlePage)
                .mapToInt(pageNumber -> pageNumber)
                .sum();
    }

    public Integer findMiddlePage(final List<Integer> pages) {
        return pages.get(pages.size() / 2);
    }

    public List<Integer> fixIncorrectlyOrderedUpdate(final List<Integer> pages) {
        return pages.stream().sorted((page1, page2) -> {
            if (pagesToBePrintedAfterPage.getOrDefault(page1, Collections.emptySet()).contains(page2)) {
                return -1;
            } else if (pagesToBePrintedAfterPage.getOrDefault(page2, Collections.emptySet()).contains(page1)) {
                return 1;
            }
            return 0;
        }).toList();
    }

    public List<List<Integer>> findCorrectlyOrderedUpdates() {
        return this.pagesPerUpdate.stream().filter(this::isUpdateInTheRightOrder).toList();
    }

    public List<List<Integer>> findIncorrectlyOrderedUpdates() {
        return this.pagesPerUpdate.stream().filter(Predicate.not(this::isUpdateInTheRightOrder)).toList();
    }

    public boolean isUpdateInTheRightOrder(final List<Integer> pagesToUpdate) {
        final int size = pagesToUpdate.size();
        for (int i = 0; i < size; i++) {
            final int pageToUpdate = pagesToUpdate.get(i);
            for (final int page : pagesToUpdate.subList(i + 1, size)) {
                if (pagesToBePrintedAfterPage.getOrDefault(page, Collections.emptySet()).contains(pageToUpdate)) {
                    return false;
                }
            }
        }
        return true;
    }
}
