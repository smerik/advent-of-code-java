package nl.smerik.adventofcode.aoc2024.model.notes;

import java.util.ArrayList;
import java.util.List;

public class AssortmentOfNotes {

    private final List<Integer> listOne = new ArrayList<>();
    private final List<Integer> listTwo = new ArrayList<>();

    public AssortmentOfNotes(final List<String> lines) {
        lines.forEach(this::parseLine);
    }

    private void parseLine(final String line) {
        final String[] lists = line.split(" {3}");
        listOne.add(Integer.parseInt(lists[0]));
        listTwo.add(Integer.parseInt(lists[1]));
    }

    public Integer calculateTotalDistance() {
        return determineDistances().stream().mapToInt(Integer::intValue).sum();
    }

    public List<Integer> determineDistances() {
        final List<Integer> result = new ArrayList<>();
        final List<Integer> sortedListOne = listOne.stream().sorted().toList();
        final List<Integer> sortedListTwo = listTwo.stream().sorted().toList();
        for (int i = 0; i < sortedListOne.size(); i++) {
            final Integer distance = Math.abs(sortedListTwo.get(i) - sortedListOne.get(i));
            result.add(distance);
        }
        return result;
    }

    public Long calculateTotalSimilarityScore() {
        long result = 0;
        for (Integer number : listOne) {
            long count = listTwo.stream().filter(n -> n.equals(number)).count();
            result += number * count;
        }
        return result;
    }
}
