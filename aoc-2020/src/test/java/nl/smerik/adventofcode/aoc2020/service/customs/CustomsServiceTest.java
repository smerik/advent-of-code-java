package nl.smerik.adventofcode.aoc2020.service.customs;

import nl.smerik.adventofcode.aoc2020.model.customs.GroupAnswers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomsServiceTest {

    private static List<String> example01Part01;

    @Autowired
    private CustomsService customsService;

    @BeforeAll
    public static void initAll() {
        example01Part01 = List.of(
                "abc",
                "",
                "a",
                "b",
                "c",
                "",
                "ab",
                "ac",
                "",
                "a",
                "a",
                "a",
                "",
                "b"
        );
    }

    @Test
    void parseAnswers() {
        final List<GroupAnswers> groupAnswers = customsService.parseAnswers(example01Part01);
        assertEquals(5, groupAnswers.size());
    }

    @Test
    void sumCountUnionOfAnswers() {
        final List<GroupAnswers> groupAnswers = customsService.parseAnswers(example01Part01);
        assertEquals(11, customsService.sumCountUnionOfAnswers(groupAnswers));
    }

    @Test
    void sumCountIntersectionOfAnswers() {
        final List<GroupAnswers> groupAnswers = customsService.parseAnswers(example01Part01);
        assertEquals(6, customsService.sumCountIntersectionOfAnswers(groupAnswers));
    }
}