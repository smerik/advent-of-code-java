package nl.smerik.adventofcode.aoc2021.model.octopus;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DumboOctopusCavernTest {

    private static final List<String> EXAMPLE_01 = List.of(
            "11111",
            "19991",
            "19191",
            "19991",
            "11111"
    );

    private static final List<String> EXAMPLE_02 = List.of(
            "5483143223",
            "2745854711",
            "5264556173",
            "6141336146",
            "6357385478",
            "4167524645",
            "2176841721",
            "6882881134",
            "4846848554",
            "5283751526"
    );

    private static Stream<Arguments> simulateMeasurementSteps() {
        return Stream.of(
                // @formatter:off
                // Example 01 - After step 1
                Arguments.of(EXAMPLE_01, 1, List.of("34543",
                                                    "40004",
                                                    "50005",
                                                    "40004",
                                                    "34543")),
                // Example 01 - After step 2
                Arguments.of(EXAMPLE_01, 2, List.of("45654",
                                                    "51115",
                                                    "61116",
                                                    "51115",
                                                    "45654")),
                // Example 02 - After step 1
                Arguments.of(EXAMPLE_02,   1, List.of("6594254334",
                                                      "3856965822",
                                                      "6375667284",
                                                      "7252447257",
                                                      "7468496589",
                                                      "5278635756",
                                                      "3287952832",
                                                      "7993992245",
                                                      "5957959665",
                                                      "6394862637")),
                // Example 02 - After step 2
                Arguments.of(EXAMPLE_02,   2, List.of("8807476555",
                                                      "5089087054",
                                                      "8597889608",
                                                      "8485769600",
                                                      "8700908800",
                                                      "6600088989",
                                                      "6800005943",
                                                      "0000007456",
                                                      "9000000876",
                                                      "8700006848")),
                // Example 02 - After step 3
                Arguments.of(EXAMPLE_02,   3, List.of("0050900866",
                                                      "8500800575",
                                                      "9900000039",
                                                      "9700000041",
                                                      "9935080063",
                                                      "7712300000",
                                                      "7911250009",
                                                      "2211130000",
                                                      "0421125000",
                                                      "0021119000")),
                // Example 02 - After step 4
                Arguments.of(EXAMPLE_02,   4, List.of("2263031977",
                                                      "0923031697",
                                                      "0032221150",
                                                      "0041111163",
                                                      "0076191174",
                                                      "0053411122",
                                                      "0042361120",
                                                      "5532241122",
                                                      "1532247211",
                                                      "1132230211")),
                // Example 02 - After step 5
                Arguments.of(EXAMPLE_02,   5, List.of("4484144000",
                                                      "2044144000",
                                                      "2253333493",
                                                      "1152333274",
                                                      "1187303285",
                                                      "1164633233",
                                                      "1153472231",
                                                      "6643352233",
                                                      "2643358322",
                                                      "2243341322")),
                // Example 02 - After step 6
                Arguments.of(EXAMPLE_02,   6, List.of("5595255111",
                                                      "3155255222",
                                                      "3364444605",
                                                      "2263444496",
                                                      "2298414396",
                                                      "2275744344",
                                                      "2264583342",
                                                      "7754463344",
                                                      "3754469433",
                                                      "3354452433")),
                // Example 02 - After step 7
                Arguments.of(EXAMPLE_02,   7, List.of("6707366222",
                                                      "4377366333",
                                                      "4475555827",
                                                      "3496655709",
                                                      "3500625609",
                                                      "3509955566",
                                                      "3486694453",
                                                      "8865585555",
                                                      "4865580644",
                                                      "4465574644")),
                // Example 02 - After step 8
                Arguments.of(EXAMPLE_02,   8, List.of("7818477333",
                                                      "5488477444",
                                                      "5697666949",
                                                      "4608766830",
                                                      "4734946730",
                                                      "4740097688",
                                                      "6900007564",
                                                      "0000009666",
                                                      "8000004755",
                                                      "6800007755")),
                // Example 02 - After step 9
                Arguments.of(EXAMPLE_02,   9, List.of("9060000644",
                                                      "7800000976",
                                                      "6900000080",
                                                      "5840000082",
                                                      "5858000093",
                                                      "6962400000",
                                                      "8021250009",
                                                      "2221130009",
                                                      "9111128097",
                                                      "7911119976")),
                // Example 02 - After step 10
                Arguments.of(EXAMPLE_02,  10, List.of("0481112976",
                                                      "0031112009",
                                                      "0041112504",
                                                      "0081111406",
                                                      "0099111306",
                                                      "0093511233",
                                                      "0442361130",
                                                      "5532252350",
                                                      "0532250600",
                                                      "0032240000")),
                // Example 02 - After step 20
                Arguments.of(EXAMPLE_02,  20, List.of("3936556452",
                                                      "5686556806",
                                                      "4496555690",
                                                      "4448655580",
                                                      "4456865570",
                                                      "5680086577",
                                                      "7000009896",
                                                      "0000000344",
                                                      "6000000364",
                                                      "4600009543")),
                // Example 02 - After step 30
                Arguments.of(EXAMPLE_02,  30, List.of("0643334118",
                                                      "4253334611",
                                                      "3374333458",
                                                      "2225333337",
                                                      "2229333338",
                                                      "2276733333",
                                                      "2754574565",
                                                      "5544458511",
                                                      "9444447111",
                                                      "7944446119")),
                // Example 02 - After step 40
                Arguments.of(EXAMPLE_02,  40, List.of("6211111981",
                                                      "0421111119",
                                                      "0042111115",
                                                      "0003111115",
                                                      "0003111116",
                                                      "0065611111",
                                                      "0532351111",
                                                      "3322234597",
                                                      "2222222976",
                                                      "2222222762")),
                // Example 02 - After step 50
                Arguments.of(EXAMPLE_02,  50, List.of("9655556447",
                                                      "4865556805",
                                                      "4486555690",
                                                      "4458655580",
                                                      "4574865570",
                                                      "5700086566",
                                                      "6000009887",
                                                      "8000000533",
                                                      "6800000633",
                                                      "5680000538")),
                // Example 02 - After step 60
                Arguments.of(EXAMPLE_02,  60, List.of("2533334200",
                                                      "2743334640",
                                                      "2264333458",
                                                      "2225333337",
                                                      "2225333338",
                                                      "2287833333",
                                                      "3854573455",
                                                      "1854458611",
                                                      "1175447111",
                                                      "1115446111")),
                // Example 02 - After step 70
                Arguments.of(EXAMPLE_02,  70, List.of("8211111164",
                                                      "0421111166",
                                                      "0042111114",
                                                      "0004211115",
                                                      "0000211116",
                                                      "0065611111",
                                                      "0532351111",
                                                      "7322235117",
                                                      "5722223475",
                                                      "4572222754")),
                // Example 02 - After step 80
                Arguments.of(EXAMPLE_02,  80, List.of("1755555697",
                                                      "5965555609",
                                                      "4486555680",
                                                      "4458655580",
                                                      "4570865570",
                                                      "5700086566",
                                                      "7000008666",
                                                      "0000000990",
                                                      "0000000800",
                                                      "0000000000")),
                // Example 02 - After step 90
                Arguments.of(EXAMPLE_02,  90, List.of("7433333522",
                                                      "2643333522",
                                                      "2264333458",
                                                      "2226433337",
                                                      "2222433338",
                                                      "2287833333",
                                                      "2854573333",
                                                      "4854458333",
                                                      "3387779333",
                                                      "3333333333")),
                // Example 02 - After step 100
                Arguments.of(EXAMPLE_02, 100, List.of("0397666866",
                                                      "0749766918",
                                                      "0053976933",
                                                      "0004297822",
                                                      "0004229892",
                                                      "0053222877",
                                                      "0532222966",
                                                      "9322228966",
                                                      "7922286866",
                                                      "6789998766"))
                // @formatter:on
        );
    }


    @ParameterizedTest
    @MethodSource("simulateMeasurementSteps")
    void testSimulateSteps(final List<String> measurements, final int steps, final List<String> expected) {
        // Given
        final DumboOctopusCavern cavern = new DumboOctopusCavern(measurements);
        // When
        cavern.simulateSteps(steps);
        // Then
        assertEquals(new DumboOctopusCavern(expected).getOctopusesByLocation(), cavern.getOctopusesByLocation());
    }

    private static Stream<Arguments> simulateFlashCount() {
        return Stream.of(
                // @formatter:off
                Arguments.of(EXAMPLE_02,  10,  204),
                Arguments.of(EXAMPLE_02, 100, 1656)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("simulateFlashCount")
    void testTotalFlashCount(final List<String> measurements, final int steps, final int expectedFlashCount) {
        final DumboOctopusCavern cavern = new DumboOctopusCavern(measurements);
        assertEquals(expectedFlashCount, cavern.simulateSteps(steps));
    }
}