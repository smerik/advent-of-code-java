package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import nl.smerik.adventofcode.aoc2020.model.satellite.SatelliteMessageDocument;
import nl.smerik.adventofcode.aoc2020.model.satellite.SatelliteMessageRule;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day19Service {

    @Value("classpath:input/day-19.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        return initSatelliteMessageDocument().findMessagesMatchingRule(0).size();
    }

    @SneakyThrows
    public Integer getSolutionPart2() {
        final SatelliteMessageDocument satelliteMessageDocument = initSatelliteMessageDocument();
        SatelliteMessageRule rule8 = satelliteMessageDocument.getMessageRulesByRuleId().get(8);
        SatelliteMessageRule rule42 = satelliteMessageDocument.getMessageRulesByRuleId().get(42);
        SatelliteMessageRule rule11 = satelliteMessageDocument.getMessageRulesByRuleId().get(11);
        SatelliteMessageRule rule31 = satelliteMessageDocument.getMessageRulesByRuleId().get(31);

        rule8.addSubRuleList(List.of(rule42, rule8));
        rule11.addSubRuleList(List.of(rule42, rule11, rule31));

        return satelliteMessageDocument.findMessagesMatchingRule(0).size();
    }

    private SatelliteMessageDocument initSatelliteMessageDocument() {
        final List<String> receivedMessages = PuzzleInputParser.parseToString(resource);
        return new SatelliteMessageDocument(receivedMessages);
    }
}
