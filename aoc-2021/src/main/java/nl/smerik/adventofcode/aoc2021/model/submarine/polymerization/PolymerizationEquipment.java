package nl.smerik.adventofcode.aoc2021.model.submarine.polymerization;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class PolymerizationEquipment {

    private final Map<String, Character> pairInsertionRules;

    private String polymerTemplate;

    public PolymerizationEquipment(final List<String> lines) {
        this.pairInsertionRules = parsePairInsertionRules(lines);
        this.polymerTemplate = lines.get(0);
    }

    private Map<String, Character> parsePairInsertionRules(final List<String> lines) {
        final Map<String, Character> result = new HashMap<>();
        for (int i = 2; i < lines.size(); i++) {
            final String[] rule = lines.get(i).split(" -> ");
            result.put(rule[0], rule[1].charAt(0));
        }
        return result;
    }

    public String applyRules(final int steps) {
        String result = polymerTemplate;
        for (int i = 0; i < steps; i++) {
            result = applyRules();
        }
        return result;
    }

    public String applyRules() {
        final StringBuilder resultBuilder = new StringBuilder();
        final char[] elements = this.polymerTemplate.toCharArray();
        for (int i = 0; i < elements.length - 1; i++) {
            resultBuilder.append(elements[i]);
            final String elementsPair = Character.toString(elements[i]) + elements[i + 1];
            final Character element = pairInsertionRules.get(elementsPair);
            if (element != null) {
                resultBuilder.append(element);
            }
        }
        resultBuilder.append(elements[elements.length - 1]);

        this.polymerTemplate = resultBuilder.toString();
        return this.polymerTemplate;
    }

    public Map<Character, Long> countElements() {
        return this.polymerTemplate.chars()
                                   .boxed()
                                   .collect(Collectors.groupingBy(element -> (char) element.intValue(), Collectors.counting()));
    }

    public Character findLeastCommonElement() {
        return countElements().entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow().getKey();
    }

    public Character findMostCommonElement() {
        return countElements().entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getKey();
    }
}