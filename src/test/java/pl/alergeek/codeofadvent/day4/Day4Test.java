package pl.alergeek.codeofadvent.day4;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    public static final String FILENAME_TASK = "/day4-task1-test.txt";

    @Nested
    class Task1Test {
        @Test
        void shouldSumPointsFromOfficialInputFromFile() {
            Day4 day = new Day4(FILENAME_TASK);

            String solve = day.task1();
            int expected = 13;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldSumPointsFromOfficialInputAsString() {
            Day4 day = new Day4();

            String solve = day.task1("""
                    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                    Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                    """);
            int expected = 13;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldPointsEqualsSumWhenTwoCardsAreGiven() {
            Day4 day = new Day4();

            String solve = day.task1("""
                    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                    """);
            int expected = 2 + 1;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldPointEqualOneWhenOneWiningNumber() {
            Day4 day = new Day4();

            String solve = day.task1("""
                    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                    """);
            int expected = 1;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldDoublePointEachMatchAfterOneWiningNumber() {
            Day4 day = new Day4();

            String solve = day.task1("""
                    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                    """);
            assertThat(solve).isEqualTo(String.valueOf(8));

            solve = day.task1("""
                    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                    """);
            assertThat(solve).isEqualTo(String.valueOf(2));


            solve = day.task1("""
                    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                    """);
            assertThat(solve).isEqualTo(String.valueOf(2));
        }

        @Test
        void shouldPointEqualZeroWhenNoWiningNumber() {
            Day4 day = new Day4();

            String solve = day.task1("""
                    Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                    """);
            assertThat(solve).isEqualTo(String.valueOf(0));

            solve = day.task1("""
                    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                    """);
            assertThat(solve).isEqualTo(String.valueOf(0));
        }
    }

    @Nested
    class Task2Test {
        @Test
        void shouldCardsCountFromOfficialInputFileEqualsHint() {
            Day4 day = new Day4(FILENAME_TASK);

            String solve = day.task2();
            int expected = 30;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldCardsCountFromOfficialInputAsStringEqualsHint() {
            Day4 day = new Day4();

            String solve = day.task2("""
                    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                    Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                    """);
            int expected = 30;
            assertThat(solve).isEqualTo(String.valueOf(expected));
        }

        @Test
        void shouldWinOneWiningNumber() {
            Day4 day = new Day4();

            String solve = day.task2("""
                    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                    Card 5: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                    """);
            int originalCard = 1;
            int matchingNumbers = 1;
            assertThat(solve).isEqualTo(String.valueOf(originalCard + matchingNumbers + 1));
        }

        @Test
        void shouldNoWinNewCardsIfNoMatches() {
            Day4 day = new Day4();


            String solve = day.task2("""
                    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                    """);

            int originalCard2 = 1;
            int matchingNumbers2 = 0;
            assertThat(solve).isEqualTo(String.valueOf(originalCard2 + matchingNumbers2));
        }

    }
}