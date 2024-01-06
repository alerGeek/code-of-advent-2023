package pl.alergeek.codeofadvent.day5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.alergeek.codeofadvent.day5.services.SeedListParser;

import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Test {
    public static final String FILENAME_TASK = "/day5-test.txt";

    @Nested
    class Task1Test {
        @Test
        void shouldSumPointsFromOfficialInputFromFile() {
            Day5 day = new Day5(FILENAME_TASK);

            String solve = day.task1();
            int expected = 35;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldSumPointsFromOfficialInputAsString() {
            Day5 day = new Day5(FILENAME_TASK);
            String input = """
                    seeds: 79 14 55 13
                                        
                    seed-to-soil map:
                    50 98 2
                    52 50 48
                                        
                    soil-to-fertilizer map:
                    0 15 37
                    37 52 2
                    39 0 15
                                        
                    fertilizer-to-water map:
                    49 53 8
                    0 11 42
                    42 0 7
                    57 7 4
                                        
                    water-to-light map:
                    88 18 7
                    18 25 70
                                        
                    light-to-temperature map:
                    45 77 23
                    81 45 19
                    68 64 13
                                        
                    temperature-to-humidity map:
                    0 69 1
                    1 0 69
                                        
                    humidity-to-location map:
                    60 56 37
                    56 93 4
                    """;

            String solve = day.task1(input);
            int expected = 35;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }
    }

    @Nested
    class Task2Test {
        @Test
        void shouldSumPointsFromOfficialInputFromFile() {
            Day5 day = new Day5(FILENAME_TASK);

            String solve = day.task2();
            int expected = 46;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldSumPointsFromOfficialInputAsString() {
            Day5 day = new Day5(FILENAME_TASK);
            String input = """
                    seeds: 79 14 55 13
                                        
                    seed-to-soil map:
                    50 98 2
                    52 50 48
                                        
                    soil-to-fertilizer map:
                    0 15 37
                    37 52 2
                    39 0 15
                                        
                    fertilizer-to-water map:
                    49 53 8
                    0 11 42
                    42 0 7
                    57 7 4
                                        
                    water-to-light map:
                    88 18 7
                    18 25 70
                                        
                    light-to-temperature map:
                    45 77 23
                    81 45 19
                    68 64 13
                                        
                    temperature-to-humidity map:
                    0 69 1
                    1 0 69
                                        
                    humidity-to-location map:
                    60 56 37
                    56 93 4
                    """;

            String solve = day.task2(input);
            int expected = 46;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }
    }

    @Nested
    class AlmanacParserTest {
        @Test
        void shouldSumPointsFromOfficialInputAsString() {
            String input = "seeds: 79 14 55 13";
            List<Long> seeds = SeedListParser.parseSeeds(input);
            assertThat(seeds)
                    .hasSize(4)
                    .isEqualTo(List.of(79L, 14L, 55L, 13L));
        }

        @Test
        void shouldAlmanacStartsWithListOfSeedsToBePlanted() {
            String input = "seeds: 79 14 55 13";

            LongStream seeds = SeedListParser.parseSeedsWithRanges(input);
            List<Long> list = seeds.boxed().toList();
            assertThat(list)
                    .hasSize(14 + 13)
                    .containsAll(List.of(79L,
                            80L, 81L, 82L, 83L, 84L, 85L, 86L, 87L, 88L, 89L, 90L, 91L, 92L,
                            55L, 56L, 57L, 58L, 59L, 60L, 61L, 62L, 63L, 64L, 65L, 66L, 67L));
        }
    }
}
