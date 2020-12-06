package nl.smerik.adventofcode.aoc2020.service.customs;

import nl.smerik.adventofcode.aoc2020.model.customs.GroupAnswers;
import nl.smerik.adventofcode.aoc2020.model.customs.PersonAnswers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomsService {

    public List<GroupAnswers> parseAnswers(final List<String> input) {
        final List<GroupAnswers> result = new ArrayList<>();

        GroupAnswers groupAnswers = new GroupAnswers();
        result.add(groupAnswers);
        for (final String line : input) {
            if (StringUtils.isBlank(line)) {
                groupAnswers = new GroupAnswers();
                result.add(groupAnswers);
            }
            final PersonAnswers personAnswers = new PersonAnswers(line);
            groupAnswers.addAnswers(personAnswers);
        }
        return result;
    }

    public int sumCountUnionOfAnswers(final List<GroupAnswers> groupAnswers) {
        return groupAnswers.stream()
                           .mapToInt(GroupAnswers::countUnionOfAnswers)
                           .sum();
    }
}